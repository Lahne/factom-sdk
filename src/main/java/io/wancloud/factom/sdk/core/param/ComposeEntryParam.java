package io.wancloud.factom.sdk.core.param;

import java.util.List;
import java.util.StringJoiner;

import org.apache.http.util.Args;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonValue;


public class ComposeEntryParam extends ComposeChainParam{

	protected final String chainId;

	public ComposeEntryParam(String chainId, String entryCreditAddress, String content) {
		this(chainId, entryCreditAddress, content, null);
	}

	public ComposeEntryParam(String chainId, String entryCreditAddress, String content, List<String> externalIds) {
		super(entryCreditAddress, content, externalIds);
		Args.notBlank(chainId, "chainId");
		this.chainId = chainId;
	}
	
	public String getChainId() {
		return chainId;
	}
	
	@JsonValue
	@JsonRawValue
	@Override
	public String serialize() {
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"entry\": {")
		  .append("\"chainid\":\"" + chainId +"\",");
		
		if (externalIds != null && !externalIds.isEmpty()){
			sb.append("\"extids\":");
			StringJoiner joiner = new StringJoiner(",", "[", "]");
			for (String id: externalIds){
				joiner.add("\""+ id +"\"" );
			}
			sb.append(joiner.toString()+",");
		}
		
		sb.append("\"content\":\"" + content +"\"")
		  .append("},")
		  .append("\"ecpub\":\"" + entryCreditAddress +"\"")
	      .append("}");
		return sb.toString();
	}
	
}
