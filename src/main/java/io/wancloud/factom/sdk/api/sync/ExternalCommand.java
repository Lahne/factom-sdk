package io.wancloud.factom.sdk.api.sync;

import java.util.Collection;

import io.wancloud.factom.sdk.core.model.EntryInfo;
import io.wancloud.factom.sdk.core.param.ComposeChainParam;
import io.wancloud.factom.sdk.core.param.ComposeEntryParam;
import io.wancloud.factom.sdk.core.result.AddressAmount;

public interface ExternalCommand {

	EntryInfo addChain(ComposeChainParam chainParams);

	String buyEntryCredit(String factoidAddress, String ecAddress, long factoshisAmount);

	String transferFactoshis(Collection<AddressAmount> inputs, Collection<AddressAmount> outputs,
			String feePaymentAddress);

	String transferFactoshis(String inputAddress, String outputAddress, long amount, boolean isFeePayByInput);

	EntryInfo addEntry(ComposeEntryParam entryParams);
}
