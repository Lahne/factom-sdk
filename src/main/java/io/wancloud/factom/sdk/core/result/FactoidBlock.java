package io.wancloud.factom.sdk.core.result;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FactoidBlock {
	@JsonProperty("chainid")
	private String chainid;
	
	@JsonProperty("keymr")
	private String keymr;
	
	@JsonProperty("ledgerkeymr")
	private String ledgerKeymr;
	
	@JsonProperty("bodymr")
	private String bodyKeymr;
	
	@JsonProperty("prevkeymr")
	private String prevKeymr;
	
	@JsonProperty("prevledgerkeymr")
	private String prevLedgerKeymr;
	
	@JsonProperty("exchrate")
	private Integer exchangeRate;

	@JsonProperty("dbheight")
	private Long dbHeight;
	
	public FactoidBlock() {
	}

	public String getChainid() {
		return chainid;
	}

	public void setChainid(String chainid) {
		this.chainid = chainid;
	}

	public String getKeymr() {
		return keymr;
	}

	public void setKeymr(String keymr) {
		this.keymr = keymr;
	}

	public String getLedgerKeymr() {
		return ledgerKeymr;
	}

	public void setLedgerKeymr(String ledgerKeymr) {
		this.ledgerKeymr = ledgerKeymr;
	}

	public String getBodyKeymr() {
		return bodyKeymr;
	}

	public void setBodyKeymr(String bodyKeymr) {
		this.bodyKeymr = bodyKeymr;
	}

	public String getPrevKeymr() {
		return prevKeymr;
	}

	public void setPrevKeymr(String prevKeymr) {
		this.prevKeymr = prevKeymr;
	}

	public String getPrevLedgerKeymr() {
		return prevLedgerKeymr;
	}

	public void setPrevLedgerKeymr(String prevLedgerKeymr) {
		this.prevLedgerKeymr = prevLedgerKeymr;
	}

	public Integer getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(Integer exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public Long getDbHeight() {
		return dbHeight;
	}

	public void setDbHeight(Long dbHeight) {
		this.dbHeight = dbHeight;
	}

}

class FactoidTransaction{

    
    @JsonProperty("millitimestamp")
    private Long millitTimestamp;
    
    @JsonProperty("blockheight")
    private Long blockHeight;

	@JsonProperty("sigblocks")
	private List<SigBlock> sigBlocks;

	@JsonProperty("inputs")
	private List<AddressAmount> inputs;
	
	@JsonProperty("outputs")
	private List<AddressAmount> outputs;
	
	@JsonProperty("outecs")
	private List<AddressAmount> ecOutputs;

	@JsonProperty("rcds")
	private List<AddressAmount> rcds;

	
	public Long getMillitTimestamp() {
		return millitTimestamp;
	}

	public void setMillitTimestamp(Long millitTimestamp) {
		this.millitTimestamp = millitTimestamp;
	}

	public Long getBlockHeight() {
		return blockHeight;
	}

	public void setBlockHeight(Long blockHeight) {
		this.blockHeight = blockHeight;
	}

	public List<SigBlock> getSigBlocks() {
		return sigBlocks;
	}

	public void setSigBlocks(List<SigBlock> sigBlocks) {
		this.sigBlocks = sigBlocks;
	}

	public List<AddressAmount> getInputs() {
		return inputs;
	}

	public void setInputs(List<AddressAmount> inputs) {
		this.inputs = inputs;
	}

	public List<AddressAmount> getOutputs() {
		return outputs;
	}

	public void setOutputs(List<AddressAmount> outputs) {
		this.outputs = outputs;
	}

	public List<AddressAmount> getEcOutputs() {
		return ecOutputs;
	}

	public void setEcOutputs(List<AddressAmount> ecOutputs) {
		this.ecOutputs = ecOutputs;
	}

	public List<AddressAmount> getRcds() {
		return rcds;
	}

	public void setRcds(List<AddressAmount> rcds) {
		this.rcds = rcds;
	}

	public class SigBlock{
		
		@JsonProperty("signatures")
		List<String> signatures;

		public SigBlock() {
		}

		public List<String> getSignatures() {
			return signatures;
		}

		public void setSignatures(List<String> signatures) {
			this.signatures = signatures;
		}
		
	}
	
	public class AddressAmount{
		
		@JsonProperty("amount")
		Long amount;
		
		@JsonProperty("address")
		String address;
		
		@JsonProperty("useraddress")
		String userAddress;

		public AddressAmount() {
		}

		public Long getAmount() {
			return amount;
		}

		public String getAddress() {
			return address;
		}

		public String getUserAddress() {
			return userAddress;
		}

		public void setAmount(Long amount) {
			this.amount = amount;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public void setUserAddress(String userAddress) {
			this.userAddress = userAddress;
		}
		
	} 

}

