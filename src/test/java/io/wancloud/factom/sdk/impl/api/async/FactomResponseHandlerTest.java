package io.wancloud.factom.sdk.impl.api.async;

import java.util.concurrent.Future;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.junit.Assert;
import org.junit.Test;

import io.wancloud.factom.sdk.TestBase;
import io.wancloud.factom.sdk.TestContext;
import io.wancloud.factom.sdk.core.FactomResponse;
import io.wancloud.factom.sdk.core.FactomResponseHandler;
import io.wancloud.factom.sdk.core.request.WalletdRequests;
import io.wancloud.factom.sdk.impl.api.async.FactomResponseFuture;

public class FactomResponseHandlerTest extends TestBase{

	
	@Test
	public void testGetTwoTimes() throws Exception{
		CloseableHttpAsyncClient asyncClient = HttpAsyncClients.createDefault();
		asyncClient.start();
		
		HttpPost post = new HttpPost(TestContext.getWalletdURL());
		post.setEntity(new StringEntity(WalletdRequests.getAllAddresses().toJson(), ContentType.APPLICATION_JSON));
		
		Future<HttpResponse> httpRespFuture = asyncClient.execute(post, null);
		
		FactomResponseFuture factomRespFuture = new FactomResponseFuture(httpRespFuture, new FactomResponseHandler());
		
		FactomResponse factomResp= factomRespFuture.get();
		
		Assert.assertTrue(factomRespFuture.isProcessed());
		
		Assert.assertTrue(factomResp == factomRespFuture.get());

		asyncClient.close();
	}
	
	
}
