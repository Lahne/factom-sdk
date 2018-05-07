package io.wancloud.factom.sdk.core.result;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdminBlock {

	@JsonProperty("header")
	private Header header;

	@JsonProperty("lookuphash")
	private String lookupHash;

	@JsonProperty("backreferencehash")
	private String backReferenceHash;

	@JsonProperty("abentries")
	private List<AdminBlockEntry> adminBlockEntries;

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public String getLookupHash() {
		return lookupHash;
	}

	public void setLookupHash(String lookupHash) {
		this.lookupHash = lookupHash;
	}

	public String getBackReferenceHash() {
		return backReferenceHash;
	}

	public void setBackReferenceHash(String backReferenceHash) {
		this.backReferenceHash = backReferenceHash;
	}

	public List<AdminBlockEntry> getAdminBlockEntries() {
		return adminBlockEntries;
	}

	public void setAdminBlockEntries(List<AdminBlockEntry> adminBlockEntries) {
		this.adminBlockEntries = adminBlockEntries;
	}

	public class Header {

		@JsonProperty("prevbackrefhash")
		String preBackRefhash;

		@JsonProperty("dbheight")
		Long dbHeight;

		@JsonProperty("headerexpansionsize")
		Integer headerExpansionSize;

		@JsonProperty("headerexpansionarea")
		String headerExpansionArea;

		@JsonProperty("messagecount")
		Integer messageCount;

		@JsonProperty("bodysize")
		Integer bodySize;

		@JsonProperty("adminchainid")
		String adminChainid;

		@JsonProperty("chainid")
		String chainid;

		public Header() {
		}

		public String getPreBackRefhash() {
			return preBackRefhash;
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

		public Integer getMessageCount() {
			return messageCount;
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

		public void setPreBackRefhash(String preBackRefhash) {
			this.preBackRefhash = preBackRefhash;
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

		public void setMessageCount(Integer messageCount) {
			this.messageCount = messageCount;
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

	public class AdminBlockEntry {

		@JsonProperty("identityadminchainid")
		private String chainid;

		@JsonProperty("prevdbsig")
		private PrevDbSignature prevDbSig;

		public String getChainid() {
			return chainid;
		}

		public void setChainid(String chainid) {
			this.chainid = chainid;
		}

		public PrevDbSignature getPrevDbSig() {
			return prevDbSig;
		}

		public void setPrevDbSig(PrevDbSignature prevDbSig) {
			this.prevDbSig = prevDbSig;
		}

	}

	public class PrevDbSignature {

		@JsonProperty("pub")
		String pub;

		@JsonProperty("sig")
		String sig;

		public PrevDbSignature() {
		}

		public String getPub() {
			return pub;
		}

		public String getSig() {
			return sig;
		}

		public void setPub(String pub) {
			this.pub = pub;
		}

		public void setSig(String sig) {
			this.sig = sig;
		}

	}

}