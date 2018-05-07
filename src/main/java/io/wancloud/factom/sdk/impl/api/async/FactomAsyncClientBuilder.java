package io.wancloud.factom.sdk.impl.api.async;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;

import org.apache.http.client.ResponseHandler;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

import io.wancloud.factom.sdk.ClientContext;
import io.wancloud.factom.sdk.FactomURI;
import io.wancloud.factom.sdk.api.async.CloseableFactomAsyncClient;
import io.wancloud.factom.sdk.api.async.CommandAsyncExecutor;
import io.wancloud.factom.sdk.core.FactomResponse;
import io.wancloud.factom.sdk.core.FactomResponseHandler;
import io.wancloud.factom.sdk.util.FactomAssert;


public class FactomAsyncClientBuilder {

	private FactomURI factomURI;

	private ResponseHandler<FactomResponse> responseHandler;

	private CloseableHttpAsyncClient httpAsyncClient;

	private ClientContext clientContext;

	private ExecutorService executorService;

	public FactomAsyncClientBuilder setFactomURI(FactomURI factomURI) {
		this.factomURI = factomURI;
		return this;
	}

	public FactomAsyncClientBuilder setResponseHandler(ResponseHandler<FactomResponse> responseHandler) {
		this.responseHandler = responseHandler;
		return this;
	}

	public FactomAsyncClientBuilder setClientContext(ClientContext config) {
		this.clientContext = config;
		return this;
	}

	public FactomAsyncClientBuilder setHttpAsyncClient(CloseableHttpAsyncClient httpAsyncClient) {
		this.httpAsyncClient = httpAsyncClient;
		return this;
	}

	/**
	 * if you don't provide a {@code ExecutorService}, we default using
	 * {@code ForkJoinPool.commonPool()}.
	 * 
	 * @param executorService
	 * @return
	 */
	public FactomAsyncClientBuilder setExecutorService(ExecutorService executorService) {
		this.executorService = executorService;
		return this;
	}

	public CloseableFactomAsyncClient build() {
		FactomAssert.notNull(factomURI, "FactomURI should not be null, please set it");

		if (clientContext == null) {
			clientContext = new ClientContext();
		}

		if (responseHandler == null) {
			responseHandler = new FactomResponseHandler();
		}

		if (executorService == null) {
			executorService = ForkJoinPool.commonPool();
		}

		if (httpAsyncClient == null) {
			httpAsyncClient = HttpAsyncClients.custom().disableConnectionState().disableCookieManagement().build();
		}

		if (!httpAsyncClient.isRunning()) {
			httpAsyncClient.start();
		}

		CommandAsyncExecutor commandAsyncExecutor = new DefaultCommandAsyncExecutor(httpAsyncClient, responseHandler);
		return new DefaultFactomAsyncClient(factomURI, commandAsyncExecutor, executorService, clientContext);
	}

}
