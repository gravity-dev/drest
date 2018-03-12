package com.drest.app.exceptions;

import java.io.StringWriter;

import com.drest.app.utils.log.EngineLoggerFactory;

public class AppException extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static enum ERROR_CODE {
		REST_001, ENGINE_001, ENGINE_002, SERVICE_001, DB_001, CRYPT_MD5_001, LOGIN_001, LOGIN_002, UTILS_001
	}

	private ERROR_CODE exceptionCode = null;
	private Throwable throwable;
	private String exceptionMsg;

	public AppException(ERROR_CODE code, String message, Throwable th) {
		this.exceptionCode = code;
		this.exceptionMsg = message;
		this.throwable = th;
	}

	public void log() {
		StringWriter strW = new StringWriter();
		if (null != this.exceptionCode)
			strW.append("CODE : " + this.exceptionCode);
		if (null != this.exceptionMsg)
			strW.append("MESSAGE : " + this.exceptionMsg);
		if (null != this.throwable)
			strW.append("TRACE : " + this.throwable.getCause());
		EngineLoggerFactory.error(strW.toString());
	}
}
