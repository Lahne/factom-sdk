package io.wancloud.factom.sdk.core.result;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WalletBackupResult {
	
	@JsonProperty("wallet-seed")
	private String walletSeed;
	
	@JsonProperty("addresses")
	private List<AddressResult> addresses;
	
	public WalletBackupResult() {
	}

	public String getWalletSeed() {
		return walletSeed;
	}

	public List<AddressResult> getAddresses() {
		return addresses;
	}

	public void setWalletSeed(String walletSeed) {
		this.walletSeed = walletSeed;
	}

	public void setAddresses(List<AddressResult> addresses) {
		this.addresses = addresses;
	}

}
