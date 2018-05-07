package io.wancloud.factom.sdk;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import org.junit.Test;

import io.wancloud.factom.sdk.api.async.CloseableFactomAsyncClient;
import io.wancloud.factom.sdk.core.result.AddressAmount;

public class FactomAsyncClientTest {

	@Test
	public void testTransferFct() throws Exception, ExecutionException{
		CloseableFactomAsyncClient factomClient = FactomAsyncClients.create(TestContext.getFactomURI());
	
		String inputAddress1 = "FA2jK2HcLnRdS94dEcU27rF3meoJfpUcZPSinpb7AwQvPRY6RL1Q";
		String inputAddress2 = "FA2hjXMAkwJjNFn9512AMExEtUU55QB7KdcFbm3MrMNrS5SxBw81";
		
		String outputAddress1 = "FA2hjXMAkwJjNFn9512AMExEtUU55QB7KdcFbm3MrMNrS5SxBw81";
		
		AddressAmount input1 = new AddressAmount(inputAddress1, 4000L);
		AddressAmount input2 = new AddressAmount(inputAddress2, 4000L);
		AddressAmount output1 = new AddressAmount(outputAddress1, 8000L);
		
		factomClient.transferFactoshis(Arrays.asList(input1, input2), Arrays.asList(output1), inputAddress1).get();
		factomClient.close();
	}
	
}
