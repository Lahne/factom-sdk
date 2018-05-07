package io.wancloud.factom.sdk.core.result;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EcBlockByHeightResult {

	@JsonProperty("rawdata")
	private String rawData;
	
	@JsonProperty("ecblock")
	private EntryCreditBlock entryCreditBlock;
	
	public EcBlockByHeightResult() {
	}

	public String getRawData() {
		return rawData;
	}

	public void setRawData(String rawData) {
		this.rawData = rawData;
	}

	public EntryCreditBlock getEntryCreditBlock() {
		return entryCreditBlock;
	}

	public void setEntryCreditBlock(EntryCreditBlock entryCreditBlock) {
		this.entryCreditBlock = entryCreditBlock;
	}

}
