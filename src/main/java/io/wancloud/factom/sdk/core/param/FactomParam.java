package io.wancloud.factom.sdk.core.param;


public abstract class FactomParam<T> {

	protected FactomParam() {
	}

	public abstract T serialize();
	
}
