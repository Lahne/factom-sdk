package io.wancloud.factom.sdk.core.request;

import org.junit.Test;

import io.wancloud.factom.sdk.core.FactomRequest;

public class FactomdRequestsTest {

	@Test
	public void testAckEntry(){
		FactomRequest res = FactomdRequests.ack("99dad233ff463b703b0de62d56da6072fb162bd0c43adaebfe46b2f11beddc7b", "84d8452668e17f08ff8c59ce13c1b3f5a2259bfe9037283ec751cb48cef63965");
		System.out.println(res.toJson());
	}
	
	
	
}
