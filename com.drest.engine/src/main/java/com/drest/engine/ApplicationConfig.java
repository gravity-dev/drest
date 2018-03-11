package com.drest.engine;

import java.util.Map;

/**
 * Application configuration file.
 * 
 * @author arvind
 *
 */
public final class ApplicationConfig {

	Map<String, Object> config;

	public ApplicationConfig(Map<String, Object> config) {
		this.config = config;
	}

	@SuppressWarnings("unchecked")
	public StorageConfig getStorageConfig() {
		return new StorageConfig((Map<String, Object>) config.get("storage"));
	}
}

class StorageConfig {

	private String providerClass;
	private String storageManagerClass;

	private String dbConfigFilePath;

	public StorageConfig(Map<String, Object> config) {
		this.providerClass = (String) config.get("provider.class");
		this.storageManagerClass = (String) config.get("storage.manager.class");
		this.dbConfigFilePath = (String) config.get("db.config.file");
	}

	public String getStorageManagerClass() {
		return storageManagerClass;
	}
	
	public String getProviderClass() {
		return providerClass;
	}

	public void setProviderClass(String providerClass) {
		this.providerClass = providerClass;
	}

	public String getDbConfigFilePath() {
		return dbConfigFilePath;
	}

	public void setDbConfigFilePath(String dbConfigFilePath) {
		this.dbConfigFilePath = dbConfigFilePath;
	}

}
