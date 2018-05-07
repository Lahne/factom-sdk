package io.wancloud.factom.sdk.support;

import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

import org.junit.Test;

import io.wancloud.factom.sdk.TestBase;
import io.wancloud.factom.sdk.api.sync.FactomCommand;

public class FluentWaitTest extends TestBase{

	public FluentWaitTest() {
	}
	
	@Test
	public void waitBalanceConfirmed(){
		new FluentWait<FactomCommand>(factomClient)
			.withTimeOut(20, TimeUnit.SECONDS)
			.until((Predicate<FactomCommand>) commands -> {
				return true;
			});
	}
	
	
	@Test(expected=TimeoutException.class)
	public void expectedTimeout(){
		new FluentWait<FactomCommand>(factomClient)
			.withTimeOut(3, TimeUnit.SECONDS)
			.until((Predicate<FactomCommand>) commands -> {
				return false;
			});
	}

}
