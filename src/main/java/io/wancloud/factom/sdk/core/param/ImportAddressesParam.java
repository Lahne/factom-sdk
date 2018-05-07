package io.wancloud.factom.sdk.core.param;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonValue;

public class ImportAddressesParam extends FactomParam<String>{

	private List<String> secrets;
	
	public ImportAddressesParam(String... secrets) {
		this(Arrays.asList(secrets));
	}
	
	public ImportAddressesParam(List<String> secrets) {
		this.secrets = secrets;
	}

	public ImportAddressesParam() {
	}

	public List<String> getSecrets() {
		return secrets;
	}

	public void setSecrets(List<String> secrets) {
		this.secrets = secrets;
	}

	
	@JsonValue
	@JsonRawValue
	@Override
	public String serialize() {
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"addresses\": ");
		
		if (secrets == null){
			sb.append("null");
		}else{
			StringJoiner joiner = new StringJoiner(",", "[", "]");
			for (String secret: secrets){
				joiner.add("{\"secret\":\""+ secret +"\"}" );
			}
			sb.append(joiner.toString());
		}
		sb.append("}");
		return sb.toString();
	}
	
}
