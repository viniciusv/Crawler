package com.crawler.api.exceptions;

public class AuthorizationException extends RuntimeException{

	private static final long serialVersionUID = -2541116530980723381L;
	
	public AuthorizationException(String msg) {
		super(msg);
	}
	
	public AuthorizationException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
