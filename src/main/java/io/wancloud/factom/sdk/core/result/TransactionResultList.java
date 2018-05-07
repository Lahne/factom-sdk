package io.wancloud.factom.sdk.core.result;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionResultList {

	@JsonProperty("transactions")
	private List<TransactionResult> transactionList;
	
	public TransactionResultList() {
	}

	public List<TransactionResult> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(List<TransactionResult> transactionList) {
		this.transactionList = transactionList;
	}
	
}
