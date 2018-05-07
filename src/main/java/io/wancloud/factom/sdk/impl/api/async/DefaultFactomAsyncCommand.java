package io.wancloud.factom.sdk.impl.api.async;

import java.net.URI;
import java.util.List;
import java.util.concurrent.Future;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;

import io.wancloud.factom.sdk.FactomURI;
import io.wancloud.factom.sdk.api.async.CommandAsyncExecutor;
import io.wancloud.factom.sdk.api.async.FactomAsyncCommand;
import io.wancloud.factom.sdk.core.FactomRequest;
import io.wancloud.factom.sdk.core.FactomResponse;
import io.wancloud.factom.sdk.core.ResultParser;
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
import io.wancloud.factom.sdk.util.FactomAssert;

public class DefaultFactomAsyncCommand implements FactomAsyncCommand{

	protected FactomURI factomURI;
	
	protected CommandAsyncExecutor asyncExecutor;

	public DefaultFactomAsyncCommand(FactomURI factomURI, CommandAsyncExecutor asyncExecutor) {
		FactomAssert.notNull(factomURI, "FactomURI should not be null");
		FactomAssert.notNull(asyncExecutor, "CommandAsyncExecutor should not be null");
		this.factomURI = factomURI;
		this.asyncExecutor = asyncExecutor;
	}

	@Override
	public Future<HeightsResult> getHeights() {
		Future<FactomResponse> fresp = executeFactomd(FactomdRequests.getHeights());
		return wrapAsTargetClassResult(fresp, HeightsResult.class);
	}

	@Override
	public Future<EntryCommitResult> commitEntry(String message) {
		Future<FactomResponse> fresp = executeFactomd(FactomdRequests.commitEntry(message));
		return wrapAsTargetClassResult(fresp, EntryCommitResult.class);
	}

	@Override
	public Future<RevealResult> revealEntry(String entry) {
		Future<FactomResponse> fresp = executeFactomd(FactomdRequests.revealEntry(entry));
		return wrapAsTargetClassResult(fresp, RevealResult.class);
	}

	@Override
	public Future<RevealResult> revealChain(String entry) {
		Future<FactomResponse> fresp = executeFactomd(FactomdRequests.revealChain(entry));
		return wrapAsTargetClassResult(fresp, RevealResult.class);
	}

	@Override
	public Future<ChainCommitResult> commitChain(String message) {
		Future<FactomResponse> fresp = executeFactomd(FactomdRequests.commitChain(message));
		return wrapAsTargetClassResult(fresp, ChainCommitResult.class);
	}

	@Override
	public Future<DBlockByHeightResult> getDblockByHeight(long height) {
		Future<FactomResponse> fresp = executeFactomd(FactomdRequests.getDBlockByHeight(height));
		return wrapAsTargetClassResult(fresp, DBlockByHeightResult.class);
	}

	@Override
	public Future<DirectoryBlockResult> getDblockByKeymr(String keymr) {
		Future<FactomResponse> fresp = executeFactomd(FactomdRequests.getDirectoryBlock(keymr));
		return wrapAsTargetClassResult(fresp, DirectoryBlockResult.class);
	}

	@Override
	public Future<EntryResult> getEntry(String entryHash) {
		Future<FactomResponse> fresp = executeFactomd(FactomdRequests.getEntry(entryHash));
		return wrapAsTargetClassResult(fresp, EntryResult.class);
	}

	@Override
	public Future<EntryBlockResult> getEntryBlockByKeymr(String keymr) {
		Future<FactomResponse> fresp = executeFactomd(FactomdRequests.getEntryBlockByKeymr(keymr));
		return wrapAsTargetClassResult(fresp, EntryBlockResult.class);
	}

	@Override
	public Future<FactoidSubmitResult> factoidSubmit(String hexTransaction) {
		Future<FactomResponse> fresp = executeFactomd(FactomdRequests.factoidSubmit(hexTransaction));
		return wrapAsTargetClassResult(fresp, FactoidSubmitResult.class);
	}

	@Override
	public Future<String> getDirectoryBlockHeadKeymr() {
		Future<FactomResponse> fresp = executeFactomd(FactomdRequests.getDirectoryBlockHead());
		return new ResultFuture<>(fresp, (ResultParser<String>) (res) -> {
			JsonNode result = res.getResultAsJsonNode();
			return result.get("keymr").asText();
		});
	}

	@Override
	public Future<TransactionByHashResult> getTransactionByHash(String hash) {
		Future<FactomResponse> fresp = executeFactomd(FactomdRequests.getTransaction(hash));
		return wrapAsTargetClassResult(fresp, TransactionByHashResult.class);
	}

	@Override
	public Future<FactoidTransactionStatusResult> factoidAck(String txid) {
		Future<FactomResponse> fresp = executeFactomd(FactomdRequests.factoidAck(txid));
		return wrapAsTargetClassResult(fresp, FactoidTransactionStatusResult.class);
	}

	@Override
	public Future<EntryTransactionStatusResult> entryAck(String txid) {
		Future<FactomResponse> fresp = executeFactomd(FactomdRequests.entryAck(txid));
		return wrapAsTargetClassResult(fresp, EntryTransactionStatusResult.class);
	}

	@Override
	public Future<List<PendingTransaction>> getPendingTransactionsByAddress(String address) {
		Future<FactomResponse> fresp = executeFactomd(FactomdRequests.getPendingTrasactions(address));
		return new ResultFuture<>(fresp, (ResultParser<List<PendingTransaction>>) (res) -> {
			return res.getResultAsTypeReference(new TypeReference<List<PendingTransaction>>() {
			});
		});
	}

	@Override
	public Future<List<PendingEntry>> getPendingEntries() {
		Future<FactomResponse> fresp = executeFactomd(FactomdRequests.getPendingEntries());
		return new ResultFuture<>(fresp, (ResultParser<List<PendingEntry>>) (res) -> {
			return res.getResultAsTypeReference(new TypeReference<List<PendingEntry>>() {
			});
		});
	}

	@Override
	public Future<Long> getEntryCreditBalance(String address) {
		Future<FactomResponse> fresp = executeFactomd(FactomdRequests.getEntryCreditBalance(address));
		return new ResultFuture<>(fresp, (ResultParser<Long>) (res) -> {
			JsonNode result = res.getResultAsJsonNode();
			return result.get("balance").asLong(-1);
		});
	}

	@Override
	public Future<Long> getFactoidBalance(String address) {
		Future<FactomResponse> fresp = executeFactomd(FactomdRequests.getFactoidBalance(address));
		return new ResultFuture<>(fresp, (ResultParser<Long>) (res) -> {
			JsonNode result = res.getResultAsJsonNode();
			return result.get("balance").asLong(-1);
		});
	}

	@Override
	public Future<Long> getEntryCreditRate() {
		Future<FactomResponse> fresp = executeFactomd(FactomdRequests.getEntryCreditRate());
		return new ResultFuture<>(fresp, (ResultParser<Long>) (res) -> {
			JsonNode result = res.getResultAsJsonNode();
			return result.get("rate").asLong(-1);
		});
	}

	@Override
	public Future<ComposeEntryResult> composeEntry(ComposeEntryParam composeEntryParams) {
		Future<FactomResponse> fresp = executeWalletd(WalletdRequests.composeEntry(composeEntryParams));
		return new ResultFuture<>(fresp, (ResultParser<ComposeEntryResult>) (res) -> {
			JsonNode result = res.getResultAsJsonNode();
			String commit = result.get("commit").get("params").get("message").asText();
			String reveal = result.get("reveal").get("params").get("entry").asText();
			return new ComposeEntryResult(commit, reveal);
		});
	}

	@Override
	public Future<ComposeChainResult> composeChain(ComposeChainParam composeChainParams) {
		Future<FactomResponse> fresp = executeWalletd(WalletdRequests.composeChain(composeChainParams));
		return new ResultFuture<>(fresp, (ResultParser<ComposeChainResult>) (res) -> {
			JsonNode result = res.getResultAsJsonNode();
			String commit = result.get("commit").get("params").get("message").asText();
			String reveal = result.get("reveal").get("params").get("entry").asText();
			return new ComposeChainResult(commit, reveal);
		});
	
	}

	@Override
	public Future<AddressResult> getAddress(String address) {
		Future<FactomResponse> fresp = executeWalletd(WalletdRequests.getAddress(address));
		return wrapAsTargetClassResult(fresp, AddressResult.class);
	}

	@Override
	public Future<AddressResult> generateEcAddress() {
		Future<FactomResponse> fresp = executeWalletd(WalletdRequests.generateEcAddress());
		return wrapAsTargetClassResult(fresp, AddressResult.class);
	}

	@Override
	public Future<AddressResult> generateFactoidAddress() {
		Future<FactomResponse> fresp = executeWalletd(WalletdRequests.generateFactoidAddress());
		return wrapAsTargetClassResult(fresp, AddressResult.class);
	}

	@Override
	public Future<AddressResultList> getAllAddresses() {
		Future<FactomResponse> fresp = executeWalletd(WalletdRequests.getAllAddresses());
		return wrapAsTargetClassResult(fresp, AddressResultList.class);
	}

	@Override
	public Future<TransactionResult> newTransaction(String txName) {
		Future<FactomResponse> fresp = executeWalletd(WalletdRequests.newTransaction(txName));
		return wrapAsTargetClassResult(fresp, TransactionResult.class);
	}

	@Override
	public Future<TransactionResultList> getTmpTransactions() {
		Future<FactomResponse> fresp = executeWalletd(WalletdRequests.getTmpTransactions());
		return wrapAsTargetClassResult(fresp, TransactionResultList.class);
	}

	@Override
	public Future<TransactionResult> deleteTransaction(String txName) {
		Future<FactomResponse> fresp = executeWalletd(WalletdRequests.deleteTransaction(txName));
		return wrapAsTargetClassResult(fresp, TransactionResult.class);
	}

	@Override
	public Future<TransactionResult> addInput(String txName, String address, long amount) {
		Future<FactomResponse> fresp = executeWalletd(WalletdRequests.addInput(txName, address, amount));
		return wrapAsTargetClassResult(fresp, TransactionResult.class);
	}

	@Override
	public Future<TransactionResult> asyncAddInput(String txName, String address, long amount) {
		Future<FactomResponse> fresp = executeWalletd(WalletdRequests.addInput(txName, address, amount));
		return wrapAsTargetClassResult(fresp, TransactionResult.class);
	}

	@Override
	public Future<TransactionResult> addOutput(String txName, String address, long amount) {
		Future<FactomResponse> fresp = executeWalletd(WalletdRequests.addOutput(txName, address, amount));
		return wrapAsTargetClassResult(fresp, TransactionResult.class);
	}

	@Override
	public Future<TransactionResult> addEcOutput(String txName, String address, long amount) {
		Future<FactomResponse> fresp = executeWalletd(WalletdRequests.addEcOutput(txName, address, amount));
		return wrapAsTargetClassResult(fresp, TransactionResult.class);
	}

	@Override
	public Future<TransactionResult> addFee(String txName, String address) {
		Future<FactomResponse> fresp = executeWalletd(WalletdRequests.addFee(txName, address));
		return wrapAsTargetClassResult(fresp, TransactionResult.class);
	}

	@Override
	public Future<TransactionResult> subFee(String txName, String address) {
		Future<FactomResponse> fresp = executeWalletd(WalletdRequests.subFee(txName, address));
		return wrapAsTargetClassResult(fresp, TransactionResult.class);
	}

	@Override
	public Future<TransactionResult> signTransaction(String txName) {
		Future<FactomResponse> fresp = executeWalletd(WalletdRequests.signTransaction(txName));
		return wrapAsTargetClassResult(fresp, TransactionResult.class);
	}

	@Override
	public Future<ComposeTransactionResult> composeTransaction(String txName) {
		Future<FactomResponse> fresp = executeWalletd(WalletdRequests.composeTransaction(txName));
		return new ResultFuture<>(fresp, (ResultParser<ComposeTransactionResult>) (res) -> {
			JsonNode result = res.getResultAsJsonNode();
			String hexTransaction = result.get("params").get("transaction").asText();
			return new ComposeTransactionResult(hexTransaction);
		});
	}

	@Override
	public Future<TransactionResultList> getTransactionsByTxid(String txid) {
		Future<FactomResponse> fresp = executeWalletd(WalletdRequests.getTransactionsByTxid(txid));
		return wrapAsTargetClassResult(fresp, TransactionResultList.class);
	}

	@Override
	public Future<TransactionResultList> getTransactionsByAddress(String address) {
		Future<FactomResponse> fresp = executeWalletd(WalletdRequests.getTransactionsByAddress(address));
		return wrapAsTargetClassResult(fresp, TransactionResultList.class);
	}

	@Override
	public Future<TransactionResultList> getTransactionsByRange(long start, long end) {
		Future<FactomResponse> fresp = executeWalletd(WalletdRequests.getTransactionsByRange(start, end));
		return wrapAsTargetClassResult(fresp, TransactionResultList.class);
	}

	@Override
	public Future<WalletBackupResult> walletBackup() {
		Future<FactomResponse> fresp = executeWalletd(WalletdRequests.walletBackup());
		return wrapAsTargetClassResult(fresp, WalletBackupResult.class);
	}

	@Override
	public Future<AddressResultList> importAddresses(ImportAddressesParam importAddressesParams) {
		Future<FactomResponse> fresp = executeWalletd(WalletdRequests.importAddresses(importAddressesParams));
		return wrapAsTargetClassResult(fresp, AddressResultList.class);
	}

	@Override
	public Future<AddressResult> importKoinify(String koinifyCrowdSaleAddress) {
		Future<FactomResponse> fresp = executeWalletd(WalletdRequests.importKoinify(koinifyCrowdSaleAddress));
		return wrapAsTargetClassResult(fresp, AddressResult.class);
	}

	@Override
	public Future<EntryTransactionStatusResult> getEntryTransactionStatus(String chainid, String hash) {
		Future<FactomResponse> fresp = executeFactomd(FactomdRequests.ack(chainid, hash));
		return wrapAsTargetClassResult(fresp, EntryTransactionStatusResult.class);
	}

	@Override
	public Future<FactoidTransactionStatusResult> getFactoidTransactionStatus(String txid) {
		Future<FactomResponse> fresp = executeFactomd(FactomdRequests.ack("f", txid));
		return wrapAsTargetClassResult(fresp, FactoidTransactionStatusResult.class);
	}
	
	protected Future<FactomResponse> executeFactomd(FactomRequest request) {
		return execute(factomURI.getFactomdURI(), request);
	}

	protected Future<FactomResponse> executeWalletd(FactomRequest request) {
		return execute(factomURI.getWalletdURI(), request);
	}

	protected Future<FactomResponse> execute(URI targatURI, FactomRequest request) {
		return asyncExecutor.execute(targatURI, request);
	}

	protected <T> ResultFuture<T> wrapAsTargetClassResult(final Future<FactomResponse> fresp, final Class<T> targetClass) {
		return new ResultFuture<>(fresp, (ResultParser<T>) (res) -> res.getResultAsType(targetClass));
	}

	public void close() throws Exception {
		asyncExecutor.close();
	}


}