package io.wancloud.factom.sdk.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.wancloud.factom.sdk.core.json.KeepAsJsonDeserializer;
import io.wancloud.factom.sdk.core.util.JsonUtil;

/**
 * There are two content type of HttpResponse when do httpRequest to factom
 * federated server.<br>
 * 
 * 1.ERROR message <br>
 * 
 * <pre>
 * e.g:
 * {
 *   "jsonrpc": "2.0",
 *   "id": 0,
 *   "error": {
 *       "code": -32601,
 *       "message": "Method not found"
 *   }
 * }
 * </pre>
 * 
 * 2. normal message <br>
 * 
 * <pre>
 * e.g:
 * {
 *   "jsonrpc": "2.0",
 *   "id": 0,
 *   "result": ${An Json Object}
 * }
 * </pre>
 * 
 * so we use this common FactomResponse to represent/cover those two content
 * types.
 * 
 * @author wanglei
 */
public class FactomResponse {

	@JsonProperty("jsonrpc")
	private String jsonrpc;

	@JsonProperty("id")
	private String id;

	@JsonProperty("error")
	private ErrorMessage errorMessage;

	@JsonProperty("result")
	@JsonDeserialize(using = KeepAsJsonDeserializer.class)
	private String result;

	public FactomResponse() {
	}

	public boolean hasError() {
		return errorMessage != null;
	}

	public String getJsonrpc() {
		return jsonrpc;
	}

	public String getId() {
		return id;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

	public String getResult() {
		return result;
	}

	public void setJsonrpc(String jsonrpc) {
		this.jsonrpc = jsonrpc;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * convert string to an instance of target Type
	 * 
	 * @param targetType
	 * @return
	 */
	public <T> T getResultAsType(Class<T> targetType) {
		if (result == null)
			return null;
		return JsonUtil.deserialize(result, targetType);
	}

	public <T> T getResultAsTypeReference(TypeReference<T> targetTypeRef) {
		if (result == null)
			return null;
		return JsonUtil.deserialize(result, targetTypeRef);
	}

	public JsonNode getResultAsJsonNode() {
		return getResultAsType(JsonNode.class);
	}

}
