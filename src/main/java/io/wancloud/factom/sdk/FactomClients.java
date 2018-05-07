package io.wancloud.factom.sdk;

import io.wancloud.factom.sdk.api.sync.CloseableFactomClient;
import io.wancloud.factom.sdk.impl.api.sync.FactomClientBuilder;

public class FactomClients {

	private FactomClients() {
	}

	public static FactomClientBuilder custom() {
		return new FactomClientBuilder();
	}

	public static CloseableFactomClient create(FactomURI factomURI) {
		return custom().setFactomURI(factomURI).usingHttpAsyncClient().build();
	}

	public static CloseableFactomClient create(FactomURI factomURI, ClientContext clientContext) {
		return custom().setFactomURI(factomURI).setClientContext(clientContext).usingHttpAsyncClient().build();
	}
}
