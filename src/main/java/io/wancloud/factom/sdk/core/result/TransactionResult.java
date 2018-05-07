package io.wancloud.factom.sdk.core.result;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class TransactionResult {
	
	@JsonProperty("blockheight")
	private Long blockHeight;
	
	@JsonProperty("feespaid")
	private Long feesPaid;
	
	@JsonProperty("feesrequired")
	private Long feesRequired;
	
	@JsonProperty("signed")
	private Boolean signed;

	@JsonProperty("name")
	private String txName;
	
	@JsonProperty("timestamp")
	private Long timestamp;

	@JsonProperty("totalecoutputs")
	private Long totalEcOuputs;
	
	@JsonProperty("totalinputs")
	private Long totalInputs;
    
	@JsonProperty("totaloutputs")
	private Long totalOutputs;
    
	@JsonProperty("inputs")
	private List<AddressAmount> inputs;
   
	@JsonProperty("outputs")
	private List<AddressAmount> outputs;
   
	@JsonProperty("ecoutputs")
	private List<AddressAmount> ecOutputs;

	@JsonProperty("txid")
	private String txid;
	
	public TransactionResult() {
	}
	
	public Long getBlockHeight() {
		return blockHeight;
	}

	public Long getFeesPaid() {
		return feesPaid;
	}

	public Long getFeesRequired() {
		return feesRequired;
	}

	public Boolean getSigned() {
		return signed;
	}

	public String getTxName() {
		return txName;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public Long getTotalEcOuputs() {
		return totalEcOuputs;
	}

	public Long getTotalInputs() {
		return totalInputs;
	}

	public Long getTotalOutputs() {
		return totalOutputs;
	}

	public List<AddressAmount> getInputs() {
		return inputs;
	}

	public List<AddressAmount> getOutputs() {
		return outputs;
	}

	public List<AddressAmount> getEcOutputs() {
		return ecOutputs;
	}

	public String getTxid() {
		return txid;
	}

	public void setBlockHeight(Long blockHeight) {
		this.blockHeight = blockHeight;
	}

	public void setFeesPaid(Long feesPaid) {
		this.feesPaid = feesPaid;
	}

	public void setFeesRequired(Long feesRequired) {
		this.feesRequired = feesRequired;
	}

	public void setSigned(Boolean signed) {
		this.signed = signed;
	}

	public void setTxName(String txName) {
		this.txName = txName;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public void setTotalEcOuputs(Long totalEcOuputs) {
		this.totalEcOuputs = totalEcOuputs;
	}

	public void setTotalInputs(Long totalInputs) {
		this.totalInputs = totalInputs;
	}

	public void setTotalOutputs(Long totalOutputs) {
		this.totalOutputs = totalOutputs;
	}

	public void setInputs(List<AddressAmount> inputs) {
		this.inputs = inputs;
	}

	public void setOutputs(List<AddressAmount> outputs) {
		this.outputs = outputs;
	}

	public void setEcOutputs(List<AddressAmount> ecOutputs) {
		this.ecOutputs = ecOutputs;
	}

	public void setTxid(String txid) {
		this.txid = txid;
	}

	@Override
	public String toString() {
		return "TransactionResult [txid=" + txid + ", blockHeight=" + blockHeight + ", feesPaid=" + feesPaid
				+ ", feesRequired=" + feesRequired + ", signed=" + signed + ", txName=" + txName + ", timestamp="
				+ timestamp + ", totalEcOuputs=" + totalEcOuputs + ", totalInputs=" + totalInputs + ", totalOutputs="
				+ totalOutputs + ", inputs=" + inputs + ", outputs=" + outputs + ", ecOutputs=" + ecOutputs + "]";
	}

}
