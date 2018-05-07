package io.wancloud.factom.sdk.core.result;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressResultList {

	private final List<AddressResult> addresses;
	
	@JsonCreator
	public AddressResultList(@JsonProperty("addresses") List<AddressResult> addresses) {
		this.addresses = addresses;
	}

	public List<AddressResult> getAddresses() {
		return addresses;
	}

	@Override
	public String toString() {
		return "AddressResultList [Addresses=" + addresses + "]";
	}

}
