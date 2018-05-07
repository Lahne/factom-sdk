package io.wancloud.factom.sdk.core.result;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressResult {

	private final String publicKey;
	
	private final String secret;

	@JsonCreator
	public AddressResult(@JsonProperty("public") String publicKey, @JsonProperty("secret") String secret) {
		this.publicKey = publicKey;
		this.secret = secret;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public String getSecret() {
		return secret;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((publicKey == null) ? 0 : publicKey.hashCode());
		result = prime * result + ((secret == null) ? 0 : secret.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddressResult other = (AddressResult) obj;
		if (publicKey == null) {
			if (other.publicKey != null)
				return false;
		} else if (!publicKey.equals(other.publicKey))
			return false;
		if (secret == null) {
			if (other.secret != null)
				return false;
		} else if (!secret.equals(other.secret))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AddressResponse [public=" + publicKey + ", secret=" + secret + "]";
	}


}
