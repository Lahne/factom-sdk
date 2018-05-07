package io.wancloud.factom.sdk.core.model;

public class EntryInfo {

	private String chainid;
	
	private String txid;
	
	private String entryHash;
	

	public EntryInfo(String chainid, String txid, String entryHash) {
		super();
		this.chainid = chainid;
		this.txid = txid;
		this.entryHash = entryHash;
	}

	public String getChainid() {
		return chainid;
	}

	public void setChainid(String chainid) {
		this.chainid = chainid;
	}

	public String getTxid(){
		return txid;
	}

	public String getEntryHash() {
		return entryHash;
	}

	public void setTxid(String txid) {
		this.txid = txid;
	}

	public void setEntryHash(String entryHash) {
		this.entryHash = entryHash;
	}

	@Override
	public String toString() {
		return "EntryInfo [chainid=" + chainid + ", txid=" + txid + ", entryHash=" + entryHash + "]";
	}

}
