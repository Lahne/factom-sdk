package io.wancloud.factom.sdk.core.result;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EntryBlockResult {
	
	@JsonProperty("header")
	private Header header;
	
	@JsonProperty("entrylist")
	private List<Entry> entries;
	
	public EntryBlockResult() {
	}

	public Header getHeader() {
		return header;
	}

	public List<Entry> getEntries() {
		return entries;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public void setEntries(List<Entry> entries) {
		this.entries = entries;
	}

	public class Header{

		@JsonProperty("prevkeymr")
		String prevKeymr;
		
		@JsonProperty("dbheight")
		Long dbHeight;
		
		@JsonProperty("blocksequencenumber")
		Long blockSequencenumber;
		
		@JsonProperty("timestamp")
		Long timestamp;
		
		@JsonProperty("chainid")
		String chainid;

		public Header() {
		}

		public String getPrevKeymr() {
			return prevKeymr;
		}

		public Long getDbHeight() {
			return dbHeight;
		}

		public Long getBlockSequencenumber() {
			return blockSequencenumber;
		}

		public Long getTimestamp() {
			return timestamp;
		}

		public String getChainid() {
			return chainid;
		}

		public void setPrevKeymr(String prevKeymr) {
			this.prevKeymr = prevKeymr;
		}

		public void setDbHeight(Long dbHeight) {
			this.dbHeight = dbHeight;
		}

		public void setBlockSequencenumber(Long blockSequencenumber) {
			this.blockSequencenumber = blockSequencenumber;
		}

		public void setTimestamp(Long timestamp) {
			this.timestamp = timestamp;
		}

		public void setChainid(String chainid) {
			this.chainid = chainid;
		}
		
	}

	public class Entry{
		
		@JsonProperty("timestamp")
		private String timestamp;
		
		@JsonProperty("entryHash")
		private String entryHash;
	
		
		public Entry() {
		}

		public String getTimestamp() {
			return timestamp;
		}
	
		public String getEntryHash() {
			return entryHash;
		}

		public void setTimestamp(String timestamp) {
			this.timestamp = timestamp;
		}

		public void setEntryHash(String entryHash) {
			this.entryHash = entryHash;
		}
	
	}

}
