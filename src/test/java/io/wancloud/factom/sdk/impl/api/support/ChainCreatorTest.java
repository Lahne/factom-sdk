package io.wancloud.factom.sdk.impl.api.support;

import java.util.Arrays;

import org.junit.Test;

import io.wancloud.factom.sdk.TestBase;
import io.wancloud.factom.sdk.core.model.EntryInfo;
import io.wancloud.factom.sdk.core.param.ComposeChainParam;

public class ChainCreatorTest extends TestBase{

	public ChainCreatorTest() {
	}


	@Test
	public void testCreatChain(){
		String ecAddress = "EC2wMFzvcAbjJeVWRUZJEcKp8S37AhZu8ZMjyrkheyTS8WZnt9Hh";
		makeSureEnoughBalanceForChainCreation(ecAddress);
		factomClient.getEntryCreditBalance(ecAddress);
		EntryInfo entryInfo = factomClient.addChain(
				new ComposeChainParam(ecAddress,
						"sha256hex-content",
						Arrays.asList("wancloud","factom","api","testing","101")));
		
		System.out.println(entryInfo.getChainid());
		factomClient.getEntryCreditBalance(ecAddress);
	}
	
	
	@Test
	public void testCreatChainWithSameParams(){
		String ecAddress = "EC2PWuCf2v7ax9TM8wwpgerQ55uhDn4YpByRj4nzwgzSJiyTLkqT";
		ComposeChainParam param= new ComposeChainParam(ecAddress, "create two times", Arrays.asList("wancloud","testing"));
		
		makeSureEnoughBalanceForChainCreation(ecAddress);
		System.out.println("balance is "+ factomClient.getEntryCreditBalance(ecAddress));
		EntryInfo entryInfo = factomClient.addChain(param);
		System.out.println(entryInfo);
		makeSureEnoughBalanceForChainCreation(ecAddress);
		entryInfo = factomClient.addChain(param);
		System.out.println("balance is "+factomClient.getEntryCreditBalance(ecAddress));
		System.out.println(entryInfo);
		
	}
	
	
	
}
