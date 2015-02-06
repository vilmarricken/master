package com.master.exception;

public class MasterRuntimeException extends RuntimeException {

	public MasterRuntimeException() {
	}

	public MasterRuntimeException(String message) {
		super(message);
	}

	public MasterRuntimeException(Throwable cause) {
		super(cause);
	}

	public MasterRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public MasterRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
