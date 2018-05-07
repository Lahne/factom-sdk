package io.wancloud.factom.sdk.core.request;


public class FactomRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FactomRequestException() {
	}

	public FactomRequestException(String message) {
		super(message);
	}

	public FactomRequestException(Throwable cause) {
		super(cause);
	}

	public FactomRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	public FactomRequestException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
