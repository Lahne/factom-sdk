/**
 * 
 */
package io.wancloud.factom.sdk.core.result;

import com.fasterxml.jackson.annotation.JsonProperty;


public class TransactionByHashResult {

	@JsonProperty("factoidtransaction")
	private TransactionResult transaction;

	@JsonProperty("includedintransactionblock")
	private String transactionBlock;
	
	@JsonProperty("includedindirectoryblock")
	private String directoryBlock;
	
	@JsonProperty("includedindirectoryblockheight")
	private Long directoryBlockHeight;
	
	public TransactionByHashResult() {
	}

	public TransactionResult getTransaction() {
		return transaction;
	}

	public String getTransactionBlock() {
		return transactionBlock;
	}

	public String getDirectoryBlock() {
		return directoryBlock;
	}

	public Long getDirectoryBlockHeight() {
		return directoryBlockHeight;
	}

	public void setTransaction(TransactionResult transaction) {
		this.transaction = transaction;
	}

	public void setTransactionBlock(String transactionBlock) {
		this.transactionBlock = transactionBlock;
	}

	public void setDirectoryBlock(String directoryBlock) {
		this.directoryBlock = directoryBlock;
	}

	public void setDirectoryBlockHeight(Long directoryBlockHeight) {
		this.directoryBlockHeight = directoryBlockHeight;
	}

}
