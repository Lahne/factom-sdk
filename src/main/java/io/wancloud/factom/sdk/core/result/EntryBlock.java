package io.wancloud.factom.sdk.core.result;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EntryBlock{
	
	@JsonProperty("chainid")
	String chainid;
	
	@JsonProperty("keymr")
	String keymr;

	public void setChainid(String chainid) {
		this.chainid = chainid;
	}

	public void setKeymr(String keymr) {
		this.keymr = keymr;
	}

	public String getChainid() {
		return chainid;
	}

	public String getKeymr() {
		return keymr;
	}
	
}