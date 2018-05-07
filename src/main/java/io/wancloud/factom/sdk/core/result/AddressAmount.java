package io.wancloud.factom.sdk.core.result;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressAmount {

	@JsonProperty("address")
	private String address;

	@JsonProperty("amount")
	private Long amount;

	public AddressAmount() {
	}

	public AddressAmount(String address, Long amount) {
		super();
		this.address = address;
		this.amount = amount;
	}

	public String getAddress() {
		return address;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "AddressAmount [address=" + address + ", amount=" + amount + "]";
	}

}