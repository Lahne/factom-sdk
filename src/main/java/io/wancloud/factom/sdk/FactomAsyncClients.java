package io.wancloud.factom.sdk;

import io.wancloud.factom.sdk.api.async.CloseableFactomAsyncClient;
import io.wancloud.factom.sdk.impl.api.async.FactomAsyncClientBuilder;

public class FactomAsyncClients {

	private FactomAsyncClients() {
	}

	public static FactomAsyncClientBuilder custom() {
		return new FactomAsyncClientBuilder();
	}

	public static CloseableFactomAsyncClient create(FactomURI factomURI) {
		return custom().setFactomURI(factomURI).build();
	}

	public static CloseableFactomAsyncClient create(FactomURI factomURI, ClientContext clientContext) {
		return custom().setFactomURI(factomURI).setClientContext(clientContext).build();
	}
}
