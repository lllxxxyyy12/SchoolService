package com.weixin.commons.exception;

public class InfoException extends RuntimeException {
	private String message;
	
	public InfoException(String message) {
		super(message);
		this.message = message;
	}
}	
