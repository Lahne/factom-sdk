package io.wancloud.factom.sdk;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import io.wancloud.factom.sdk.api.sync.CloseableFactomClient;
import io.wancloud.factom.sdk.core.model.EntryInfo;
import io.wancloud.factom.sdk.core.param.ComposeChainParam;
import io.wancloud.factom.sdk.core.param.ImportAddressesParam;
import io.wancloud.factom.sdk.core.result.AddressAmount;
import io.wancloud.factom.sdk.core.result.AddressResult;
import io.wancloud.factom.sdk.core.result.AddressResultList;
import io.wancloud.factom.sdk.core.result.DBlockByHeightResult;
import io.wancloud.factom.sdk.core.result.DirectoryBlockResult;
import io.wancloud.factom.sdk.core.result.EntryResult;
import io.wancloud.factom.sdk.core.result.HeightsResult;
import io.wancloud.factom.sdk.core.result.PendingEntry;
import io.wancloud.factom.sdk.core.result.TransactionResultList;
import io.wancloud.factom.sdk.util.Hex;

public class FactomClientTest extends TestBase{

	@Test
	public void testTransferFct(){
		String inputAddress1 = "FA2jK2HcLnRdS94dEcU27rF3meoJfpUcZPSinpb7AwQvPRY6RL1Q";
		String inputAddress2 = "FA2hjXMAkwJjNFn9512AMExEtUU55QB7KdcFbm3MrMNrS5SxBw81";
		
		String ecAddress1 = "EC2wMFzvcAbjJeVWRUZJEcKp8S37AhZu8ZMjyrkheyTS8WZnt9Hh";
		String ecAddress2 = "EC2xFxCzppZD1wjetxCPdLsTd3xbs8BTQKny63GnuEGDLAgXLu8z";
		
		AddressAmount input1 = new AddressAmount(inputAddress1, 4000L);
		AddressAmount input2 = new AddressAmount(inputAddress2, 5000L);
		AddressAmount output1 = new AddressAmount(ecAddress1, 1000L);
		AddressAmount output2 = new AddressAmount(ecAddress2, 8000L);
		
		factomClient.transferFactoshis(Arrays.asList(input1, input2), Arrays.asList(output1,output2), inputAddress1);
	}
	
	
	@Test
	public void testTransferFctWithTwoInputs(){
		String inputAddress1 = "FA2jK2HcLnRdS94dEcU27rF3meoJfpUcZPSinpb7AwQvPRY6RL1Q";
		String inputAddress2 = "FA2hjXMAkwJjNFn9512AMExEtUU55QB7KdcFbm3MrMNrS5SxBw81";
		
		String outputAddress1 = "FA2hjXMAkwJjNFn9512AMExEtUU55QB7KdcFbm3MrMNrS5SxBw81";
		
		AddressAmount input1 = new AddressAmount(inputAddress1, 4000L);
		AddressAmount input2 = new AddressAmount(inputAddress2, 4000L);
		AddressAmount output1 = new AddressAmount(outputAddress1, 8000L);
		
		factomClient.transferFactoshis(Arrays.asList(input1, input2), Arrays.asList(output1), inputAddress1);
	}
	
	
	@Test
	public void testSampleTransferFct(){
		String inputAddress1 = "FA2jK2HcLnRdS94dEcU27rF3meoJfpUcZPSinpb7AwQvPRY6RL1Q";
		String outputAddress1 = "FA2hjXMAkwJjNFn9512AMExEtUU55QB7KdcFbm3MrMNrS5SxBw81";
		
		factomClient.transferFactoshis(inputAddress1, outputAddress1, 80000000L,  true);
	}
	
	@Test
	public void testBuyEntryCredit(){
		String factoidAddress = "FA2jK2HcLnRdS94dEcU27rF3meoJfpUcZPSinpb7AwQvPRY6RL1Q";
		String ecAddress = "EC2xFxCzppZD1wjetxCPdLsTd3xbs8BTQKny63GnuEGDLAgXLu8z";
		
		factomClient.buyEntryCredit(factoidAddress, ecAddress, 8000L);
	}
	
	@Test
	public void testGetEntries(){
		List<PendingEntry> pendingEntries = factomClient.getPendingEntries();
		System.out.println(pendingEntries.size());
	}
	
	@Test
	public void testCreatChain(){
		String ecAddress = "EC2XLuEyXTqF6EDYAmuZQ48b17E6zAQ35EUcx7jbQ2on1oH8jXQP";
		makeSureEnoughBalanceForChainCreation(ecAddress);
		factomClient.getEntryCreditBalance(ecAddress);
		EntryInfo entryInfo = factomClient.addChain(
				new ComposeChainParam(ecAddress,
						"sha256hex-content",
						Arrays.asList("wancloud","factom","api","testing")));
		
		System.out.println(entryInfo.getChainid());
		factomClient.getEntryCreditBalance(ecAddress);
	}
	
	@Test
	public void testFactomClientUsingHttpClient(){
		CloseableFactomClient factomClient = FactomClients.custom().setFactomURI(TestContext.getFactomURI()).usingHttpClient().build();
	
		String inputAddress1 = "FA2jK2HcLnRdS94dEcU27rF3meoJfpUcZPSinpb7AwQvPRY6RL1Q";
		String inputAddress2 = "FA2hjXMAkwJjNFn9512AMExEtUU55QB7KdcFbm3MrMNrS5SxBw81";
		
		String outputAddress1 = "FA2hjXMAkwJjNFn9512AMExEtUU55QB7KdcFbm3MrMNrS5SxBw81";
		
		AddressAmount input1 = new AddressAmount(inputAddress1, 4000L);
		AddressAmount input2 = new AddressAmount(inputAddress2, 4000L);
		AddressAmount output1 = new AddressAmount(outputAddress1, 8000L);
		
		factomClient.transferFactoshis(Arrays.asList(input1, input2), Arrays.asList(output1), inputAddress1);
	}
	
	@Test
	public void testGetDBlockByHeight() throws Exception{
		Long expectedHeight = 2L;
		DBlockByHeightResult dblockByHeightResult = factomClient.getDblockByHeight(expectedHeight);
		assertEquals(expectedHeight, dblockByHeightResult.getDirectoryBlock().getHeader().getDirectoryHeight());
	}
	
	@Test
	public void testGetDirectoryBlockByKeymr() throws Exception{
		Long expectedHeight = 2L;
		DBlockByHeightResult dblockByHeightResult = factomClient.getDblockByHeight(expectedHeight);
		DirectoryBlockResult dblockByKeymrResult = factomClient.getDblockByKeymr(dblockByHeightResult.getDirectoryBlock().getKeymr());
		assertEquals(expectedHeight, dblockByKeymrResult.getHeader().getDirectoryHeight());
	}
	
	@Test
	public void testGetHeights(){
		HeightsResult res = factomClient.getHeights();
		assertNotNull(res);
	}
	
	@Test
	public void testGetEntryByHash(){
		String hash = "7fbc22692b0ae5956beb4f9aa9d6136a801d7dfba249742b6cfff0dceada39a9";
		
		EntryResult result = factomClient.getEntry(hash);
		System.out.println(result);
		System.out.println(Hex.decode(result));
	}
	
	
	
	
	@Test
	public void testGetAllAddresses() throws Exception{
		AddressResultList addresses = factomClient.getAllAddresses();
		assertTrue("addresses must not be null", !addresses.getAddresses().isEmpty());
	}

	@Test
	public void testGetAddress(){
		AddressResultList addresses = factomClient.getAllAddresses();
		assertTrue("addresses must not be null", !addresses.getAddresses().isEmpty());
		String expectedKey =addresses.getAddresses().get(0).getPublicKey();
		
		AddressResult rep = factomClient.getAddress(expectedKey);
		assertNotNull(rep);
		assertEquals(expectedKey, rep.getPublicKey());
	}
	
	@Test
	public void testGenerateEcAddress(){
		AddressResult address = factomClient.generateEcAddress();
		AddressResultList allAddresses = factomClient.getAllAddresses();
		Assert.assertTrue(allAddresses.getAddresses().contains(address));
	}
	
	
	@Test
	public void testGenerateFactoidAddress(){
		AddressResult address = factomClient.generateFactoidAddress();
		AddressResultList allAddresses = factomClient.getAllAddresses();
		Assert.assertTrue(allAddresses.getAddresses().contains(address));
	}
	
	
	@Test
	public void testRetriveTrasctionsByRange(){
		TransactionResultList list = factomClient.getTransactionsByRange(1, 100);
		assertNull(list.getTransactionList());
	}
	
	
	@Test
	public void testRetriveTrasctionsByAddress(){
		TransactionResultList list = factomClient.getTransactionsByAddress("FA2jK2HcLnRdS94dEcU27rF3meoJfpUcZPSinpb7AwQvPRY6RL1Q");
		assertNotNull(list.getTransactionList());
	}

	@Test
	public void testRetriveTrasctionsByTxid(){
		TransactionResultList list = factomClient.getTransactionsByTxid("914333898b4cd3a87091ced94d6276090a1a266e1f4b7578e2b036cfaf9aaf3e");
		assertNotNull(list.getTransactionList());
	}
	
	@Test
	public void testImportAddress(){
		String privKey = "Fs3E9gV6DXsYzf7Fqx1fVBQPQXV695eP3k5XbmHEZVRLkMdD9qCK";
		AddressResultList list = factomClient.importAddresses(new ImportAddressesParam(privKey));
		assertTrue(list.getAddresses().size() > 0);
	}
	
	@Test 
	public void testGetEntryTransactionStatus(){
		factomClient.getEntryTransactionStatus("99dad233ff463b703b0de62d56da6072fb162bd0c43adaebfe46b2f11beddc7b", "84d8452668e17f08ff8c59ce13c1b3f5a2259bfe9037283ec751cb48cef63965");
	}

	@Test 
	public void testGetFactoidTransactionStatus(){
		factomClient.getFactoidTransactionStatus("914333898b4cd3a87091ced94d6276090a1a266e1f4b7578e2b036cfaf9aaf3e");
	}
	
}
