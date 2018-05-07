package io.wancloud.factom.sdk.core.result;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EntryCommitResult {

	@JsonProperty("txid")
	private String txid;
	
	@JsonProperty("message")
	private String message;
	
	@JsonProperty("entryhash")
	private String entryHash;

	public EntryCommitResult() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public String getTxid() {
		return txid;
	}

	public String getEntryHash() {
		return entryHash;
	}

	public void setEntryHash(String entryHash) {
		this.entryHash = entryHash;
	}

	public void setTxid(String txid) {
		this.txid = txid;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}