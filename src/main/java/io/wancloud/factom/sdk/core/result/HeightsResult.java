/**
 * 
 */
package io.wancloud.factom.sdk.core.result;

import com.fasterxml.jackson.annotation.JsonProperty;


public class HeightsResult {

	@JsonProperty("directoryblockheight")
	private Long directoryBlockHeight;
	
	@JsonProperty("leaderheight")
	private Long leaderHeight;
	
	@JsonProperty("entryblockheight")
	private Long entryBlockHeight;
	
	@JsonProperty("entryheight")
	private Long entryHeight;
	
	@JsonProperty("missingentrycount")
	private Long missingEntryHeight;
	
	@JsonProperty("entryblockdbheightprocessing")
	private Long entryBlockDbHeightProcessing;
	
	@JsonProperty("entryblockdbheightcomplete")
	private Long entryBlockDbHeightComplete;
    
	public HeightsResult(){
	}

	public Long getDirectoryBlockHeight() {
		return directoryBlockHeight;
	}

	public Long getLeaderHeight() {
		return leaderHeight;
	}

	public Long getEntryBlockHeight() {
		return entryBlockHeight;
	}

	public Long getEntryHeight() {
		return entryHeight;
	}

	public Long getMissingEntryHeight() {
		return missingEntryHeight;
	}

	public Long getEntryBlockDbHeightProcessing() {
		return entryBlockDbHeightProcessing;
	}

	public Long getEntryBlockDbHeightComplete() {
		return entryBlockDbHeightComplete;
	}

	public void setDirectoryBlockHeight(Long directoryBlockHeight) {
		this.directoryBlockHeight = directoryBlockHeight;
	}

	public void setLeaderHeight(Long leaderHeight) {
		this.leaderHeight = leaderHeight;
	}

	public void setEntryBlockHeight(Long entryBlockHeight) {
		this.entryBlockHeight = entryBlockHeight;
	}

	public void setEntryHeight(Long entryHeight) {
		this.entryHeight = entryHeight;
	}

	public void setMissingEntryHeight(Long missingEntryHeight) {
		this.missingEntryHeight = missingEntryHeight;
	}

	public void setEntryBlockDbHeightProcessing(Long entryBlockDbHeightProcessing) {
		this.entryBlockDbHeightProcessing = entryBlockDbHeightProcessing;
	}

	public void setEntryBlockDbHeightComplete(Long entryBlockDbHeightComplete) {
		this.entryBlockDbHeightComplete = entryBlockDbHeightComplete;
	}
	
}
