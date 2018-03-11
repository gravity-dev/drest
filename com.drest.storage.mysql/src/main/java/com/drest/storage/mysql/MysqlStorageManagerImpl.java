package com.drest.storage.mysql;

import com.drest.app.utils.log.EngineLoggerFactory;
import com.drest.engine.managers.IStorageManager;

/**
 * MySql database storage implementation
 * 
 * @author arvind
 *
 */
public class MysqlStorageManagerImpl implements IStorageManager {

	private final static String STORAGE_TYPE = "MYSQL";
	private final static String STORAGE_ID = "mysql-5.6";

	private MysqlStorageConfig mysqlConfig;
	
	@Override
	public String getStorageType() {
		return STORAGE_TYPE;
	}

	@Override
	public String getStorageId() {
		return STORAGE_ID;
	}

	@Override
	public void initialize(String configFile) throws Exception {
		mysqlConfig = new MysqlStorageConfig();
		EngineLoggerFactory.info("Initialzed mysql data source: ");
	}
	
	public MysqlStorageConfig getConfig()
	{
		return this.mysqlConfig;
	}
}