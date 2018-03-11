package com.drest.engine.service;

/**
 * Abstract storage Search Service Make sure you implement this as singleton
 * service loader class.
 * 
 * @author arvind
 *
 */
public interface IStorageService {

	/**
	 * Create Service instance
	 * 
	 * @param manager
	 */
	public IStorageService instance();
	
}
