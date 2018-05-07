package io.wancloud.factom.sdk.core.result;

import com.fasterxml.jackson.annotation.JsonProperty;


public class RevealResult {

	@JsonProperty("entryhash")
	private String entryHash;

	@JsonProperty("message")
	private String message;
	
	@JsonProperty("chainid")
	private String chainid;
	
	public RevealResult() {
	}

	public String getEntryHash() {
		return entryHash;
	}

	public void setEntryHash(String entryHash) {
		this.entryHash = entryHash;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getChainid() {
		return chainid;
	}

	public void setChainid(String chainid) {
		this.chainid = chainid;
	}


}
