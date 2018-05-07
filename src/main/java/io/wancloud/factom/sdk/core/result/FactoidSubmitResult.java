package io.wancloud.factom.sdk.core.result;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class FactoidSubmitResult {

	private final String message;
	
	private final String txid;

	@JsonCreator
	public FactoidSubmitResult(@JsonProperty("txid") String txid, @JsonProperty("message") String message) {
		this.txid = txid;
		this.message = message;
	}


	public String getMessage() {
		return message;
	}


	public String getTxid() {
		return txid;
	}

}
