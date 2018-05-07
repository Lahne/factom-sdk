package io.wancloud.factom.sdk.core.result;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FBlockByHeight {

	@JsonProperty("rawdata")
	private String rawData;
	
	@JsonProperty("fblock")
	private FactoidBlock factidBlock;
	
	public FBlockByHeight() {
	}

	public String getRawData() {
		return rawData;
	}

	public FactoidBlock getFactidBlock() {
		return factidBlock;
	}

	public void setRawData(String rawData) {
		this.rawData = rawData;
	}

	public void setFactidBlock(FactoidBlock factidBlock) {
		this.factidBlock = factidBlock;
	}

}
