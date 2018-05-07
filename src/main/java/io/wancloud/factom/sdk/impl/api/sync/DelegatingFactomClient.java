package io.wancloud.factom.sdk.impl.api.sync;

import static io.wancloud.factom.sdk.impl.api.support.FutureUtil.safeGet;

import java.util.List;

import io.wancloud.factom.sdk.ClientContext;
import io.wancloud.factom.sdk.api.async.FactomAsyncCommand;
import io.wancloud.factom.sdk.core.param.ComposeChainParam;
import io.wancloud.factom.sdk.core.param.ComposeEntryParam;
import io.wancloud.factom.sdk.core.param.ImportAddressesParam;
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
import io.wancloud.factom.sdk.impl.api.async.DefaultFactomAsyncClient;
import io.wancloud.factom.sdk.impl.api.support.AbstractTransactionHelper;
import io.wancloud.factom.sdk.impl.api.support.AsyncTransactionHelper;
import io.wancloud.factom.sdk.util.RandomString;

public class DelegatingFactomClient extends AbstractCloseableFactomClient {

	private FactomAsyncCommand factomAsyncCommand;

	public DelegatingFactomClient(FactomAsyncCommand asyncCommand, ClientContext clientContext) {
		super(clientContext);
		this.factomAsyncCommand = asyncCommand;
	}

	@Override
	public ComposeEntryResult composeEntry(ComposeEntryParam composeEntryParams) {
		return safeGet(factomAsyncCommand.composeEntry(composeEntryParams));
	}

	@Override
	public HeightsResult getHeights() {
		return safeGet(factomAsyncCommand.getHeights());
	}

	@Override
	public ComposeChainResult composeChain(ComposeChainParam composeChainParams) {
		return safeGet(factomAsyncCommand.composeChain(composeChainParams));
	}

	@Override
	public EntryCommitResult commitEntry(String message) {
		return safeGet(factomAsyncCommand.commitEntry(message));
	}

	@Override
	public RevealResult revealEntry(String entry) {
		return safeGet(factomAsyncCommand.revealEntry(entry));
	}

	@Override
	public AddressResult getAddress(String address) {
		return safeGet(factomAsyncCommand.getAddress(address));
	}

	@Override
	public RevealResult revealChain(String entry) {
		return safeGet(factomAsyncCommand.revealChain(entry));
	}

	@Override
	public AddressResult generateEcAddress() {
		return safeGet(factomAsyncCommand.generateEcAddress());
	}

	@Override
	public ChainCommitResult commitChain(String message) {
		return safeGet(factomAsyncCommand.commitChain(message));
	}

	@Override
	public AddressResult generateFactoidAddress() {
		return safeGet(factomAsyncCommand.generateFactoidAddress());
	}

	@Override
	public DBlockByHeightResult getDblockByHeight(long height) {
		return safeGet(factomAsyncCommand.getDblockByHeight(height));
	}

	@Override
	public AddressResultList getAllAddresses() {
		return safeGet(factomAsyncCommand.getAllAddresses());
	}

	@Override
	public TransactionResult newTransaction(String txName) {
		return safeGet(factomAsyncCommand.newTransaction(txName));
	}

	@Override
	public DirectoryBlockResult getDblockByKeymr(String keymr) {
		return safeGet(factomAsyncCommand.getDblockByKeymr(keymr));
	}

	@Override
	public TransactionResultList getTmpTransactions() {
		return safeGet(factomAsyncCommand.getTmpTransactions());
	}

	@Override
	public EntryResult getEntry(String entryHash) {
		return safeGet(factomAsyncCommand.getEntry(entryHash));
	}

	@Override
	public TransactionResult deleteTransaction(String txName) {
		return safeGet(factomAsyncCommand.deleteTransaction(txName));
	}

	@Override
	public EntryBlockResult getEntryBlockByKeymr(String keymr) {
		return safeGet(factomAsyncCommand.getEntryBlockByKeymr(keymr));
	}

	@Override
	public TransactionResult addInput(String txName, String address, long amount) {
		return safeGet(factomAsyncCommand.addInput(txName, address, amount));
	}

	@Override
	public FactoidSubmitResult factoidSubmit(String hexTransaction) {
		return safeGet(factomAsyncCommand.factoidSubmit(hexTransaction));
	}

	@Override
	public String getDirectoryBlockHeadKeymr() {
		return safeGet(factomAsyncCommand.getDirectoryBlockHeadKeymr());
	}

	@Override
	public TransactionResult addOutput(String txName, String address, long amount) {
		return safeGet(factomAsyncCommand.addOutput(txName, address, amount));
	}

	@Override
	public TransactionByHashResult getTransactionByHash(String hash) {
		return safeGet(factomAsyncCommand.getTransactionByHash(hash));
	}

	@Override
	public TransactionResult addEcOutput(String txName, String address, long amount) {
		return safeGet(factomAsyncCommand.addEcOutput(txName, address, amount));
	}

	@Override
	public FactoidTransactionStatusResult factoidAck(String txid) {
		return safeGet(factomAsyncCommand.factoidAck(txid));
	}

	@Override
	public EntryTransactionStatusResult entryAck(String txid) {
		return safeGet(factomAsyncCommand.entryAck(txid));
	}

	@Override
	public TransactionResult signTransaction(String txName) {
		return safeGet(factomAsyncCommand.signTransaction(txName));
	}

	@Override
	public List<PendingTransaction> getPendingTransactionsByAddress(String address) {
		return safeGet(factomAsyncCommand.getPendingTransactionsByAddress(address));
	}

	@Override
	public TransactionResult addFee(String txName, String address) {
		return safeGet(factomAsyncCommand.addFee(txName, address));
	}

	@Override
	public List<PendingEntry> getPendingEntries() {
		return safeGet(factomAsyncCommand.getPendingEntries());
	}

	@Override
	public TransactionResult subFee(String txName, String address) {
		return safeGet(factomAsyncCommand.subFee(txName, address));
	}

	@Override
	public long getEntryCreditBalance(String address) {
		return safeGet(factomAsyncCommand.getEntryCreditBalance(address));
	}

	@Override
	public ComposeTransactionResult composeTransaction(String txName) {
		return safeGet(factomAsyncCommand.composeTransaction(txName));
	}

	@Override
	public long getFactoidBalance(String address) {
		return safeGet(factomAsyncCommand.getFactoidBalance(address));
	}

	@Override
	public long getEntryCreditRate() {
		return safeGet(factomAsyncCommand.getEntryCreditRate());
	}

	@Override
	public TransactionResultList getTransactionsByTxid(String txid) {
		return safeGet(factomAsyncCommand.getTransactionsByTxid(txid));
	}

	@Override
	public TransactionResultList getTransactionsByAddress(String address) {
		return safeGet(factomAsyncCommand.getTransactionsByAddress(address));
	}

	@Override
	public TransactionResultList getTransactionsByRange(long start, long end) {
		return safeGet(factomAsyncCommand.getTransactionsByRange(start, end));
	}

	@Override
	public WalletBackupResult walletBackup() {
		return safeGet(factomAsyncCommand.walletBackup());
	}

	@Override
	public AddressResultList importAddresses(ImportAddressesParam importAddressesParams) {
		return safeGet(factomAsyncCommand.importAddresses(importAddressesParams));
	}

	@Override
	public AddressResult importKoinify(String koinifyCrowdSaleAddress) {
		return safeGet(factomAsyncCommand.importKoinify(koinifyCrowdSaleAddress));
	}

	@Override
	public void close() throws Exception {
		if (factomAsyncCommand instanceof DefaultFactomAsyncClient){
			((DefaultFactomAsyncClient)factomAsyncCommand).close();
		}
	}

	@Override
	protected AbstractTransactionHelper createTransactionHelper() {
		String txName = RandomString.randomString32();
		return new AsyncTransactionHelper(txName, factomAsyncCommand);
	}

	@Override
	public EntryTransactionStatusResult getEntryTransactionStatus(String chainid, String hash) {
		return safeGet(factomAsyncCommand.getEntryTransactionStatus(chainid, hash));
	}
	
	@Override
	public FactoidTransactionStatusResult getFactoidTransactionStatus(String txid) {
		return safeGet(factomAsyncCommand.getFactoidTransactionStatus(txid));
	}
	
}
