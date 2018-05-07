package io.wancloud.factom.sdk.core.request;

import org.apache.http.util.Args;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.wancloud.factom.sdk.core.FactomRequest;
import io.wancloud.factom.sdk.core.param.FactomParam;
import io.wancloud.factom.sdk.core.util.JsonUtil;

@JsonInclude(value=Include.NON_NULL)
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
public class JacksonFactomRequest extends FactomRequest{

	@JsonProperty("jsonrpc")
	protected String jsonrpc = "2.0";
	
	@JsonProperty("id")
	protected int id = 0;
	
	@JsonProperty("method")
	protected String factomMethod;
	
	@JsonProperty("params")
	protected FactomParam<?> factomParams;
	
	public JacksonFactomRequest(String factomMethod) {
		Args.notNull(factomMethod, "factomMethod");
		this.factomMethod = factomMethod;
	}
	
	public JacksonFactomRequest(String factomMethod, FactomParam<?> factomParams) {
		Args.notNull(factomMethod, "factomMethod");
		this.factomMethod = factomMethod;
		this.factomParams = factomParams;
	}
	
	public JacksonFactomRequest withMethod(String method){
		this.factomMethod = method;
		return this;
	}
	
	public JacksonFactomRequest withParams(FactomParam<?> params){
		this.factomParams = params;
		return this;
	}
	
	public String getFactomMethod(){
		return this.factomMethod;
	}
	
	public FactomParam<?> getFactomParam(){
		return this.factomParams;
	}
	
	public String toJson(){
		try{
			return JsonUtil.serializeAsString(this);
		}catch (Exception e) {
			throw new FactomRequestException("Building factom request error",e);
		}
	}

	public String getJsonrpc() {
		return jsonrpc;
	}

	public void setJsonrpc(String jsonrpc) {
		this.jsonrpc = jsonrpc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFactomMethod(String factomMethod) {
		this.factomMethod = factomMethod;
	}

	public void setFactomParam(FactomParam<?> factomParam) {
		this.factomParams = factomParam;
	}

}
