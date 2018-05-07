/**
 * 
 */
package io.wancloud.factom.sdk.core.result;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class DirectoryBlockResult {

	@JsonProperty("header")
	private Header header;

	@JsonProperty("entryblocklist")
	private List<EntryBlock> entryBlock;
	
	public DirectoryBlockResult() {
	}

	public Header getHeader() {
		return header;
	}

	public List<EntryBlock> getEntryBlock() {
		return entryBlock;
	}
	

	public void setHeader(Header header) {
		this.header = header;
	}

	public void setEntryBlock(List<EntryBlock> entryBlock) {
		this.entryBlock = entryBlock;
	}


	public class Header{
		@JsonProperty("prevblockkeymr")
		String prevblockkeymr;
		
		@JsonProperty("sequencenumber")
		Long directoryHeight;
		
		@JsonProperty("timestamp")
		Long timestamp;
		
		public Header(){
		}
	
		public String getPrevblockkeymr() {
			return prevblockkeymr;
		}
	
		public Long getDirectoryHeight() {
			return directoryHeight;
		}
	
		public Long getTimestamp() {
			return timestamp;
		}

		public void setPrevblockkeymr(String prevblockkeymr) {
			this.prevblockkeymr = prevblockkeymr;
		}

		public void setDirectoryHeight(Long directoryHeight) {
			this.directoryHeight = directoryHeight;
		}

		public void setTimestamp(Long timestamp) {
			this.timestamp = timestamp;
		}
	
	}
}
