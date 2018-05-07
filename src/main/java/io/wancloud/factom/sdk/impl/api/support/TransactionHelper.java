package io.wancloud.factom.sdk.impl.api.support;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

import io.wancloud.factom.sdk.api.sync.FactomCommand;
import io.wancloud.factom.sdk.core.result.ComposeTransactionResult;
import io.wancloud.factom.sdk.core.result.TransactionResult;

public class TransactionHelper extends AbstractTransactionHelper {

	private FactomCommand factomCommand;

	public TransactionHelper(String txName, FactomCommand factomCommand) {
		super(txName);
		this.factomCommand = factomCommand;
	}

	@Override
	protected void begin() {
		factomCommand.newTransaction(txName);
	}

	@Override
	protected void addFee(String address) {
		factomCommand.addFee(txName, address);

	}

	@Override
	protected void subFee(String address) {
		factomCommand.subFee(txName, address);
	}

	private ExecutorService getCommonPool() {
		return ForkJoinPool.commonPool();
	}

	@Override
	protected Future<TransactionResult> addInput(String address, long amount) {
		return getCommonPool().submit((Callable<TransactionResult>) () -> {
			return factomCommand.addInput(txName, address, amount);
		});
	}

	@Override
	protected Future<TransactionResult> addOutput(String address, long amount) {
		return getCommonPool().submit((Callable<TransactionResult>) () -> {
			return factomCommand.addOutput(txName, address, amount);
		});
	}

	@Override
	protected Future<TransactionResult> addEcOutput(String address, long amount) {
		return getCommonPool().submit((Callable<TransactionResult>) () -> {
			return factomCommand.addEcOutput(txName, address, amount);
		});
	}

	@Override
	protected String submit(String hexTranaction) {
		return factomCommand.factoidSubmit(hexTranaction).getTxid();
	}

	@Override
	protected void sign() {
		factomCommand.signTransaction(txName);
	}

	@Override
	protected ComposeTransactionResult compose() {
		return factomCommand.composeTransaction(txName);
	}

	@Override
	protected void delete() {
		factomCommand.deleteTransaction(txName);
	}

}
