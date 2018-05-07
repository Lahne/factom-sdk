package io.wancloud.factom.sdk.core.request;

import io.wancloud.factom.sdk.core.FactomRequest;
import io.wancloud.factom.sdk.core.param.OneParam;
import io.wancloud.factom.sdk.core.param.MapParam;


public class FactomdRequests {
	
	public static final String COMMIT_ENTRY = "commit-entry";
	public static final String REVEAL_ENTRY = "reveal-entry";
	public static final String COMMIT_CHAIN = "commit-chain";
	public static final String REVEAL_CHAIN = "reveal-chain";
	public static final String ENTRY = "entry";
	public static final String HEIGHTS = "heights";
	public static final String DIRECTORY_BLOCK = "directory-block";
	public static final String DIRECTORY_BLOCK_HEAD = "directory-block-head";
	public static final String DBLOCK_BY_HEIGHT = "dblock-by-height";
	public static final String ABLOCK_BY_HEIGHT = "ablock-by-height";
	public static final String ECBLOCK_BY_HEIGHT = "ecblock-by-height";
	public static final String FBLOCK_BY_HEIGHT = "fblock-by-height";
	public static final String ENTRY_BLOCK = "fblock-by-height";
	public static final String ENTRY_ACK = "entry-ack";
	public static final String FACTOID_ACK = "factoid-ack";
	public static final String ACK = "ack";
	public static final String TRANSACTION = "transaction";
	public static final String PENDING_TRANSACTIONS = "pending-transactions";
	public static final String PENDING_ENTRIES = "pending-entries";
	public static final String ENTRY_CREDIT_BALANCE = "entry-credit-balance";
	public static final String FACTOID_BALANCE =  "factoid-balance";
	public static final String ENTRY_CREDIT_RATE =  "entry-credit-rate";
	public static final String FACTOID_SUBMIT =  "factoid-submit";
	
	private static final String PARAM_ENTRY = ENTRY;
	private static final String PARAM_HEIGHT = "height";
	private static final String PARAM_TXID = "TxID";
	private static final String PARAM_ADDRESS = "address";
	private static final String PARAM_MESSAGE = "message";
	private static final String PARAM_HASH = "hash";
	private static final String PARAM_TRANSACTION = "transaction";
	private static final String PARAM_CHAINID = "chainid";
	
	private FactomdRequests() {
	}

	public static FactomRequest getHeights(){
		return new JacksonFactomRequest(HEIGHTS);
	}
	
	public static FactomRequest getDirectoryBlock(String keymr){
		return new JacksonFactomRequest(DIRECTORY_BLOCK,new OneParam("keymr", keymr));
	}
	
	public static FactomRequest getDirectoryBlockHead(){
		return new JacksonFactomRequest(DIRECTORY_BLOCK_HEAD);
	}
	
	public static FactomRequest getDBlockByHeight(long height){
		return new JacksonFactomRequest(DBLOCK_BY_HEIGHT, new OneParam(PARAM_HEIGHT, height));
	}
	
	@Deprecated
	public static FactomRequest factoidAck(String txid){
		return new JacksonFactomRequest(FACTOID_ACK, new OneParam(PARAM_TXID, txid));
	}
	
	@Deprecated
	public static FactomRequest entryAck(String txid){
		return new JacksonFactomRequest(ENTRY_ACK, new OneParam(PARAM_TXID, txid));
	}
	
	public static FactomRequest ack(String chainid, String hash){
		MapParam param = new MapParam().with(PARAM_HASH, hash).with(PARAM_CHAINID, chainid);
		return new JacksonFactomRequest(ACK, param);
	}
	
	public static FactomRequest ackFactoid(String txid){
		MapParam param= new MapParam().with(PARAM_HASH, txid).with(PARAM_CHAINID, "f");
		return new JacksonFactomRequest(ACK, param);
	}
	
	public static FactomRequest getPendingTrasactions(String address){
		return new JacksonFactomRequest(PENDING_TRANSACTIONS, new OneParam(PARAM_ADDRESS, address));
	}
	
	public static FactomRequest getPendingEntries(){
		return new JacksonFactomRequest(PENDING_ENTRIES);
	}
	
	public static FactomRequest getEntryCreditBalance(String address){
		return new JacksonFactomRequest(ENTRY_CREDIT_BALANCE, new OneParam(PARAM_ADDRESS, address));
	}
	
	public static FactomRequest getFactoidBalance(String address){
		return new JacksonFactomRequest(FACTOID_BALANCE, new OneParam(PARAM_ADDRESS, address));
	}
	
	public static FactomRequest factoidSubmit(String hexTransaction){
		return new JacksonFactomRequest(FACTOID_SUBMIT, new OneParam(PARAM_TRANSACTION, hexTransaction));
	}
	
	public static FactomRequest getEntryCreditRate(){
		return new JacksonFactomRequest(ENTRY_CREDIT_RATE);
	}
	
	public static FactomRequest getAdminBlockByHeight(long height){
		return new JacksonFactomRequest(ABLOCK_BY_HEIGHT, new OneParam(PARAM_HEIGHT, height));
	}
	
	public static FactomRequest getEcBlockByHeight(long height){
		return new JacksonFactomRequest(ECBLOCK_BY_HEIGHT, new OneParam(PARAM_HEIGHT, height));
	}
	
	public static FactomRequest getFactoidBockByHeight(long height){
		return new JacksonFactomRequest(FBLOCK_BY_HEIGHT, new OneParam(PARAM_HEIGHT, height));
	}
	
	public static FactomRequest commitEntry(String message){
		return new JacksonFactomRequest(COMMIT_ENTRY, new OneParam(PARAM_MESSAGE, message));
	}
	
	public static FactomRequest revealEntry(String entry){
		return new JacksonFactomRequest(REVEAL_ENTRY, new OneParam(PARAM_ENTRY, entry));
	}
	
	public static FactomRequest commitChain(String message){
		return new JacksonFactomRequest(COMMIT_CHAIN, new OneParam(PARAM_MESSAGE, message));
	}
	
	public static FactomRequest revealChain(String entry){
		return new JacksonFactomRequest(REVEAL_CHAIN, new OneParam(PARAM_ENTRY, entry));
	}
	public static FactomRequest getEntryBlockByKeymr(String keymr){
		return new JacksonFactomRequest(ENTRY_BLOCK, new OneParam("KeyMR", keymr));
	}
	public static FactomRequest getEntry(String entryHash){
		return new JacksonFactomRequest(ENTRY, new OneParam(PARAM_HASH, entryHash));
	}
	
	public static FactomRequest getTransaction(String hash){
		return new JacksonFactomRequest(TRANSACTION, new OneParam(PARAM_HASH, hash));
	}
}
