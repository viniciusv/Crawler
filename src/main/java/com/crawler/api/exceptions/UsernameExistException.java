package com.crawler.api.exceptions;

public class UsernameExistException extends RuntimeException {

	private static final long serialVersionUID = 8419736963795048579L;

	public UsernameExistException(String msg) {
		super(msg);
	}
	
	public UsernameExistException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
