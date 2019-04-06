package com.weixin.commons.exception;

public class ServerException extends RuntimeException{
	private String message;
	
	public ServerException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
