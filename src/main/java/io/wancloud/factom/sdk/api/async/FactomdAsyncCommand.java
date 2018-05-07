package io.wancloud.factom.sdk.api.async;

import java.util.List;
import java.util.concurrent.Future;

import io.wancloud.factom.sdk.core.result.ChainCommitResult;
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

/**
 * All commands of factomd, please see <a href="https://docs.factom.com/api#factomd-api">https://docs.factom.com/api#factomd-api</a>
 * 
 * @author Lahne
 */
public interface FactomdAsyncCommand {

	Future<HeightsResult> getHeights();

	Future<EntryCommitResult> commitEntry(String message);

	Future<RevealResult> revealEntry(String entry);

	Future<RevealResult> revealChain(String entry);

	Future<ChainCommitResult> commitChain(String message);

	Future<DBlockByHeightResult> getDblockByHeight(long height);

	Future<DirectoryBlockResult> getDblockByKeymr(String keymr);

	Future<EntryResult> getEntry(String entryHash);

	Future<EntryBlockResult> getEntryBlockByKeymr(String keymr);

	Future<FactoidSubmitResult> factoidSubmit(String hexTransaction);

	Future<String> getDirectoryBlockHeadKeymr();

	Future<TransactionByHashResult> getTransactionByHash(String hash);

	/**
	 * This function is deprecated, please use please use {@code getFactoidTransactionStatus(String chainid, String hash)}
	 * please see <a href="https://docs.factom.com/api#factoid-ack">https://docs.factom.com/api#factoid-ack</a>
	 * 
	 */
	@Deprecated
	Future<FactoidTransactionStatusResult> factoidAck(String txid);

	
	/**
	 * This function is deprecated, please use {@code getEntryTransactionStatus(String chainid, String hash)}
	 * please see <a href="https://docs.factom.com/api#factoid-ack">https://docs.factom.com/api#factoid-ack</a>
	 * 
	 */
	@Deprecated
	Future<EntryTransactionStatusResult> entryAck(String txid);
	
	/**
	 * This api call is used to find the status of a transaction, whether it be a reveal entry, or commit entry.
	 * please see <a href="https://docs.factom.com/api#ack">https://docs.factom.com/api#ack</a>
	 * 
	 * @param chainid
	 * @param hash
	 * @return The future of EntryTransactionStatusResult
	 */
	Future<EntryTransactionStatusResult> getEntryTransactionStatus(String chainid, String hash);
	
	/**
	 * This api call is used to find the status of a factoid transaction.
	 * please see <a href="https://docs.factom.com/api#ack">https://docs.factom.com/api#ack</a>
	 * @param txid
	 * @return The future of FactoidTransactionStatusResult
	 */
	Future<FactoidTransactionStatusResult> getFactoidTransactionStatus(String txid);
	
	Future<List<PendingTransaction>> getPendingTransactionsByAddress(String address);

	Future<List<PendingEntry>> getPendingEntries();

	Future<Long> getEntryCreditBalance(String address);

	Future<Long> getFactoidBalance(String address);

	Future<Long> getEntryCreditRate();

}