package io.wancloud.factom.sdk.api.async;

import java.net.URI;
import java.util.concurrent.Future;

import io.wancloud.factom.sdk.core.FactomRequest;
import io.wancloud.factom.sdk.core.FactomResponse;

public interface CommandAsyncExecutor extends AutoCloseable {
	
	Future<FactomResponse> execute(URI target, FactomRequest request);
	
}
