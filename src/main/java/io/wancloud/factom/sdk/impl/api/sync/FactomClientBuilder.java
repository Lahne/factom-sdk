package io.wancloud.factom.sdk.impl.api.sync;

import org.apache.http.client.ResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

import io.wancloud.factom.sdk.ClientContext;
import io.wancloud.factom.sdk.FactomURI;
import io.wancloud.factom.sdk.api.async.CommandAsyncExecutor;
import io.wancloud.factom.sdk.api.sync.CloseableFactomClient;
import io.wancloud.factom.sdk.api.sync.CommandExecutor;
import io.wancloud.factom.sdk.core.FactomResponse;
import io.wancloud.factom.sdk.core.FactomResponseHandler;
import io.wancloud.factom.sdk.impl.api.async.DefaultCommandAsyncExecutor;
import io.wancloud.factom.sdk.impl.api.async.DefaultFactomAsyncCommand;
import io.wancloud.factom.sdk.util.FactomAssert;

public class FactomClientBuilder {
	
	private static final int HTTP_CLIENT = 1;
	
	private static final int HTTP_ASYNC_CLIENT = 2;
	
	private FactomURI factomURI;

	private ResponseHandler<FactomResponse> responseHandler;

	private int httpClientType = HTTP_CLIENT;
	
	private CloseableHttpAsyncClient httpAsyncClient;
	
	private CloseableHttpClient httpClient;
	
	private ClientContext clientContext;

	public FactomClientBuilder setFactomURI(FactomURI factomURI) {
		this.factomURI = factomURI;
		return this;
	}

	public FactomClientBuilder setResponseHandler(ResponseHandler<FactomResponse> responseHandler) {
		this.responseHandler = responseHandler;
		return this;
	}

	public FactomClientBuilder setHttpAsyncClient(CloseableHttpAsyncClient httpAsyncClient) {
		this.httpAsyncClient = httpAsyncClient;
		return this;
	}

	public FactomClientBuilder setClientContext(ClientContext context) {
		this.clientContext = context;
		return this;
	}
	
	public FactomClientBuilder usingHttpAsyncClient(){
		this.httpClientType = HTTP_ASYNC_CLIENT;
		return this;
	}
	
	public FactomClientBuilder usingHttpAsyncClient(CloseableHttpAsyncClient httpAsyncClient){
		this.httpClientType = HTTP_ASYNC_CLIENT;
		this.httpAsyncClient = httpAsyncClient;
		return this;
	}
	
	public FactomClientBuilder usingHttpClient(){
		this.httpClientType = HTTP_CLIENT;
		return this;
	}
	
	public FactomClientBuilder usingHttpClient(CloseableHttpClient httpClient){
		this.httpClientType = HTTP_CLIENT;
		this.httpClient = httpClient;
		return this;
	}
	
	public CloseableFactomClient build() {
		FactomAssert.notNull(factomURI, "FactomURI should not be null, please set it");

		if (clientContext == null){
			clientContext = new ClientContext();
		}
		
		if (responseHandler == null) {
			responseHandler = new FactomResponseHandler();
		}

		if (httpClientType == HTTP_ASYNC_CLIENT){
			return buildWithHttpAsyncClient();
		} else {
			return buildWithHttpClient();
		}
	}
	
	CloseableFactomClient buildWithHttpClient(){
		if (httpClient == null) {
			httpClient = HttpClients.custom().disableConnectionState().disableCookieManagement()
					.build();
		}
		
		CommandExecutor	commandExecutor = new DefaultCommandExecutor(httpClient, responseHandler);
		return new DefaultFactomClient(factomURI, commandExecutor, clientContext);
	}
	
	CloseableFactomClient buildWithHttpAsyncClient(){
		if (httpAsyncClient == null) {
			httpAsyncClient = HttpAsyncClients.custom().disableConnectionState().disableCookieManagement()
					.build();
		}
		if (!httpAsyncClient.isRunning()){
			httpAsyncClient.start();
		}
		
		CommandAsyncExecutor commandAsyncExecutor = new DefaultCommandAsyncExecutor(httpAsyncClient, responseHandler);
		DefaultFactomAsyncCommand asyncCommands = new DefaultFactomAsyncCommand(factomURI, commandAsyncExecutor);
		return new DelegatingFactomClient(asyncCommands, clientContext);
	}

}
