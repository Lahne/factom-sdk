package io.wancloud.factom.sdk.api.sync;

import java.util.List;

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
public interface FactomdCommand {

	HeightsResult getHeights();

	EntryCommitResult commitEntry(String message);

	RevealResult revealEntry(String entry);

	RevealResult revealChain(String entry);

	ChainCommitResult commitChain(String message);

	DBlockByHeightResult getDblockByHeight(long height);

	DirectoryBlockResult getDblockByKeymr(String keymr);

	EntryResult getEntry(String entryHash);

	EntryBlockResult getEntryBlockByKeymr(String keymr);

	FactoidSubmitResult factoidSubmit(String hexTransaction);

	String getDirectoryBlockHeadKeymr();

	TransactionByHashResult getTransactionByHash(String hash);
	
	/**
	 * This function is deprecated, please use please use {@code getFactoidTransactionStatus(String chainid, String hash)}
	 * please see <a href="https://docs.factom.com/api#factoid-ack">https://docs.factom.com/api#factoid-ack</a>
	 * 
	 * @return FactoidTransactionStatusResult
	 */
	@Deprecated
	FactoidTransactionStatusResult factoidAck(String txid);

	
	/**
	 * This function is deprecated, please use {@code getEntryTransactionStatus(String chainid, String hash)}
	 * please see <a href="https://docs.factom.com/api#factoid-ack">https://docs.factom.com/api#factoid-ack</a>
	 * 
	 * @return EntryTransactionStatusResult
	 */
	@Deprecated
	EntryTransactionStatusResult entryAck(String txid);
	
	/**
	 * This api call is used to find the status of a transaction, whether it be a reveal entry, or commit entry.
	 * please see <a href="https://docs.factom.com/api#ack">https://docs.factom.com/api#ack</a>
	 * 
	 * @param chainid
	 * @param hash
	 * @return EntryTransactionStatusResult
	 */
	EntryTransactionStatusResult getEntryTransactionStatus(String chainid, String hash);
	
	/**
	 * This api call is used to find the status of a factoid transaction.
	 * please see <a href="https://docs.factom.com/api#ack">https://docs.factom.com/api#ack</a>
	 * 
	 * @param txid
	 * @return
	 */
	FactoidTransactionStatusResult getFactoidTransactionStatus(String txid);
	
	List<PendingTransaction> getPendingTransactionsByAddress(String address);

	List<PendingEntry> getPendingEntries();

	long getEntryCreditBalance(String address);

	long getFactoidBalance(String address);

	long getEntryCreditRate();

}