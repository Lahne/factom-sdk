package io.wancloud.factom.sdk.api.sync;

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
public interface WalletdCommand {

	ComposeEntryResult composeEntry(ComposeEntryParam composeEntryParams);

	ComposeChainResult composeChain(ComposeChainParam composeChainParams);

	AddressResult getAddress(String address);

	AddressResult generateEcAddress();

	AddressResult generateFactoidAddress();

	AddressResultList getAllAddresses();

	TransactionResult newTransaction(String txName);

	TransactionResultList getTmpTransactions();

	TransactionResult deleteTransaction(String txName);

	TransactionResult addInput(String txName, String address, long amount);

	TransactionResult addOutput(String txName, String address, long amount);

	TransactionResult addEcOutput(String txName, String address, long amount);

	TransactionResult signTransaction(String txName);

	TransactionResult addFee(String txName, String address);

	TransactionResult subFee(String txName, String address);

	ComposeTransactionResult composeTransaction(String txName);

	TransactionResultList getTransactionsByTxid(String txid);

	TransactionResultList getTransactionsByAddress(String address);

	TransactionResultList getTransactionsByRange(long start, long end);

	WalletBackupResult walletBackup();

	AddressResultList importAddresses(ImportAddressesParam importAddressesParams);

	AddressResult importKoinify(String koinifyCrowdSaleAddress);

}