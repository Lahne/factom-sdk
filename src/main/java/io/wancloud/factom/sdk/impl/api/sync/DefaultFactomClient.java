package io.wancloud.factom.sdk.impl.api.sync;

import java.net.URI;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;

import io.wancloud.factom.sdk.ClientContext;
import io.wancloud.factom.sdk.FactomException;
import io.wancloud.factom.sdk.FactomURI;
import io.wancloud.factom.sdk.api.sync.CommandExecutor;
import io.wancloud.factom.sdk.core.ErrorMessage;
import io.wancloud.factom.sdk.core.FactomRequest;
import io.wancloud.factom.sdk.core.FactomResponse;
import io.wancloud.factom.sdk.core.param.ComposeChainParam;
import io.wancloud.factom.sdk.core.param.ComposeEntryParam;
import io.wancloud.factom.sdk.core.param.ImportAddressesParam;
import io.wancloud.factom.sdk.core.request.FactomdRequests;
import io.wancloud.factom.sdk.core.request.WalletdRequests;
import io.wancloud.factom.sdk.core.result.AddressResult;
import io.wancloud.factom.sdk.core.result.AddressResultList;
import io.wancloud.factom.sdk.core.result.ChainCommitResult;
import io.wancloud.factom.sdk.core.result.ComposeChainResult;
import io.wancloud.factom.sdk.core.result.ComposeEntryResult;
import io.wancloud.factom.sdk.core.result.ComposeTransactionResult;
import io.wancloud.factom.sdk.core.result.DBlockByHeightResult;
import io.wancloud.factom.sdk.core.result.DirectoryBlockResult;
import io.wancloud.factom.sdk.core.result.EntryTransactionStatusResult;
import io.wancloud.factom.sdk.core.result.EntryBlockResult;
import io.wancloud.factom.sdk.core.result.EntryCommitResult;
import io.wancloud.factom.sdk.core.result.EntryResult;
import io.wancloud.factom.sdk.core.result.FactoidTransactionStatusResult;
import io.wancloud.factom.sdk.core.result.FactoidSubmitResult;
import io.wancloud.factom.sdk.core.result.HeightsResult;
import io.wancloud.factom.sdk.core.result.PendingEntry;
import io.wancloud.factom.sdk.core.result.PendingTransaction;
import io.wancloud.factom.sdk.core.result.RevealResult;
import io.wancloud.factom.sdk.core.result.TransactionByHashResult;
import io.wancloud.factom.sdk.core.result.TransactionResult;
import io.wancloud.factom.sdk.core.result.TransactionResultList;
import io.wancloud.factom.sdk.core.result.WalletBackupResult;
import io.wancloud.factom.sdk.impl.api.support.AbstractTransactionHelper;
import io.wancloud.factom.sdk.impl.api.support.TransactionHelper;
import io.wancloud.factom.sdk.util.FactomAssert;
import io.wancloud.factom.sdk.util.RandomString;

public class DefaultFactomClient extends AbstractCloseableFactomClient {

	protected FactomURI factomURI;
	
	protected CommandExecutor executor;
	
	public DefaultFactomClient(FactomURI factomURI, CommandExecutor executor, ClientContext clientContext) {
		super(clientContext);
		FactomAssert.notNull(factomURI, "FactomURI should not be null");
		FactomAssert.notNull(executor, "CommandExecutor should not be null");
		this.factomURI = factomURI;
		this.executor = executor;
	}

	@Override
	public HeightsResult getHeights() {
		FactomResponse response = executeFactomd(FactomdRequests.getHeights());
		return response.getResultAsType(HeightsResult.class);
	}

	@Override
	public EntryCommitResult commitEntry(String message) {
		FactomResponse response = executeFactomd(FactomdRequests.commitEntry(message));
		return response.getResultAsType(ChainCommitResult.class);
	}

	@Override
	public RevealResult revealEntry(String entry) {
		FactomResponse response = executeFactomd(FactomdRequests.revealEntry(entry));
		return response.getResultAsType(RevealResult.class);
	}

	@Override
	public RevealResult revealChain(String entry) {
		FactomResponse response = executeFactomd(FactomdRequests.revealChain(entry));
		return response.getResultAsType(RevealResult.class);
	}

	@Override
	public ChainCommitResult commitChain(String message) {
		FactomResponse response = executeFactomd(FactomdRequests.commitChain(message));
		return response.getResultAsType(ChainCommitResult.class);
	}

	@Override
	public DBlockByHeightResult getDblockByHeight(long height) {
		FactomResponse response = executeFactomd(FactomdRequests.getDBlockByHeight(height));
		return response.getResultAsType(DBlockByHeightResult.class);
	}

	@Override
	public DirectoryBlockResult getDblockByKeymr(String keymr) {
		FactomResponse response = executeFactomd(FactomdRequests.getDirectoryBlock(keymr));
		return response.getResultAsType(DirectoryBlockResult.class);
	}

	@Override
	public EntryResult getEntry(String entryHash) {
		FactomResponse response = executeFactomd(FactomdRequests.getEntry(entryHash));
		return response.getResultAsType(EntryResult.class);
	}

	@Override
	public EntryBlockResult getEntryBlockByKeymr(String keymr) {
		FactomResponse response = executeFactomd(FactomdRequests.getEntryBlockByKeymr(keymr));
		return response.getResultAsType(EntryBlockResult.class);
	}

	@Override
	public FactoidSubmitResult factoidSubmit(String hexTransaction) {
		FactomResponse response = executeFactomd(FactomdRequests.factoidSubmit(hexTransaction));
		return response.getResultAsType(FactoidSubmitResult.class);
	}

	@Override
	public String getDirectoryBlockHeadKeymr() {
		FactomResponse response = executeFactomd(FactomdRequests.getDirectoryBlockHead());
		JsonNode result = response.getResultAsJsonNode();
		return result.get("keymr").asText();
	}

	@Override
	public TransactionByHashResult getTransactionByHash(String hash) {
		FactomResponse response = executeFactomd(FactomdRequests.getTransaction(hash));
		return response.getResultAsType(TransactionByHashResult.class);
	}

	@Override
	public FactoidTransactionStatusResult factoidAck(String txid) {
		FactomResponse response = executeFactomd(FactomdRequests.factoidAck(txid));
		return response.getResultAsType(FactoidTransactionStatusResult.class);
	}

	@Override
	public EntryTransactionStatusResult entryAck(String txid) {
		FactomResponse response = executeFactomd(FactomdRequests.entryAck(txid));
		return response.getResultAsType(EntryTransactionStatusResult.class);
	}

	@Override
	public EntryTransactionStatusResult getEntryTransactionStatus(String chainid, String hash) {
		FactomResponse response = executeFactomd(FactomdRequests.ack(chainid, hash));
		return response.getResultAsType(EntryTransactionStatusResult.class);
	}

	@Override
	public FactoidTransactionStatusResult getFactoidTransactionStatus(String txid) {
		FactomResponse response = executeFactomd(FactomdRequests.ack("f", txid));
		return response.getResultAsType(FactoidTransactionStatusResult.class);
	}
	
	@Override
	public List<PendingTransaction> getPendingTransactionsByAddress(String address) {
		FactomResponse response = executeFactomd(FactomdRequests.getPendingTrasactions(address));
		return response.getResultAsTypeReference(new TypeReference<List<PendingTransaction>>() {
		});
	}

	@Override
	public List<PendingEntry> getPendingEntries() {
		FactomResponse response = executeFactomd(FactomdRequests.getPendingEntries());
		return response.getResultAsTypeReference(new TypeReference<List<PendingEntry>>() {
		});
	}

	@Override
	public long getEntryCreditBalance(String address) {
		FactomResponse response = executeFactomd(FactomdRequests.getEntryCreditBalance(address));
		JsonNode result = response.getResultAsJsonNode();
		return result.get("balance").asLong(-1);
	}

	@Override
	public long getFactoidBalance(String address) {
		FactomResponse response = executeFactomd(FactomdRequests.getFactoidBalance(address));
		JsonNode result = response.getResultAsJsonNode();
		return result.get("balance").asLong(-1);
	}

	@Override
	public long getEntryCreditRate() {
		FactomResponse response = executeFactomd(FactomdRequests.getEntryCreditRate());
		JsonNode result = response.getResultAsJsonNode();
		return result.get("rate").asLong(-1);
	}

	@Override
	public ComposeEntryResult composeEntry(ComposeEntryParam composeEntryParams) {
		FactomResponse response = executeWalletd(WalletdRequests.composeEntry(composeEntryParams));
		JsonNode result = response.getResultAsJsonNode();
		String commit = result.get("commit").get("params").get("message").asText();
		String reveal = result.get("reveal").get("params").get("entry").asText();
		return new ComposeEntryResult(commit, reveal);
	}

	@Override
	public ComposeChainResult composeChain(ComposeChainParam composeChainParams) {
		FactomResponse response = executeWalletd(WalletdRequests.composeChain(composeChainParams));
		JsonNode result = response.getResultAsJsonNode();
		String commit = result.get("commit").get("params").get("message").asText();
		String reveal = result.get("reveal").get("params").get("entry").asText();
		return new ComposeChainResult(commit, reveal);
	}

	@Override
	public AddressResult getAddress(String address) {
		FactomResponse response = executeWalletd(WalletdRequests.getAddress(address));
		return response.getResultAsType(AddressResult.class);
	}

	@Override
	public AddressResult generateEcAddress() {
		FactomResponse response = executeWalletd(WalletdRequests.generateEcAddress());
		return response.getResultAsType(AddressResult.class);
	}

	@Override
	public AddressResult generateFactoidAddress() {
		FactomResponse response = executeWalletd(WalletdRequests.generateFactoidAddress());
		return response.getResultAsType(AddressResult.class);
	}

	@Override
	public AddressResultList getAllAddresses() {
		FactomResponse response = executeWalletd(WalletdRequests.getAllAddresses());
		return response.getResultAsType(AddressResultList.class);
	}

	@Override
	public TransactionResult newTransaction(String txName) {
		FactomResponse response = executeWalletd(WalletdRequests.newTransaction(txName));
		return response.getResultAsType(TransactionResult.class);
	}

	@Override
	public TransactionResultList getTmpTransactions() {
		FactomResponse response = executeWalletd(WalletdRequests.getTmpTransactions());
		return response.getResultAsType(TransactionResultList.class);
	}

	@Override
	public TransactionResult deleteTransaction(String txName) {
		FactomResponse response = executeWalletd(WalletdRequests.deleteTransaction(txName));
		return response.getResultAsType(TransactionResult.class);
	}

	@Override
	public TransactionResult addInput(String txName, String address, long amount) {
		FactomResponse response = executeWalletd(WalletdRequests.addInput(txName, address, amount));
		return response.getResultAsType(TransactionResult.class);
	}

	@Override
	public TransactionResult addOutput(String txName, String address, long amount) {
		FactomResponse response = executeWalletd(WalletdRequests.addOutput(txName, address, amount));
		return response.getResultAsType(TransactionResult.class);
	}

	@Override
	public TransactionResult addEcOutput(String txName, String address, long amount) {
		FactomResponse response = executeWalletd(WalletdRequests.addEcOutput(txName, address, amount));
		return response.getResultAsType(TransactionResult.class);
	}

	@Override
	public TransactionResult signTransaction(String txName) {
		FactomResponse response = executeWalletd(WalletdRequests.signTransaction(txName));
		return response.getResultAsType(TransactionResult.class);
	}

	@Override
	public TransactionResult addFee(String txName, String address) {
		FactomResponse response = executeWalletd(WalletdRequests.addFee(txName, address));
		return response.getResultAsType(TransactionResult.class);
	}

	@Override
	public TransactionResult subFee(String txName, String address) {
		FactomResponse response = executeWalletd(WalletdRequests.subFee(txName, address));
		return response.getResultAsType(TransactionResult.class);
	}

	@Override
	public ComposeTransactionResult composeTransaction(String txName) {
		FactomResponse response = executeWalletd(WalletdRequests.composeTransaction(txName));
		JsonNode result = response.getResultAsJsonNode();
		String hexTransaction = result.get("params").get("transaction").asText();
		return new ComposeTransactionResult(hexTransaction);
	}

	@Override
	public TransactionResultList getTransactionsByTxid(String txid) {
		FactomResponse response = executeWalletd(WalletdRequests.getTransactionsByTxid(txid));
		return response.getResultAsType(TransactionResultList.class);
	}

	@Override
	public TransactionResultList getTransactionsByAddress(String address) {
		FactomResponse response = executeWalletd(WalletdRequests.getTransactionsByAddress(address));
		return response.getResultAsType(TransactionResultList.class);
	}

	@Override
	public TransactionResultList getTransactionsByRange(long start, long end) {
		FactomResponse response = executeWalletd(WalletdRequests.getTransactionsByRange(start, end));
		return response.getResultAsType(TransactionResultList.class);
	}

	@Override
	public WalletBackupResult walletBackup() {
		FactomResponse response = executeWalletd(WalletdRequests.walletBackup());
		return response.getResultAsType(WalletBackupResult.class);
	}

	@Override
	public AddressResultList importAddresses(ImportAddressesParam importAddressesParams) {
		FactomResponse response = executeWalletd(WalletdRequests.importAddresses(importAddressesParams));
		return response.getResultAsType(AddressResultList.class);
	}

	@Override
	public AddressResult importKoinify(String koinifyCrowdSaleAddress) {
		FactomResponse response = executeWalletd(WalletdRequests.importKoinify(koinifyCrowdSaleAddress));
		return response.getResultAsType(AddressResult.class);
	}

	protected final FactomResponse executeFactomd(FactomRequest factomRequest) {
		return execute(factomURI.getFactomdURI(), factomRequest);
	}

	protected final FactomResponse executeWalletd(FactomRequest factomRequest) {
		return execute(factomURI.getWalletdURI(), factomRequest);
	}

	protected FactomResponse execute(URI target, FactomRequest request) {
		FactomResponse response = executor.execute(target, request);
		if (response.hasError()) {
			ErrorMessage errorMessage = response.getErrorMessage();
			throw new FactomException(errorMessage.toString());
		}
		return response;
	}
	
	@Override
	protected AbstractTransactionHelper createTransactionHelper() {
		String txName = RandomString.randomString32();
		return new TransactionHelper(txName, this);
	}
	
	@Override
	public void close() throws Exception {
		executor.close();
	}

	
}
