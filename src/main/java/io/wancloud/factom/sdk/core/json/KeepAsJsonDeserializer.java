package io.wancloud.factom.sdk.core.json;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class KeepAsJsonDeserializer extends StdDeserializer<String>{

	private static final long serialVersionUID = -1046903275215110540L;

	public KeepAsJsonDeserializer() {
		super(String.class);
	}

	@Override
	public String deserialize(JsonParser jp, DeserializationContext context)
			throws IOException, JsonProcessingException {
		TreeNode treeNode = jp.getCodec().readTree(jp);
		return treeNode.toString();
	}

}
