package io.wancloud.factom.sdk.core.result;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EntryTransactionStatusResult {

	@JsonProperty("committxid")
	private String commitTxid;

	@JsonProperty("entryhash")
	private String entryHash;

	@JsonProperty("commitdata")
	private CommitData cmmitData;

	@JsonProperty("entrydata")
	private EntryData entryData;

	public EntryTransactionStatusResult() {
	}

	public String getCommitTxid() {
		return commitTxid;
	}

	public String getEntryHash() {
		return entryHash;
	}

	public CommitData getCmmitData() {
		return cmmitData;
	}

	public EntryData getEntryData() {
		return entryData;
	}

	public void setCommitTxid(String commitTxid) {
		this.commitTxid = commitTxid;
	}

	public void setEntryHash(String entryHash) {
		this.entryHash = entryHash;
	}

	public void setCmmitData(CommitData cmmitData) {
		this.cmmitData = cmmitData;
	}

	public void setEntryData(EntryData entryData) {
		this.entryData = entryData;
	}

	public class CommitData {
		@JsonProperty("transactiondate")
		String transactionDate;

		@JsonProperty("transactiondatestring")
		String transactionDateString;

		@JsonProperty("blockdate")
		Long blockDate;

		@JsonProperty("blockdatestring")
		String blockDateString;

		@JsonProperty("status")
		String status;

		public CommitData() {
			super();
		}

		public String getTransactionDate() {
			return transactionDate;
		}

		public String getTransactionDateString() {
			return transactionDateString;
		}

		public Long getBlockDate() {
			return blockDate;
		}

		public String getBlockDateString() {
			return blockDateString;
		}

		public String getStatus() {
			return status;
		}

		public void setTransactionDate(String transactionDate) {
			this.transactionDate = transactionDate;
		}

		public void setTransactionDateString(String transactionDateString) {
			this.transactionDateString = transactionDateString;
		}

		public void setBlockDate(Long blockDate) {
			this.blockDate = blockDate;
		}

		public void setBlockDateString(String blockDateString) {
			this.blockDateString = blockDateString;
		}

		public void setStatus(String status) {
			this.status = status;
		}

	}

	public class EntryData {
		@JsonProperty("blockdate")
		Long blockDate;

		@JsonProperty("blockdatestring")
		String blockDateString;

		@JsonProperty("status")
		String status;

		public EntryData() {
			super();
		}

		public Long getBlockDate() {
			return blockDate;
		}

		public String getBlockDateString() {
			return blockDateString;
		}

		public String getStatus() {
			return status;
		}

		public void setBlockDate(Long blockDate) {
			this.blockDate = blockDate;
		}

		public void setBlockDateString(String blockDateString) {
			this.blockDateString = blockDateString;
		}

		public void setStatus(String status) {
			this.status = status;
		}

	}

}
