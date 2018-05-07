package io.wancloud.factom.sdk;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Properties;

public class TestContext {

	public final static String WALLETD_URL = "WALLETD_URL";
	
	public final static String FACTOMD_URL = "FACTOMD_URL";
	
	public final static String MAIN_FACTOID_ADDRESS = "MAIN_FACTOID_ADDRESS";
	
	private static Properties prop = new Properties();
	
	public static final String TEST_CONTEXTS_PROP_FILE = "testcontext.properties";
	
	static{
		try{
			TestContext.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(TEST_CONTEXTS_PROP_FILE));
		}catch(Exception e){
			TestContext.load(TestContext.class.getResourceAsStream(TEST_CONTEXTS_PROP_FILE));
		}
	}
	
	private TestContext() {
	}
	
	public static void load(InputStream propInputStream){
		prop = new Properties();
		try {
			prop.load(propInputStream);
		} catch (IOException e) {
			reThrow(e.getMessage(), e);
		}
	}
	
	private static void reThrow(String message, Throwable t){
		throw new RuntimeException(message, t);
	}
	
	public static String get(String key){
		return prop.getProperty(key);
	}

	public static String getMainFactoidAddress(){
		return get(MAIN_FACTOID_ADDRESS);
	}
	
	public static FactomURI getFactomURI(){
		return FactomURI.create(getFactomdURL(), getWalletdURL());
	}
	
	public static URI getFactomdURL(){
		return URI.create(get(FACTOMD_URL));
	}
	
	public static URI getWalletdURL(){
		return URI.create(get(WALLETD_URL));
	}
	
	public static String getTestChainId(){
		return get("CHAIN_ID");
	}
	
	public static String getTestEntryCreditAddress(){
		return get("ENTRY_CREDIT_ADDRESS");
	}
}
