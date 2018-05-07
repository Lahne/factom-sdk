package io.wancloud.factom.sdk.core;

@FunctionalInterface
public interface ResultParser<T> {

	T parse(FactomResponse response);
	
}
