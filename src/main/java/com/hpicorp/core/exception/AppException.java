package com.hpicorp.core.exception;

public class AppException extends RuntimeException {
    
	private static final long serialVersionUID = -6451545067975181812L;

	public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
