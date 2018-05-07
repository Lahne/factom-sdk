package io.wancloud.factom.sdk.core.result;


public class ComposeTransactionResult {

	private String hexTransaction;
	
	public ComposeTransactionResult(String hexTransaction) {
		this.hexTransaction = hexTransaction;
	}

	public String getHexTransaction() {
		return hexTransaction;
	}

	public void setHexTransaction(String hexTransaction) {
		this.hexTransaction = hexTransaction;
	}

	
}
