package io.wancloud.factom.sdk.impl.api.async;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

import io.wancloud.factom.sdk.ClientContext;
import io.wancloud.factom.sdk.FactomURI;
import io.wancloud.factom.sdk.api.async.CloseableFactomAsyncClient;
import io.wancloud.factom.sdk.api.async.CommandAsyncExecutor;
import io.wancloud.factom.sdk.core.model.EntryInfo;
import io.wancloud.factom.sdk.core.param.ComposeChainParam;
import io.wancloud.factom.sdk.core.param.ComposeEntryParam;
import io.wancloud.factom.sdk.core.param.ImportAddressesParam;
import io.wancloud.factom.sdk.core.result.AddressAmount;
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
import io.wancloud.factom.sdk.impl.api.support.AsyncTransactionHelper;
import io.wancloud.factom.sdk.impl.api.support.ChainCreator;
import io.wancloud.factom.sdk.impl.api.support.EntryStorage;
import io.wancloud.factom.sdk.impl.api.sync.DelegatingFactomClient;
import io.wancloud.factom.sdk.util.FactomAssert;
import io.wancloud.factom.sdk.util.RandomString;

public class DefaultFactomAsyncClient extends CloseableFactomAsyncClient {

	protected ExecutorService executorService;
	
	protected ClientContext clientContext;
	
	protected DefaultFactomAsyncCommand factomAsyncCommand;
	
	public DefaultFactomAsyncClient(FactomURI factomURI, CommandAsyncExecutor asyncExecutor, ExecutorService executorService) {
		this(factomURI, asyncExecutor, executorService, new ClientContext());
	}

	public DefaultFactomAsyncClient(FactomURI factomURI, CommandAsyncExecutor asyncExecutor,
			ExecutorService executorService, ClientContext clientContext) {
		FactomAssert.notNull(factomURI, "FactomURI should not be null");
		FactomAssert.notNull(asyncExecutor, "CommandAsyncExecutor should not be null");
		FactomAssert.notNull(executorService, "ExecutorService should not be null");
		FactomAssert.notNull(clientContext, "ClientContext should not be null");
		this.executorService = executorService;
		this.factomAsyncCommand = new DefaultFactomAsyncCommand(factomURI, asyncExecutor);
		this.clientContext = clientContext;
	}

	@Override
	public Future<EntryInfo> addChain(ComposeChainParam chainParams) {
		return executorService.submit((Callable<EntryInfo>) ()-> {
			ChainCreator chainCreator = new ChainCreator(new DelegatingFactomClient(this, clientContext), chainParams);
			chainCreator.setSleepMillSecondsBeforeRevealChain(clientContext.getSleepMillSecondsBeforeRevealChain());
			return chainCreator.create();
		});
	}
	
	protected AbstractTransactionHelper createTransactionHelper() {
		String txName = RandomString.randomString32();
		return new AsyncTransactionHelper(txName, this);
	}

	@Override
	public Future<String> buyEntryCredit(String factoidAddress, String ecAddress, long factoshisAmount) {
		return transferFactoshis(factoidAddress, ecAddress, factoshisAmount, true);
	}

	@Override
	public Future<String> transferFactoshis(Collection<AddressAmount> inputs, Collection<AddressAmount> outputs,
			String feePaymentAddress) {
		return executorService.submit((Callable<String>) ()-> {
			return createTransactionHelper().transferFctoshis(inputs, outputs, feePaymentAddress);
		});
	}

	@Override
	public Future<String> transferFactoshis(String inputAddress, String outputAddress, long factoshisAmount,
			boolean isFeePayByInput) {
		return executorService.submit((Callable<String>) ()-> {
			return createTransactionHelper().transferFctoshis(inputAddress, outputAddress, factoshisAmount, isFeePayByInput);
		});
	}

	@Override
	public Future<EntryInfo> addEntry(ComposeEntryParam entryParams) {
		return executorService.submit((Callable<EntryInfo>) ()-> {
			EntryStorage entryStorage = new EntryStorage(new DelegatingFactomClient(this, clientContext), entryParams);
			entryStorage.setSleepMillSecondsBeforeRevealEntry(clientContext.getSleepMillSecondsBeforeRevealEntry());
			return entryStorage.store();
		});
	}

	public Future<ComposeEntryResult> composeEntry(ComposeEntryParam composeEntryParams) {
		return factomAsyncCommand.composeEntry(composeEntryParams);
	}

	public Future<ComposeChainResult> composeChain(ComposeChainParam composeChainParams) {
		return factomAsyncCommand.composeChain(composeChainParams);
	}

	public Future<HeightsResult> getHeights() {
		return factomAsyncCommand.getHeights();
	}

	public Future<EntryCommitResult> commitEntry(String message) {
		return factomAsyncCommand.commitEntry(message);
	}

	public Future<AddressResult> getAddress(String address) {
		return factomAsyncCommand.getAddress(address);
	}

	public Future<RevealResult> revealEntry(String entry) {
		return factomAsyncCommand.revealEntry(entry);
	}

	public Future<AddressResult> generateEcAddress() {
		return factomAsyncCommand.generateEcAddress();
	}

	public Future<AddressResult> generateFactoidAddress() {
		return factomAsyncCommand.generateFactoidAddress();
	}

	public Future<RevealResult> revealChain(String entry) {
		return factomAsyncCommand.revealChain(entry);
	}

	public Future<AddressResultList> getAllAddresses() {
		return factomAsyncCommand.getAllAddresses();
	}

	public Future<ChainCommitResult> commitChain(String message) {
		return factomAsyncCommand.commitChain(message);
	}

	public Future<TransactionResult> newTransaction(String txName) {
		return factomAsyncCommand.newTransaction(txName);
	}

	public Future<DBlockByHeightResult> getDblockByHeight(long height) {
		return factomAsyncCommand.getDblockByHeight(height);
	}

	public Future<TransactionResultList> getTmpTransactions() {
		return factomAsyncCommand.getTmpTransactions();
	}

	public Future<DirectoryBlockResult> getDblockByKeymr(String keymr) {
		return factomAsyncCommand.getDblockByKeymr(keymr);
	}

	public Future<TransactionResult> deleteTransaction(String txName) {
		return factomAsyncCommand.deleteTransaction(txName);
	}

	public Future<EntryResult> getEntry(String entryHash) {
		return factomAsyncCommand.getEntry(entryHash);
	}

	public Future<TransactionResult> addInput(String txName, String address, long amount) {
		return factomAsyncCommand.addInput(txName, address, amount);
	}

	public Future<EntryBlockResult> getEntryBlockByKeymr(String keymr) {
		return factomAsyncCommand.getEntryBlockByKeymr(keymr);
	}

	public Future<FactoidSubmitResult> factoidSubmit(String hexTransaction) {
		return factomAsyncCommand.factoidSubmit(hexTransaction);
	}

	public Future<TransactionResult> asyncAddInput(String txName, String address, long amount) {
		return factomAsyncCommand.asyncAddInput(txName, address, amount);
	}

	public Future<String> getDirectoryBlockHeadKeymr() {
		return factomAsyncCommand.getDirectoryBlockHeadKeymr();
	}

	public Future<TransactionResult> addOutput(String txName, String address, long amount) {
		return factomAsyncCommand.addOutput(txName, address, amount);
	}

	public Future<TransactionByHashResult> getTransactionByHash(String hash) {
		return factomAsyncCommand.getTransactionByHash(hash);
	}

	public Future<TransactionResult> addEcOutput(String txName, String address, long amount) {
		return factomAsyncCommand.addEcOutput(txName, address, amount);
	}

	public Future<FactoidTransactionStatusResult> factoidAck(String txid) {
		return factomAsyncCommand.factoidAck(txid);
	}
	
	@Override
	public Future<EntryTransactionStatusResult> getEntryTransactionStatus(String chainid, String hash) {
		return factomAsyncCommand.getEntryTransactionStatus(chainid, hash);
	}
	
	@Override
	public Future<FactoidTransactionStatusResult> getFactoidTransactionStatus(String txid) {
		return factomAsyncCommand.getFactoidTransactionStatus(txid);
	}

	public Future<EntryTransactionStatusResult> entryAck(String txid) {
		return factomAsyncCommand.entryAck(txid);
	}

	public Future<TransactionResult> addFee(String txName, String address) {
		return factomAsyncCommand.addFee(txName, address);
	}

	public Future<List<PendingTransaction>> getPendingTransactionsByAddress(String address) {
		return factomAsyncCommand.getPendingTransactionsByAddress(address);
	}

	public Future<TransactionResult> subFee(String txName, String address) {
		return factomAsyncCommand.subFee(txName, address);
	}

	public Future<List<PendingEntry>> getPendingEntries() {
		return factomAsyncCommand.getPendingEntries();
	}

	public Future<TransactionResult> signTransaction(String txName) {
		return factomAsyncCommand.signTransaction(txName);
	}

	public Future<Long> getEntryCreditBalance(String address) {
		return factomAsyncCommand.getEntryCreditBalance(address);
	}

	public Future<ComposeTransactionResult> composeTransaction(String txName) {
		return factomAsyncCommand.composeTransaction(txName);
	}

	public Future<Long> getFactoidBalance(String address) {
		return factomAsyncCommand.getFactoidBalance(address);
	}

	public Future<Long> getEntryCreditRate() {
		return factomAsyncCommand.getEntryCreditRate();
	}

	public Future<TransactionResultList> getTransactionsByTxid(String txid) {
		return factomAsyncCommand.getTransactionsByTxid(txid);
	}

	public Future<TransactionResultList> getTransactionsByAddress(String address) {
		return factomAsyncCommand.getTransactionsByAddress(address);
	}

	public Future<TransactionResultList> getTransactionsByRange(long start, long end) {
		return factomAsyncCommand.getTransactionsByRange(start, end);
	}

	public Future<WalletBackupResult> walletBackup() {
		return factomAsyncCommand.walletBackup();
	}

	public Future<AddressResultList> importAddresses(ImportAddressesParam importAddressesParams) {
		return factomAsyncCommand.importAddresses(importAddressesParams);
	}

	public Future<AddressResult> importKoinify(String koinifyCrowdSaleAddress) {
		return factomAsyncCommand.importKoinify(koinifyCrowdSaleAddress);
	}

	@Override
	public void close() throws Exception {
		factomAsyncCommand.close();
		if (executorService != ForkJoinPool.commonPool()){
			executorService.shutdownNow();
		}
	}
	
	public ExecutorService getExecutorService() {
		return executorService;
	}

	public void setExecutorService(ExecutorService executorService) {
		this.executorService = executorService;
	}

	public ClientContext getClientContext() {
		return clientContext;
	}

	public void setClientContext(ClientContext clientContext) {
		this.clientContext = clientContext;
	}
	
}
