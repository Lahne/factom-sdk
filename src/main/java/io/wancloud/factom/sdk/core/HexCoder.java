package io.wancloud.factom.sdk.core;

import java.nio.charset.Charset;

public interface HexCoder {

	Byte[] encode(Byte[] bytes, Charset charSet);
	
	
	
}
