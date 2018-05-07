package io.wancloud.factom.sdk.core.param;

import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonValue;

public class ComposeChainParam extends FactomParam<String>{

	protected List<String> externalIds;
	
	protected String content; 
	
	protected final String entryCreditAddress;
	
	public ComposeChainParam(String entryCreditAddress, String content) {
		this.content = content;
		this.entryCreditAddress = entryCreditAddress;
	}

	public ComposeChainParam(String entryCreditAddress, String content, List<String> externalIds) {
		this.entryCreditAddress = entryCreditAddress;
		this.content = content;
		this.externalIds = externalIds;
	}

	
	public List<String> getExternalIds() {
		return externalIds;
	}

	public void setExternalIds(List<String> externalIds) {
		this.externalIds = externalIds;
	}

	public void addExternalId(String extId){
		if (this.externalIds == null)
			this.externalIds = new LinkedList<>();
		this.externalIds.add(extId);
	}
	
	public String getContent() {
		return content;
	}

	public String getEntryCreditAddress() {
		return entryCreditAddress;
	}


	@JsonValue
	@JsonRawValue
	@Override
	public String serialize() {
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"chain\": {")
		  	.append("\"firstentry\": {");
		
		if (externalIds != null && !externalIds.isEmpty()){
			sb.append("\"extids\":");
			StringJoiner joiner = new StringJoiner(",", "[", "]");
			for (String id: externalIds){
				joiner.add("\""+ id +"\"" );
			}
			sb.append(joiner.toString()+",");
		}
		
		sb.append("\"content\":\"" + content +"\"")
		  .append("}},")
		  .append("\"ecpub\":\"" + entryCreditAddress +"\"")
	      .append("}");
		return sb.toString();
	}
}
