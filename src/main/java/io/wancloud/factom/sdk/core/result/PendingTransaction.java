package io.wancloud.factom.sdk.core.result;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PendingTransaction {

	@JsonProperty("TransactionID")
	private String txid;
	
	@JsonProperty("Status")
	private String status;

	public PendingTransaction() {
	}

	public String getTxid() {
		return txid;
	}

	public String getStatus() {
		return status;
	}

	public void setTxid(String txid) {
		this.txid = txid;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
