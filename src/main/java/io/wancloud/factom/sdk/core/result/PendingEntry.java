package io.wancloud.factom.sdk.core.result;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PendingEntry {

	@JsonProperty("EntryHash")
	private String entryHash;
	
	@JsonProperty("ChainID")
	private String chainId;

	@JsonProperty("Status")
	private String status;
	
	public PendingEntry() {
	}

	public String getEntryHash() {
		return entryHash;
	}

	public void setEntryHash(String entryHash) {
		this.entryHash = entryHash;
	}

	public String getChainId() {
		return chainId;
	}

	public void setChainId(String chainId) {
		this.chainId = chainId;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
	
}
