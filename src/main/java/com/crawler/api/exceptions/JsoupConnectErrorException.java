package com.crawler.api.exceptions;

public class JsoupConnectErrorException extends RuntimeException {

	private static final long serialVersionUID = -5203809892950706503L;
	
	public JsoupConnectErrorException(String msg) {
		super(msg);
	}
	
	public JsoupConnectErrorException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
