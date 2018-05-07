package io.wancloud.factom.sdk.core.request;

import io.wancloud.factom.sdk.core.FactomRequest;
import io.wancloud.factom.sdk.core.param.ComposeChainParam;
import io.wancloud.factom.sdk.core.param.ComposeEntryParam;
import io.wancloud.factom.sdk.core.param.ImportAddressesParam;
import io.wancloud.factom.sdk.core.param.OneParam;
import io.wancloud.factom.sdk.core.param.MapParam;

public class WalletdRequests {

	public static final String NEW_TRANSACTION = "new-transaction";
	public static final String DELETE_TRANSACTION = "delete-transaction";
	public static final String TMP_TRANSACTIONS = "tmp-transactions";
	public static final String ADD_INPUT = "add-input";
	public static final String ADD_OUTPUT = "add-output";
	public static final String ADD_EC_OUTPUT = "add-ec-output";
	public static final String ADD_FEE = "add-fee";
	public static final String SUB_FEE = "sub-fee";
	public static final String SIGN_TRANSACTION = "sign-transaction";
	public static final String COMPOSE_TRANSACTION = "compose-transaction";
	public static final String COMPOSE_ENTRY = "compose-entry";
	public static final String COMPOSE_CHAIN = "compose-chain";
	public static final String ADDRESS = "address";
	public static final String ALL_ADDRESSES = "all-addresses";
	public static final String GENERATE_EC_ADDRESS = "generate-ec-address";
	public static final String GENERATE_FACTOID_ADDRESS = "generate-factoid-address";
	public static final String TRANSACTIONS = "transactions";
	public static final String WALLET_BACKUP = "wallet-backup";
	public static final String IMPORT_ADDRESSES = "import-addresses";
	public static final String IMPORT_KOINIFY = "import-koinify";
	
	private static final String PARAM_TX_NAME = "tx-name";
	private static final String PARAM_ADDRESS = "address";
	
	private WalletdRequests() {
	}

	public static FactomRequest generateEcAddress(){
		return new JacksonFactomRequest(GENERATE_EC_ADDRESS);
	}
	
	public static FactomRequest generateFactoidAddress(){
		return new JacksonFactomRequest(GENERATE_FACTOID_ADDRESS);
	}
	
	public static FactomRequest getTmpTransactions(){
		return new JacksonFactomRequest(TMP_TRANSACTIONS);
	}
	
	public static FactomRequest newTransaction(String txName){
		return new JacksonFactomRequest(NEW_TRANSACTION, new OneParam(PARAM_TX_NAME, txName));
	}
	
	public static FactomRequest deleteTransaction(String txName){
		return new JacksonFactomRequest(DELETE_TRANSACTION, new OneParam(PARAM_TX_NAME, txName));
	}
	
	public static FactomRequest addInput(String txName, String address, long ammount){
		return transferAssetRequest(ADD_INPUT,txName,address,ammount);
	}
	
	public static FactomRequest addOutput(String txName, String address, long ammount){
		return transferAssetRequest(ADD_OUTPUT,txName,address,ammount);
	}
	
	public static FactomRequest addEcOutput(String txName, String address, long ammount){
		return transferAssetRequest(ADD_EC_OUTPUT,txName,address,ammount);
	}

	public static FactomRequest addFee(String txName, String address){
		MapParam params = new MapParam()
				.with("tx-name", txName)
				.with("address", address);
		return new JacksonFactomRequest(ADD_FEE).withParams(params);	
	}
	
	public static FactomRequest subFee(String txName, String address){
		MapParam params = new MapParam()
				.with("tx-name", txName)
				.with("address", address);
		return new JacksonFactomRequest(SUB_FEE).withParams(params);	
	}
	
	public static FactomRequest signTransaction(String txName){
		return new JacksonFactomRequest(SIGN_TRANSACTION, new OneParam(PARAM_TX_NAME, txName));
	}
	
	public static FactomRequest composeTransaction(String txName){
		return new JacksonFactomRequest(COMPOSE_TRANSACTION, new OneParam(PARAM_TX_NAME, txName));
	}
	
	public static FactomRequest composeEntry(ComposeEntryParam composeParams){
		return new JacksonFactomRequest(COMPOSE_ENTRY,composeParams);
    }
	
	public static FactomRequest composeChain(ComposeChainParam composeChainParams){
		return new JacksonFactomRequest(COMPOSE_CHAIN,composeChainParams);
    }
		
    public static FactomRequest getAddress(String address){
    	return new JacksonFactomRequest(ADDRESS, new OneParam(PARAM_ADDRESS, address));
    }
    
    public static FactomRequest getAllAddresses(){
		return new JacksonFactomRequest(ALL_ADDRESSES);
    }
    
    public static FactomRequest getTransactionsByTxid(String txid){
    	return new JacksonFactomRequest(TRANSACTIONS, new OneParam("txid", txid));
    }
    
    public static FactomRequest getTransactionsByAddress(String address){
    	return new JacksonFactomRequest(TRANSACTIONS, new OneParam(PARAM_ADDRESS, address));
    }
    
	public static FactomRequest getTransactionsByRange(long start, long end){
		MapParam value = new MapParam().with("start", start).with("end", end);
		return new JacksonFactomRequest(TRANSACTIONS, new OneParam("range", value));
	}
    
    public static FactomRequest walletBackup(){
		return new JacksonFactomRequest(WALLET_BACKUP);
    }
	
    public static FactomRequest importAddresses(ImportAddressesParam importAddresses){
		return new JacksonFactomRequest(IMPORT_ADDRESSES,importAddresses);
    }
    
    public static FactomRequest importKoinify(String koinifyCrowdSaleAddress){
		return new JacksonFactomRequest(IMPORT_KOINIFY, new OneParam("words", koinifyCrowdSaleAddress));
    }
    
	private static FactomRequest transferAssetRequest(String factomMethod, String txName, String address, long ammount){
		MapParam params = new MapParam()
				.with(PARAM_TX_NAME, txName)
				.with(PARAM_ADDRESS, address)
				.with("amount", ammount);
		return new JacksonFactomRequest(factomMethod, params);	
	}
	
	
}
