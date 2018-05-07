package io.wancloud.factom.sdk.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.wancloud.factom.sdk.core.json.KeepAsJsonDeserializer;

public class ErrorMessage {

	@JsonProperty("code")
	private String code;
	
	@JsonProperty("message")
	private String message;

	@JsonProperty("data")
	@JsonDeserialize(using = KeepAsJsonDeserializer.class)
	private String data;
	
	public ErrorMessage(){
	}
	
	public ErrorMessage(String errorCode,String errorMessage, String data) {
		super();
		this.code = errorCode;
		this.message = errorMessage;
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public String getData() {
		return data;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ErrorMessage [code=" + code + ", message=" + message + ", data=" + data + "]";
	}
	

}
