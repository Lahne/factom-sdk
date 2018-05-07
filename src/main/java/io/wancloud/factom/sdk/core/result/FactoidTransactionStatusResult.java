package io.wancloud.factom.sdk.core.result;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FactoidTransactionStatusResult {

    @JsonProperty("txid")
    private String txid;

	@JsonProperty("transactiondate")
	private String transactionDate;
	
	@JsonProperty("transactiondatestring")
	private String transactionDateString;
	
	@JsonProperty("blockdate")
	private Long blockDate;
	
	@JsonProperty("blockdatestring")
	private String blockDateString;
	
	@JsonProperty("status")
	private String status;
    	
	public FactoidTransactionStatusResult() {
	}

	public String getTxid() {
		return txid;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public String getTransactionDateString() {
		return transactionDateString;
	}

	public Long getBlockDate() {
		return blockDate;
	}

	public String getBlockDateString() {
		return blockDateString;
	}

	public String getStatus() {
		return status;
	}

	public void setTxid(String txid) {
		this.txid = txid;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public void setTransactionDateString(String transactionDateString) {
		this.transactionDateString = transactionDateString;
	}

	public void setBlockDate(Long blockDate) {
		this.blockDate = blockDate;
	}

	public void setBlockDateString(String blockDateString) {
		this.blockDateString = blockDateString;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
