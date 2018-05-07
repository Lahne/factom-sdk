package io.wancloud.factom.sdk.impl.api.sync;

import java.util.Collection;

import io.wancloud.factom.sdk.ClientContext;
import io.wancloud.factom.sdk.api.sync.CloseableFactomClient;
import io.wancloud.factom.sdk.core.model.EntryInfo;
import io.wancloud.factom.sdk.core.param.ComposeChainParam;
import io.wancloud.factom.sdk.core.param.ComposeEntryParam;
import io.wancloud.factom.sdk.core.result.AddressAmount;
import io.wancloud.factom.sdk.impl.api.support.AbstractTransactionHelper;
import io.wancloud.factom.sdk.impl.api.support.ChainCreator;
import io.wancloud.factom.sdk.impl.api.support.EntryStorage;
import io.wancloud.factom.sdk.util.FactomAssert;

public abstract class AbstractCloseableFactomClient extends CloseableFactomClient {

	protected ClientContext clientContext;
	
	public AbstractCloseableFactomClient(ClientContext clientContext) {
		FactomAssert.notNull(clientContext, "ClientContext should not be null");
		this.clientContext = clientContext;
	}

	@Override
	public EntryInfo addChain(ComposeChainParam chainParams) {
		ChainCreator chainCreator = new ChainCreator(this, chainParams);
		chainCreator.setSleepMillSecondsBeforeRevealChain(clientContext.getSleepMillSecondsBeforeRevealChain());
		return chainCreator.create();
	}

	@Override
	public String buyEntryCredit(String factoidAddress, String ecAddress, long factoshisAmount) {
		return transferFactoshis(factoidAddress, ecAddress, factoshisAmount, true);
	}

	@Override
	public String transferFactoshis(Collection<AddressAmount> inputs, Collection<AddressAmount> outputs, String feePaymentAddress) {
		return createTransactionHelper().transferFctoshis(inputs, outputs, feePaymentAddress);
	}

	@Override
	public String transferFactoshis(String inputAddress, String outputAddress, long amount, boolean isFeePayByInput) {
		return createTransactionHelper().transferFctoshis(inputAddress, outputAddress, amount, isFeePayByInput);
	}

	@Override
	public EntryInfo addEntry(ComposeEntryParam entryParams) {
		EntryStorage entryStorage = new EntryStorage(this, entryParams);
		entryStorage.setSleepMillSecondsBeforeRevealEntry(clientContext.getSleepMillSecondsBeforeRevealEntry());
		return entryStorage.store();
	}

	protected abstract AbstractTransactionHelper createTransactionHelper();
	
}