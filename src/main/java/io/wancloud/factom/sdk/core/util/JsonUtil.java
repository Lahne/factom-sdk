package io.wancloud.factom.sdk.core.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.wancloud.factom.sdk.FactomException;

public class JsonUtil {

	/**
	 * ObjectMapper is thread-safe, so we use an static instance
	 */
	private static final ObjectMapper mapper = new ObjectMapper();
	
	private JsonUtil(){
	}
	
	public static <T> T deserialize(String content, Class<T> type) throws FactomException{
		T result = null;
		try {
			result = mapper.readValue(content, type);
		} catch (IOException e) {
			throw new FactomException(e);
		}
		return result;
	}
	
	public static <T> T deserialize(String content, TypeReference<T> typeRefer) throws FactomException{
		T result = null;
		try {
			result = mapper.readValue(content, typeRefer);
		} catch (IOException e) {
			throw new FactomException(e);
		}
		return result;
	}
	
	public static JsonNode deserializeToJsonNode(String content) throws FactomException{
		JsonNode node = null;
		try {
			node = mapper.readValue(content, JsonNode.class);
		} catch (IOException e) {
			throw new FactomException(e);
		}
		return node;
	}
	
	public static String serializeAsString(Object value) throws JsonProcessingException {
		return mapper.writeValueAsString(value);
	}
	
}
