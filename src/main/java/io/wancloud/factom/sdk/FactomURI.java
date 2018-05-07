package io.wancloud.factom.sdk;

import java.net.URI;

import io.wancloud.factom.sdk.util.FactomAssert;

public class FactomURI {

	private URI factomdURI;
	
	private URI walletdURI;

	private FactomURI(URI factomdURI, URI walletdURI) {
		FactomAssert.notNull(factomdURI, "Factomd URI should not be null");
		FactomAssert.notNull(walletdURI, "Walletd URI should not be null");
		this.factomdURI = factomdURI;
		this.walletdURI = walletdURI;
	}
	
	public static FactomURI create(URI factomdURI, URI walletdURI){
		return new FactomURI(factomdURI, walletdURI);
	}
	
	public static FactomURI create(String factomdURI, String walletdURI){
		return new FactomURI(URI.create(factomdURI), URI.create(walletdURI));
	}

	public URI getFactomdURI() {
		return factomdURI;
	}

	public URI getWalletdURI() {
		return walletdURI;
	}
	
}
