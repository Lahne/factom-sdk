package io.wancloud.factom.sdk.impl.api.support;

import static io.wancloud.factom.sdk.impl.api.support.FutureUtil.safeGet;

import java.util.concurrent.Future;

import io.wancloud.factom.sdk.api.async.FactomAsyncCommand;
import io.wancloud.factom.sdk.core.result.ComposeTransactionResult;
import io.wancloud.factom.sdk.core.result.TransactionResult;

public class AsyncTransactionHelper extends AbstractTransactionHelper{

	FactomAsyncCommand asyncCommand;
	
	public AsyncTransactionHelper(String txName, FactomAsyncCommand asyncCommand) {
		super(txName);
		this.asyncCommand = asyncCommand;
	}
	
	@Override
	protected void begin(){
		safeGet(asyncCommand.newTransaction(txName));
	}
	
	@Override
	protected void addFee(String address){
		safeGet(asyncCommand.addFee(txName, address));
	}
	
	@Override
	protected void subFee(String address){
		safeGet(asyncCommand.addFee(txName, address));
	}
	
	@Override
	protected Future<TransactionResult> addInput(String address, long amount){
		return asyncCommand.asyncAddInput(txName, address, amount);
	}

	@Override
	protected Future<TransactionResult> addOutput(String address, long amount){
		return asyncCommand.addOutput(txName, address, amount);
	}

	@Override
	protected Future<TransactionResult> addEcOutput(String address, long amount){
		return asyncCommand.addEcOutput(txName, address, amount);
	}
	
	@Override
	protected String submit(String hexTranaction){
		return safeGet(asyncCommand.factoidSubmit(hexTranaction)).getTxid();
	}
	
	@Override
	protected void sign(){
		safeGet(asyncCommand.signTransaction(txName));
	}
	
	@Override
	protected ComposeTransactionResult compose(){
		return safeGet(asyncCommand.composeTransaction(txName));
	}
	
	@Override
	protected void delete(){
		safeGet(asyncCommand.deleteTransaction(txName));
	}
}
