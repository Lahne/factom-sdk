package io.wancloud.factom.sdk.core;

public abstract class FactomRequest {
	
	public abstract String toJson();
	
	@Override
	public String toString(){
		return toJson();
	}
	
	
}
