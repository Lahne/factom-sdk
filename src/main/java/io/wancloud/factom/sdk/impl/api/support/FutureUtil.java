package io.wancloud.factom.sdk.impl.api.support;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import io.wancloud.factom.sdk.FactomException;

public class FutureUtil {
	
	private FutureUtil(){
	}
	
	public static <T> T safeGet(Future<T> future) throws FactomException{
		T result = null;
		try {
			result = future.get(3, TimeUnit.MINUTES);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			throw new FactomException(e);
		}
		return result;
	}
	
}
