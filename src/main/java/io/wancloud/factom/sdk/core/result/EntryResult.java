package io.wancloud.factom.sdk.core.result;

import java.util.List;
import java.util.StringJoiner;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class EntryResult {

	private final String chainid;
	
	private final List<String> extids;
	
	private final String content;

	@JsonCreator
	public EntryResult(@JsonProperty("chainid") String chainid, @JsonProperty("extids") List<String> extids, 
			@JsonProperty("content") String content) {
		super();
		this.chainid = chainid;
		this.extids = extids;
		this.content = content;
	}

	public String getChainid() {
		return chainid;
	}

	public List<String> getExtids() {
		return extids;
	}

	public String getContent() {
		return content;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"chainid\":\"" + chainid +"\",");
		
		if (extids != null && !extids.isEmpty()){
			sb.append("\"extids\":");
			StringJoiner joiner = new StringJoiner(",", "[", "]");
			for (String id: extids){
				joiner.add("\""+ id +"\"" );
			}
			sb.append(joiner.toString()+",");
		}
		
		sb.append("\"content\":\"" + content +"\"")
		  .append("}");
		
		return sb.toString();
	
	}
	
}
