/**
 * 
 */
package io.wancloud.factom.sdk.core.result;

import com.fasterxml.jackson.annotation.JsonProperty;


public class DBlockByHeightResult {

	@JsonProperty("rawdata")
	private String rawData;

	@JsonProperty("dblock")
	private DirectoryBlock directoryBlock;

	public DBlockByHeightResult() {
	}

	public String getRawData() {
		return rawData;
	}

	public DirectoryBlock getDirectoryBlock() {
		return directoryBlock;
	}

	public void setRawData(String rawData) {
		this.rawData = rawData;
	}

	public void setDirectoryBlock(DirectoryBlock directoryBlock) {
		this.directoryBlock = directoryBlock;
	}

}
