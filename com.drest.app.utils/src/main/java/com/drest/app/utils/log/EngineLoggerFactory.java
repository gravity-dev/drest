package com.drest.app.utils.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EngineLoggerFactory {

	private static Logger logger = LoggerFactory.getLogger(EngineLoggerFactory.class);

	public static void info(String message) {
		logger.info(message);
	}

	public static void warning(String message) {
		logger.warn(message);
	}

	public static void error(String message) {
		logger.error("** ERROR **", message);
	}

	public static void severe(String message) {
		logger.error("** SEVERE **", message);
	}
}