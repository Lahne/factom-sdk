package io.wancloud.factom.sdk.support;

import io.wancloud.factom.sdk.FactomException;

public class TimeoutException extends FactomException{

	private static final long serialVersionUID = 5096484296843740799L;

	public TimeoutException(String message) {
		super(message);
	}

	public TimeoutException(Throwable cause) {
		super(cause);
	}
	
	public TimeoutException(String message, Throwable cause) {
		super(message, cause);
	}

}
