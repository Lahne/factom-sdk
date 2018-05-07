package io.wancloud.factom.sdk.core;

import io.wancloud.factom.sdk.FactomException;

public class ErrorMessageException extends FactomException {

	private static final long serialVersionUID = -5353157225506223097L;

	
	public ErrorMessage errorMessage;
	
	public ErrorMessageException(ErrorMessage errorMessage) {
		super(errorMessage.getMessage());
		this.errorMessage = errorMessage;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

}
