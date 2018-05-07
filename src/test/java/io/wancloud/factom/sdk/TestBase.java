package io.wancloud.factom.sdk;

import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

import org.junit.Rule;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import io.wancloud.factom.sdk.api.sync.FactomClient;
import io.wancloud.factom.sdk.api.sync.FactomCommand;
import io.wancloud.factom.sdk.support.FluentWait;

public class TestBase {
	
	
	public static final long MIN_EC_BALANCE_IN_ADDRESS = 15; 
	
	public static final long EC_AMOUNT_PER_ONE_TRANSFER = 20; 
	
	public static final long CREAT_CHAIN_MIN_EC_AMOUNT = 30;
	
	@Rule
	public TestRule rule = RuleChain.outerRule(new TraceMethod());
	
	protected static final FactomClient factomClient = FactomClients.create(TestContext.getFactomURI());
	
	class TraceMethod implements TestRule{
		@Override
		public Statement apply(final Statement stat, Description d) {
			return new Statement() {
				@Override
				public void evaluate() throws Throwable {
					System.out.println("..... Start " + d.getDisplayName() + " .....");
					stat.evaluate();
					System.out.println("..... Finish " + d.getDisplayName() + " .....");
				}
			};
		}
		
	}
	
	protected void makeSureEnoughBalanceInEcAddress(String ecAddress){
		if (factomClient.getEntryCreditBalance(ecAddress) < MIN_EC_BALANCE_IN_ADDRESS){
			//transfer entry credits to account
			buyEentryCredits(TestContext.getMainFactoidAddress(), ecAddress, EC_AMOUNT_PER_ONE_TRANSFER);
		}
	}
	
	protected void makeSureEnoughBalanceForChainCreation(final String ecAddress){
		if (factomClient.getEntryCreditBalance(ecAddress) >= CREAT_CHAIN_MIN_EC_AMOUNT){
			return;
		}
		
		//transfer entry credits to account
		buyEentryCredits(TestContext.getMainFactoidAddress(), ecAddress, CREAT_CHAIN_MIN_EC_AMOUNT);

		//make sure the balance is ok
		new FluentWait<FactomCommand>(factomClient)
		.withTimeOut(20, TimeUnit.SECONDS)
		.until((Predicate<FactomCommand>) commands -> {
			long balance = commands.getEntryCreditBalance(ecAddress);
			return balance >= CREAT_CHAIN_MIN_EC_AMOUNT;
		});
	}
	
	
	public String buyEentryCredits(String inputAddress, String ecOutputAddress, long ecAmount) {
		long factoshisAmount = factomClient.getEntryCreditRate() * ecAmount;
		String txId = factomClient.buyEntryCredit(inputAddress, ecOutputAddress, factoshisAmount);
		return txId;
	}
}
