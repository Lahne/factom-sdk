package io.wancloud.factom.sdk.impl.api.async;

import java.net.URI;
import java.util.concurrent.Future;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.wancloud.factom.sdk.api.async.CommandAsyncExecutor;
import io.wancloud.factom.sdk.core.FactomRequest;
import io.wancloud.factom.sdk.core.FactomResponse;
import io.wancloud.factom.sdk.util.FactomAssert;

public class DefaultCommandAsyncExecutor implements CommandAsyncExecutor{

	protected static final Logger logger = LoggerFactory.getLogger(DefaultCommandAsyncExecutor.class);

	private CloseableHttpAsyncClient httpClient;

	protected ResponseHandler<FactomResponse> handler;
	
	public DefaultCommandAsyncExecutor(CloseableHttpAsyncClient client, ResponseHandler<FactomResponse> handler) {
		FactomAssert.notNull(client, "CloseableHttpAsyncClient should not null");
		FactomAssert.notNull(handler, "FactomResponse handler should not null");
		this.httpClient = client;
		this.handler = handler;
	}

	private HttpPost buildHttpPost(URI targateURI, FactomRequest req) {
		HttpPost httpPost = new HttpPost(targateURI);
		String strEntity = req.toJson();
		HttpEntity entity = new StringEntity(strEntity, ContentType.APPLICATION_JSON);
		logger.debug("Build Request {} to {}", strEntity, targateURI);
		httpPost.setEntity(entity);
		return httpPost;
	}

	@Override
	public Future<FactomResponse> execute(URI targetURI, FactomRequest request) {
		HttpPost httpPost = buildHttpPost(targetURI, request);
		Future<HttpResponse> future = httpClient.execute(httpPost, null);
		return new FactomResponseFuture(future, handler);
	}

	@Override
	public void close() throws Exception {
		httpClient.close();
	}
	
}
