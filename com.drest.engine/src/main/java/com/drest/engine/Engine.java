package com.drest.engine;

import com.drest.app.exceptions.AppException;
import com.drest.app.exceptions.AppException.ERROR_CODE;
import com.drest.app.utils.file.YamlReader;
import com.drest.app.utils.log.EngineLoggerFactory;
import com.drest.engine.managers.IStorageManager;
import com.drest.engine.service.IStorageService;

/**
 * Main application engine class
 * 
 * @author arvind
 *
 */
public class Engine {

	/**
	 * Private constructor single instance
	 */
	private Engine() {
	}

	private static final String APPLICATION_CONFIG_FILE = "application.yml";

	private static Engine engine = null;
	private IStorageManager storageManager;
	private IStorageService storageService;

	private String passPhrase = null;

	/**
	 * Get engine instance
	 * 
	 * @return
	 */
	public static Engine getInstance() throws AppException {
		if (engine == null) {
			try {
				engine = new Engine();
				EngineLoggerFactory
						.info("********************* ENGINE STARTED *********************");
			} catch (Exception ex) {
				EngineLoggerFactory.severe("ENGINE FAILED TO INSTANTIATE");
				throw new AppException(
						ERROR_CODE.ENGINE_002,
						"--------------------- ENGINE FAILED TO INSTANTIATE ---------------------",
						ex);
			}
		}
		return engine;
	}

	/**
	 * Start engine
	 * 
	 * @throws Exception
	 */
	public void boot() throws AppException {
		if (engine != null) {
			try {
				engine.init();
			} catch (Exception e) {
				AppException ex = new AppException(ERROR_CODE.ENGINE_001, "ERROR STARTUP", e);
				ex.log();
				throw ex;
			}
		} else {
			AppException ex =
					new AppException(ERROR_CODE.ENGINE_001, "ENGINE NOT INSTANTIATED YET", null);
			ex.log();
			throw ex;
		}
	}

	/**
	 * Start engine
	 * 
	 * @throws Exception
	 */
	private void init() throws Exception {
		EngineLoggerFactory.info("Starting engine configuration");
		initConfugration();

		EngineLoggerFactory.info("Starting engine storage");
		startUpStorage();
	}

	private void startUpStorage() throws Exception {
		ApplicationConfig appConfig = new ApplicationConfig(
				YamlReader.parseYamlResource(Engine.class, APPLICATION_CONFIG_FILE));

		passPhrase = appConfig.getEncryptionSalt();
		storageManager = instantiate(
				appConfig.getStorageConfig().getStorageManagerClass(),
				IStorageManager.class);

		storageManager.initialize(appConfig.getStorageConfig().getDbConfigFilePath());
		EngineLoggerFactory.info(
				"Loaded Storage Manager : " + appConfig.getStorageConfig().getProviderClass());

		storageService =
				instantiate(appConfig.getStorageConfig().getProviderClass(), IStorageService.class)
						.instance();
		EngineLoggerFactory.info(
				"Loaded Storage Service : " + appConfig.getStorageConfig().getProviderClass());

	}

	public String getEncyptionSalt() {
		return this.passPhrase;
	}

	/**
	 * Get engine storage manager
	 * 
	 * @return
	 */
	public IStorageManager getStorageManager() {
		return storageManager;
	}

	/**
	 * Get Storage service manager
	 * 
	 * @return
	 */
	public IStorageService getStorageService() {
		return storageService;
	}

	/**
	 * Initialize configuration
	 */
	private void initConfugration() {

	}

	/**
	 * Initialize classes dynamically
	 * 
	 * @param className
	 * @param type
	 * @return
	 */
	public <T> T instantiate(final String className, final Class<T> type) {
		try {
			return type.cast(Class.forName(className).newInstance());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new IllegalStateException(e);
		}
	}

}
