package io.wancloud.factom.sdk.core.param;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonValue;


public class MapParam extends FactomParam<Map<String, Object>> {

	protected static final int DEFAULT_CAPACITY = 3;

	protected Map<String, Object> actualMap;

	public MapParam() {
		this(DEFAULT_CAPACITY);
	}

	public MapParam(int initialCapacity) {
		this.actualMap = new HashMap<>(initialCapacity);
	}

	@SuppressWarnings("unchecked")
	public <T extends MapParam> T with(String paramKey, Object paramValue) {
		this.actualMap.put(paramKey, paramValue);
		return (T) this;
	}

	@JsonValue
	@Override
	public Map<String, Object> serialize() {
		return this.actualMap;
	}

}
