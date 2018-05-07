package io.wancloud.factom.sdk.core.result;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DirectoryBlock {

	@JsonProperty("header")
	private Header header;

	@JsonProperty("dbentries")
	private List<EntryBlock> entryBlocks;

	@JsonProperty("dbhash")
	private String dbHash;

	@JsonProperty("keymr")
	private String keymr;

	public DirectoryBlock() {
	}

	public Header getHeader() {
		return header;
	}

	public List<EntryBlock> getEntryBlocks() {
		return entryBlocks;
	}

	public String getDbHash() {
		return dbHash;
	}

	public String getKeymr() {
		return keymr;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public void setEntryBlocks(List<EntryBlock> entryBlocks) {
		this.entryBlocks = entryBlocks;
	}

	public void setDbHash(String dbHash) {
		this.dbHash = dbHash;
	}

	public void setKeymr(String keymr) {
		this.keymr = keymr;
	}

	public class Header {

		@JsonProperty("version")
		Integer version;

		@JsonProperty("networkid")
		Long networkid;

		@JsonProperty("bodymr")
		String bodyKeymr;

		@JsonProperty("prevkeymr")
		String prevKeymr;

		@JsonProperty("dbheight")
		Long directoryHeight;

		@JsonProperty("timestamp")
		Long timestamp;

		@JsonProperty("prevfullhash")
		String prevFullHash;

		@JsonProperty("blockcount")
		Integer blockCount;

		@JsonProperty("chainid")
		String chainid;

		public Header() {
		}

		public Integer getVersion() {
			return version;
		}

		public Long getNetworkid() {
			return networkid;
		}

		public String getBodyKeymr() {
			return bodyKeymr;
		}

		public String getPrevKeymr() {
			return prevKeymr;
		}

		public Long getDirectoryHeight() {
			return directoryHeight;
		}

		public Long getTimestamp() {
			return timestamp;
		}

		public String getPrevFullHash() {
			return prevFullHash;
		}

		public Integer getBlockCount() {
			return blockCount;
		}

		public String getChainid() {
			return chainid;
		}

		public void setVersion(Integer version) {
			this.version = version;
		}

		public void setNetworkid(Long networkid) {
			this.networkid = networkid;
		}

		public void setBodyKeymr(String bodyKeymr) {
			this.bodyKeymr = bodyKeymr;
		}

		public void setPrevKeymr(String prevKeymr) {
			this.prevKeymr = prevKeymr;
		}

		public void setDirectoryHeight(Long directoryHeight) {
			this.directoryHeight = directoryHeight;
		}

		public void setTimestamp(Long timestamp) {
			this.timestamp = timestamp;
		}

		public void setPrevFullHash(String prevFullHash) {
			this.prevFullHash = prevFullHash;
		}

		public void setBlockCount(Integer blockCount) {
			this.blockCount = blockCount;
		}

		public void setChainid(String chainid) {
			this.chainid = chainid;
		}

	}
}
