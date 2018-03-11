package com.drest.engine.managers;

/**
 * Storage manager interface to persist and fetch the data for the scrape engine
 * 
 * @author arvind
 *
 */
public interface IStorageManager {

	public String getStorageType();

	public String getStorageId();

	public void initialize(String configFile) throws Exception;
}
