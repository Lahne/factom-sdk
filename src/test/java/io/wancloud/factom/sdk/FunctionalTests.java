package io.wancloud.factom.sdk;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.junit.Ignore;
import org.junit.Test;

import io.wancloud.factom.sdk.api.sync.CommandExecutor;
import io.wancloud.factom.sdk.api.sync.FactomdCommand;
import io.wancloud.factom.sdk.api.sync.WalletdCommand;
import io.wancloud.factom.sdk.core.FactomResponse;
import io.wancloud.factom.sdk.core.param.ComposeChainParam;
import io.wancloud.factom.sdk.core.request.JacksonFactomRequest;
import io.wancloud.factom.sdk.core.request.WalletdRequests;
import io.wancloud.factom.sdk.core.result.ComposeTransactionResult;
import io.wancloud.factom.sdk.core.result.FactoidSubmitResult;
import io.wancloud.factom.sdk.core.result.TransactionResult;
import io.wancloud.factom.sdk.core.result.TransactionResultList;

public class FunctionalTests extends TestBase{
	
	protected WalletdCommand walletd;
	protected FactomdCommand factomd;
	protected CommandExecutor executor;

	protected String inputAddress = "FA2jK2HcLnRdS94dEcU27rF3meoJfpUcZPSinpb7AwQvPRY6RL1Q";
	
	public FunctionalTests() {
	}

	
	@Test
	public void testTransferToOneEcAddress(){
		String txName = "tx-name";
		String ecAddress = "EC2wMFzvcAbjJeVWRUZJEcKp8S37AhZu8ZMjyrkheyTS8WZnt9Hh";
		long ecAmount = 20;
		long rate = factomd.getEntryCreditRate();
		System.out.println("exchange rate is "+ rate);
		
		walletd.newTransaction(txName);
		long factoidAmount = rate * ecAmount;
		walletd.addEcOutput(txName, ecAddress, factoidAmount);
		walletd.addInput(txName, inputAddress, factoidAmount);
		walletd.addFee(txName, inputAddress);
		walletd.signTransaction(txName);
		ComposeTransactionResult compose = walletd.composeTransaction(txName);
		FactoidSubmitResult submitResult = factomd.factoidSubmit(compose.getHexTransaction());
		System.out.println(submitResult.getTxid());
	}
	
	@Test
	@Ignore
	public void testCreateChainWithUnenoughEntryCredit(){
		String address = "EC2wMFzvcAbjJeVWRUZJEcKp8S37AhZu8ZMjyrkheyTS8WZnt9Hh";

		ComposeChainParam chainParams = new ComposeChainParam(address, "1234abcd");
		chainParams.addExternalId("abcd");
		chainParams.addExternalId("1234");
		
		FactomResponse resp = executor.execute(TestContext.getWalletdURL(),WalletdRequests.composeChain(chainParams));
		assertTrue(resp.hasError());
		assertEquals("Internal error", resp.getErrorMessage().getMessage());
		assertEquals("Not enough Entry Credits", resp.getErrorMessage().getData());
	}
	
	@Test(expected=FactomException.class)
	public void testBadRequest(){
		FactomResponse response = executor.execute(TestContext.getWalletdURL(), new JacksonFactomRequest("bad-request"));
		assertTrue(response.hasError());
		assertEquals("Method not found", response.getErrorMessage().getMessage());
	}
	
	//@Test
	public void testTmpTransactions(){
		String txName = "testTmpTransactions";
		walletd.deleteTransaction(txName);
		walletd.newTransaction(txName);
		TransactionResultList list = walletd.getTmpTransactions();
		assertTrue(list.getTransactionList()
				.stream()
				.anyMatch(tx -> txName.equals(tx.getTxName())));
		walletd.deleteTransaction(txName);
	}

	@Test(expected=FactomException.class)
	public void testDoOtherTxOperationBeforeNewTx(){
		String txName = "NotExistingTxName";
		
		FactomResponse resp = executor.execute(TestContext.getWalletdURL(), WalletdRequests.addInput(txName, inputAddress, 1000));
		assertTrue(resp.hasError());
		assertEquals("Internal error", resp.getErrorMessage().getMessage());
		assertEquals("wallet: Transaction name was not found", resp.getErrorMessage().getData());
	}
	
	//@Test
	public void testTheBehaviorWhenFactoidIsInadequate(){
		String txName = "FactoidIsInadequate";
		walletd.deleteTransaction(txName);
		factomd.getFactoidBalance(inputAddress);
		walletd.newTransaction(txName);
		long factoshis = 30000 * 100000000;
		TransactionResult result = walletd.addInput(txName, inputAddress, factoshis);
		 System.out.println(result);	
	}
	
}
