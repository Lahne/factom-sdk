package io.wancloud.factom.sdk.core.param;

import org.apache.http.util.Args;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class OneParam extends MapParam{

	@JsonIgnore
	private final String paramKey;
	
	public OneParam(String paramKey, Object paramValue) {
		super(1);
		Args.notNull(paramKey,"paramKey");
		this.paramKey = paramKey;
		actualMap.put(paramKey, paramValue);
	}

	public String getParamKey() {
		return paramKey;
	}

	public Object getParamValue() {
		return actualMap.get(paramKey);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public MapParam with(String paramKey, Object paramValue){
		throw new UnsupportedOperationException("paramKey and paramValue must be set by OneParam class constructor");
	}
}
