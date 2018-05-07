package io.wancloud.factom.sdk.impl.api.sync;

import java.io.IOException;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.wancloud.factom.sdk.FactomException;
import io.wancloud.factom.sdk.api.sync.CommandExecutor;
import io.wancloud.factom.sdk.core.FactomRequest;
import io.wancloud.factom.sdk.core.FactomResponse;
import io.wancloud.factom.sdk.core.FactomResponseHandler;
import io.wancloud.factom.sdk.util.FactomAssert;

public class DefaultCommandExecutor implements CommandExecutor, AutoCloseable {

	protected static final Logger logger = LoggerFactory.getLogger(DefaultCommandExecutor.class);

	private CloseableHttpClient httpClient;

	protected ResponseHandler<FactomResponse> handler;

	public DefaultCommandExecutor() {
		this(HttpClients.createDefault());
	}

	public DefaultCommandExecutor(CloseableHttpClient httpClient) {
		this(httpClient, new FactomResponseHandler());
	}

	public DefaultCommandExecutor(CloseableHttpClient httpClient, ResponseHandler<FactomResponse> handler) {
		FactomAssert.notNull(httpClient, "Httpclient should not be null");
		FactomAssert.notNull(handler, "FactomResponse handler should not be null");
		this.httpClient = httpClient;
		this.handler = handler;
	}

	@Override
	public FactomResponse execute(URI targetURI, FactomRequest request) {
		HttpPost httpPost = buildHttpPost(targetURI, request);
		FactomResponse response = postRequest(httpPost);
		return response;
	}

	private FactomResponse postRequest(HttpPost httpPost) {
		FactomResponse factomResponse = null;
		try {
			HttpResponse response = httpClient.execute(httpPost);
			factomResponse = handler.handleResponse(response);
		} catch (IOException e) {
			throw new FactomException(e);
		}
		return factomResponse;
	}

	private HttpPost buildHttpPost(URI targetURI, FactomRequest request) {
		String stringEntity = request.toJson();
		HttpEntity entity = new StringEntity(stringEntity, ContentType.APPLICATION_JSON);
		logger.debug("Build request : {} to {}", stringEntity, targetURI);

		HttpPost httpPost = new HttpPost(targetURI);
		httpPost.setEntity(entity);
		return httpPost;
	}

	@Override
	public void close() throws IOException {
		httpClient.close();
	}

}
