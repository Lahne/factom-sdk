package io.wancloud.factom.sdk.api.sync;

import java.net.URI;

import io.wancloud.factom.sdk.core.FactomRequest;
import io.wancloud.factom.sdk.core.FactomResponse;

public interface CommandExecutor extends AutoCloseable{
	
	FactomResponse execute(URI target, FactomRequest request);

}
