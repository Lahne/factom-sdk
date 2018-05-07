package io.wancloud.factom.sdk.core.result;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class EntryCreditBlock {

	@JsonProperty("header")
	private Header header;

	@JsonProperty("body")
	private Body body;

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public class Body {

		@JsonProperty("entries")
		private List<EntryCreditBlockEntry> entries;

		public Body() {
		}

		public List<EntryCreditBlockEntry> getEntries() {
			return entries;
		}

		public void setEntries(List<EntryCreditBlockEntry> entries) {
			this.entries = entries;
		}

	}

	public class Header {

		@JsonProperty("bodyhash")
		String bodyHash;

		@JsonProperty("prevheaderhash")
		String prevHeaderHash;

		@JsonProperty("prevfullhash")
		String prevFullHash;

		@JsonProperty("dbheight")
		Long dbHeight;

		@JsonProperty("headerexpansionsize")
		Integer headerExpansionSize;

		@JsonProperty("headerexpansionarea")
		String headerExpansionArea;

		@JsonProperty("objectcount")
		Integer objectCount;

		@JsonProperty("bodysize")
		Integer bodySize;

		@JsonProperty("ecchainid")
		String adminChainid;

		@JsonProperty("chainid")
		String chainid;

		public Header() {
		}

		public Long getDbHeight() {
			return dbHeight;
		}

		public Integer getHeaderExpansionSize() {
			return headerExpansionSize;
		}

		public String getHeaderExpansionArea() {
			return headerExpansionArea;
		}

		public String getBodyHash() {
			return bodyHash;
		}

		public String getPrevHeaderHash() {
			return prevHeaderHash;
		}

		public String getPrevFullHash() {
			return prevFullHash;
		}

		public Integer getObjectCount() {
			return objectCount;
		}

		public Integer getBodySize() {
			return bodySize;
		}

		public String getAdminChainid() {
			return adminChainid;
		}

		public String getChainid() {
			return chainid;
		}

		public void setBodyHash(String bodyHash) {
			this.bodyHash = bodyHash;
		}

		public void setPrevHeaderHash(String prevHeaderHash) {
			this.prevHeaderHash = prevHeaderHash;
		}

		public void setPrevFullHash(String prevFullHash) {
			this.prevFullHash = prevFullHash;
		}

		public void setDbHeight(Long dbHeight) {
			this.dbHeight = dbHeight;
		}

		public void setHeaderExpansionSize(Integer headerExpansionSize) {
			this.headerExpansionSize = headerExpansionSize;
		}

		public void setHeaderExpansionArea(String headerExpansionArea) {
			this.headerExpansionArea = headerExpansionArea;
		}

		public void setObjectCount(Integer objectCount) {
			this.objectCount = objectCount;
		}

		public void setBodySize(Integer bodySize) {
			this.bodySize = bodySize;
		}

		public void setAdminChainid(String adminChainid) {
			this.adminChainid = adminChainid;
		}

		public void setChainid(String chainid) {
			this.chainid = chainid;
		}

	}

	public class EntryCreditBlockEntry {

		@JsonProperty("number")
		private Integer minuteNumber;

		@JsonProperty("Version")
		private Integer version;

		@JsonProperty("MilliTime")
		private List<Byte> milliTime;

		@JsonProperty("EntryHash")
		private String entryHash;

		@JsonProperty("Credits")
		private Integer credits;

		@JsonProperty("ECPubKey")
		private List<Byte> entryCreditPublicKey;

		@JsonProperty("Sig")
		private List<Byte> signature;

		public boolean isMinuteNumber() {
			return minuteNumber != null;
		}

		public Integer getMinuteNumber() {
			return minuteNumber;
		}

		public Integer getVersion() {
			return version;
		}

		public List<Byte> getMilliTime() {
			return milliTime;
		}

		public String getEntryHash() {
			return entryHash;
		}

		public Integer getCredits() {
			return credits;
		}

		public List<Byte> getEntryCreditPublicKey() {
			return entryCreditPublicKey;
		}

		public List<Byte> getSignature() {
			return signature;
		}

		public void setMinuteNumber(Integer minuteNumber) {
			this.minuteNumber = minuteNumber;
		}

		public void setVersion(Integer version) {
			this.version = version;
		}

		public void setMilliTime(List<Byte> milliTime) {
			this.milliTime = milliTime;
		}

		public void setEntryHash(String entryHash) {
			this.entryHash = entryHash;
		}

		public void setCredits(Integer credits) {
			this.credits = credits;
		}

		public void setEntryCreditPublicKey(List<Byte> entryCreditPublicKey) {
			this.entryCreditPublicKey = entryCreditPublicKey;
		}

		public void setSignature(List<Byte> signature) {
			this.signature = signature;
		}

	}
}
