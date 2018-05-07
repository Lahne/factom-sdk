package io.wancloud.factom.sdk.api.async;

import java.util.concurrent.Future;

import io.wancloud.factom.sdk.core.param.ComposeChainParam;
import io.wancloud.factom.sdk.core.param.ComposeEntryParam;
import io.wancloud.factom.sdk.core.param.ImportAddressesParam;
import io.wancloud.factom.sdk.core.result.AddressResult;
import io.wancloud.factom.sdk.core.result.AddressResultList;
import io.wancloud.factom.sdk.core.result.ComposeChainResult;
import io.wancloud.factom.sdk.core.result.ComposeEntryResult;
import io.wancloud.factom.sdk.core.result.ComposeTransactionResult;
import io.wancloud.factom.sdk.core.result.TransactionResult;
import io.wancloud.factom.sdk.core.result.TransactionResultList;
import io.wancloud.factom.sdk.core.result.WalletBackupResult;

/**
 * All commands of factom-walletd, please see <a href="https://docs.factom.com/api#factom-walletd-api">https://docs.factom.com/api#factom-walletd-api</a>
 * 
 * @author Lahne
 */
public interface WalletdAsyncCommand {

	Future<ComposeEntryResult> composeEntry(ComposeEntryParam composeEntryParams);

	Future<ComposeChainResult> composeChain(ComposeChainParam composeChainParams);

	Future<AddressResult> getAddress(String address);

	Future<AddressResult> generateEcAddress();

	Future<AddressResult> generateFactoidAddress();

	Future<AddressResultList> getAllAddresses();

	Future<TransactionResult> newTransaction(String txName);

	Future<TransactionResultList> getTmpTransactions();

	Future<TransactionResult> deleteTransaction(String txName);

	Future<TransactionResult> addInput(String txName, String address, long amount);

	Future<TransactionResult> asyncAddInput(String txName, String address, long amount);

	Future<TransactionResult> addOutput(String txName, String address, long amount);

	Future<TransactionResult> addEcOutput(String txName, String address, long amount);

	Future<TransactionResult> addFee(String txName, String address);

	Future<TransactionResult> subFee(String txName, String address);

	Future<TransactionResult> signTransaction(String txName);

	Future<ComposeTransactionResult> composeTransaction(String txName);

	Future<TransactionResultList> getTransactionsByTxid(String txid);

	Future<TransactionResultList> getTransactionsByAddress(String address);

	Future<TransactionResultList> getTransactionsByRange(long start, long end);

	Future<WalletBackupResult> walletBackup();

	Future<AddressResultList> importAddresses(ImportAddressesParam importAddressesParams);

	Future<AddressResult> importKoinify(String koinifyCrowdSaleAddress);

}