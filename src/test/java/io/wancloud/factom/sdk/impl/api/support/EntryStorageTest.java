package io.wancloud.factom.sdk.impl.api.support;

import java.util.Arrays;

import org.junit.Test;

import io.wancloud.factom.sdk.FactomException;
import io.wancloud.factom.sdk.TestBase;
import io.wancloud.factom.sdk.TestContext;
import io.wancloud.factom.sdk.core.model.EntryInfo;
import io.wancloud.factom.sdk.core.param.ComposeEntryParam;
import io.wancloud.factom.sdk.core.result.EntryResult;

public class EntryStorageTest extends TestBase{

	public EntryStorageTest() {
	}
	
	
	@Test
	public void testCreatEntry(){
		ComposeEntryParam entryParam = new ComposeEntryParam(TestContext.getTestChainId(),
				"EC2wMFzvcAbjJeVWRUZJEcKp8S37AhZu8ZMjyrkheyTS8WZnt9Hh", "123456你好，中国。", Arrays.asList("add-entry-test","1004"));
		EntryInfo entryInfo = factomClient.addEntry(entryParam);
		System.out.println(entryInfo);
			
		/*EntryResult result = factomServer.getEntry(entryInfo.getEntryHash());
		System.out.println(entryInfo);
		*/
		factomClient.getPendingEntries();
	
	}
	
	@Test(expected=FactomException.class)
	public void testIfCanCreateEntryByFactoidAddress(){
		EntryInfo entryInfo = factomClient.addEntry(new ComposeEntryParam(TestContext.getTestChainId(),
				TestContext.getMainFactoidAddress(), "haha", Arrays.asList("没有","123456")));
		System.out.println(entryInfo);
		
		EntryResult result = factomClient.getEntry(entryInfo.getEntryHash());
		System.out.println(result);

	}
	
	
	@Test
	public void testIfCanCreateEntryAtAnyChain(){
		EntryInfo entryInfo =  factomClient.addEntry(new ComposeEntryParam(TestContext.getTestChainId(),
				TestContext.getTestEntryCreditAddress(), "haha", Arrays.asList("can not","123456")));
		System.out.println(entryInfo);
		
		EntryResult result = factomClient.getEntry(entryInfo.getEntryHash());
		System.out.println(result);
	}
}
