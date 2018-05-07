package io.wancloud.factom.sdk.impl.api.async;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import io.wancloud.factom.sdk.core.FactomResponse;
import io.wancloud.factom.sdk.core.ResultParser;

public class ResultFuture<T> implements Future<T> {
	
	private Future<FactomResponse> response;
	
	private ResultParser<T> convertor;

	public ResultFuture(Future<FactomResponse> response, ResultParser<T> convertor) {
		super();
		this.response = response;
		this.convertor = convertor;
	}

	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		return response.cancel(mayInterruptIfRunning);
	}

	@Override
	public boolean isCancelled() {
		return response.isCancelled();
	}

	@Override
	public boolean isDone() {
		return response.isDone();
	}

	@Override
	public T get() throws InterruptedException, ExecutionException {
		return convertor.parse(response.get());
	}

	@Override
	public T get(long timeout, TimeUnit unit)
			throws InterruptedException, ExecutionException, TimeoutException {
		return convertor.parse(response.get(timeout, unit));
	}
	

}
