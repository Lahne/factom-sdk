package io.wancloud.factom.sdk.impl.api.async;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;

import io.wancloud.factom.sdk.FactomException;
import io.wancloud.factom.sdk.core.ErrorMessageException;
import io.wancloud.factom.sdk.core.FactomResponse;

public class FactomResponseFuture implements Future<FactomResponse> {

	private Future<HttpResponse> delegate;

	private ResponseHandler<FactomResponse> handler;

	private volatile boolean isProcessed;
	
	private FactomResponse factomResponse;
	
	private FactomException exception;
	
	public FactomResponseFuture(Future<HttpResponse> delegate, ResponseHandler<FactomResponse> handler) {
		super();
		this.delegate = delegate;
		this.handler = handler;
	}

	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		return delegate.cancel(mayInterruptIfRunning);
	}

	@Override
	public boolean isCancelled() {
		return delegate.isCancelled();
	}

	@Override
	public boolean isDone() {
		return delegate.isDone();
	}

	@Override
	public FactomResponse get() throws InterruptedException, ExecutionException {
		HttpResponse response = delegate.get();
		return handleHttpResponse(response);
	}

	@Override
	public FactomResponse get(long timeout, TimeUnit unit)
			throws InterruptedException, ExecutionException, TimeoutException {
		HttpResponse response = delegate.get(timeout, unit);
		return handleHttpResponse(response);
	}

	private FactomResponse handleHttpResponse(HttpResponse response) {
		
		if (!isProcessed){
			synchronized (this) {
				if (!isProcessed){
					try {
						factomResponse = handler.handleResponse(response);
					} catch (Exception ex) {
						exception = new FactomException(ex);
					}finally{
						isProcessed = true;
					}
				}
			}
		}
		
		if (exception != null){
			throw exception;
		}
		
		if (factomResponse != null && factomResponse.hasError()) {
			throw new ErrorMessageException(factomResponse.getErrorMessage());
		}
		
		return factomResponse;
	}

	protected boolean isProcessed() {
		return isProcessed;
	}
	
}
