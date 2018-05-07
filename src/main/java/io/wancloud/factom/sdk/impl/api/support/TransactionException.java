package io.wancloud.factom.sdk.impl.api.support;

import io.wancloud.factom.sdk.FactomException;

/**
 * @author wanglei
 */
public class TransactionException extends FactomException {
	
	private static final long serialVersionUID = 2343667080371179603L;

	public TransactionException(String message) {
		super(message);
	}

	public TransactionException(Throwable cause) {
		super(cause);
	}

	public TransactionException(String message, Throwable cause) {
		super(message, cause);
	}
}
