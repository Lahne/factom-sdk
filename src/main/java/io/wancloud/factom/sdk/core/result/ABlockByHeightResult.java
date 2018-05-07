package io.wancloud.factom.sdk.core.result;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ABlockByHeightResult {

	@JsonProperty("rawdata")
	private String rawData;
	
	@JsonProperty("ablock")
	private AdminBlock adminBlock;
	
	
	public ABlockByHeightResult(){
	}


	public String getRawData() {
		return rawData;
	}


	public AdminBlock getAdminBlock() {
		return adminBlock;
	}


	public void setRawData(String rawData) {
		this.rawData = rawData;
	}


	public void setAdminBlock(AdminBlock adminBlock) {
		this.adminBlock = adminBlock;
	}

	
}
