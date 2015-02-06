package com.master.exception;

public class MasterException extends Exception {

	public MasterException() {
	}

	public MasterException(String message) {
		super(message);
	}

	public MasterException(Throwable cause) {
		super(cause);
	}

	public MasterException(String message, Throwable cause) {
		super(message, cause);
	}

	public MasterException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
