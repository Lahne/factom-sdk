package io.wancloud.factom.sdk.core.result;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChainCommitResult extends EntryCommitResult {
	
	@JsonProperty("chainid")
	private String chainid;
	
	public ChainCommitResult() {
	}

	public String getChainid() {
		return chainid;
	}

	public void setChainid(String chainid) {
		this.chainid = chainid;
	}
	
}
