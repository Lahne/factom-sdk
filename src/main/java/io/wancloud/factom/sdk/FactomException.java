package io.wancloud.factom.sdk;

public class FactomException extends RuntimeException {

	private static final long serialVersionUID = -1629768875380241602L;

	public FactomException(String message) {
		super(message);
	}

	public FactomException(Throwable cause) {
		super(cause);
	}
	
	public FactomException(String message, Throwable cause) {
		super(message, cause);
	}

}
