package io.wancloud.factom.sdk.api.async;

import java.util.Collection;
import java.util.concurrent.Future;

import io.wancloud.factom.sdk.core.model.EntryInfo;
import io.wancloud.factom.sdk.core.param.ComposeChainParam;
import io.wancloud.factom.sdk.core.param.ComposeEntryParam;
import io.wancloud.factom.sdk.core.result.AddressAmount;

public interface ExternalAsyncCommand {

	Future<EntryInfo> addChain(ComposeChainParam chainParams);

	Future<String> buyEntryCredit(String factoidAddress, String ecAddress, long factoshisAmount);

	Future<String> transferFactoshis(Collection<AddressAmount> inputs, Collection<AddressAmount> outputs,
			String feePaymentAddress);

	Future<String> transferFactoshis(String inputAddress, String outputAddress, long amount, boolean isFeePayByInput);

	Future<EntryInfo> addEntry(ComposeEntryParam entryParams);

}
