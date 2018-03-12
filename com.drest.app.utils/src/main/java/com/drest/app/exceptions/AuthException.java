package com.drest.app.exceptions;

public class AuthException extends AppException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1243434494414310290L;

	public AuthException(ERROR_CODE code, String message, Throwable th) {
		super(code, message, th);
	}
}
