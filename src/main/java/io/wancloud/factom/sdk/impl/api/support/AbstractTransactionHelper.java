package io.wancloud.factom.sdk.impl.api.support;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.wancloud.factom.sdk.core.result.AddressAmount;
import io.wancloud.factom.sdk.core.result.ComposeTransactionResult;
import io.wancloud.factom.sdk.core.result.TransactionResult;
import io.wancloud.factom.sdk.util.FactomAssert;

/**
 * @author wanglei
 */
public abstract class AbstractTransactionHelper {
	
	protected static final Logger logger = LoggerFactory.getLogger(AbstractTransactionHelper.class);
	
	protected String txName;
	
	public AbstractTransactionHelper(String txName) {
		FactomAssert.notNull(txName, "TxName should not be null");
		this.txName = txName;
	}
	
	protected abstract void begin();
	
	protected abstract void addFee(String address);
	
	protected abstract void subFee(String address);
	
	protected abstract Future<TransactionResult> addInput(String address, long amount);

	protected abstract Future<TransactionResult> addOutput(String address, long amount);

	protected abstract Future<TransactionResult> addEcOutput(String address, long amount);
	
	protected abstract String submit(String hexTranaction);
	
	protected abstract void sign();
	
	protected abstract ComposeTransactionResult compose();
	
	protected abstract void delete();
	
	public String transferFctoshis(String inputAddress, String outputAddress, long amount, boolean isFeePayByInput){
		AddressAmount input = new AddressAmount(inputAddress, amount);
		AddressAmount output = new AddressAmount(outputAddress, amount);
		if (isFeePayByInput){
			return transferFctoshis(Arrays.asList(input), Arrays.asList(output), inputAddress);
		}else {
			return transferFctoshis(Arrays.asList(input), Arrays.asList(output), outputAddress);
		}
	}

	public String transferFctoshis(Collection<AddressAmount> inputs, Collection<AddressAmount> outputs,
			String feePaymentAddress) {
		
		checkParams(inputs, outputs, feePaymentAddress);
		
		try {
			
			//new transaction at first
			logger.debug("transaction[{}] : new transaction", txName);
			begin();
			
			//send addInput addEcOutput addOutput
			logger.debug("transaction[{}] : parallel to send addInput/addEcOutput/addOutput", txName);
			parallelOperateAmountRequests(inputs,outputs);
			
			//choose use addFee or subFee
			if (isFeePaymentAddressInInputs(inputs,feePaymentAddress)){
				logger.debug("transaction[{}] : add fee on address - {}", txName, feePaymentAddress);
				addFee(feePaymentAddress);
			}else{
				logger.debug("transaction[{}] : sub fee on address - {}", txName, feePaymentAddress);
				subFee(feePaymentAddress);
			}

			//sign
			logger.debug("transaction[{}] : sign", txName);
			sign();
			
			//compose
			logger.debug("transaction[{}] : compose transaction", txName);
			ComposeTransactionResult result = compose();
			logger.debug("transaction[{}] : composed hex transaction is {}", txName, result.getHexTransaction());
			
			//submit
			logger.debug("transaction[{}] : submit the transaction", txName);
			return submit(result.getHexTransaction());
		} catch (Exception ex) {
			logger.debug(String.format("transaction[%s] : come up with exception", txName), ex);
			logger.debug("transaction[{}] : delete transaction", txName);
			delete();
			
			//wrap and rethrow the exception
			throw new TransactionException(ex);
		}
	}

	protected void checkParams(Collection<AddressAmount> inputs, Collection<AddressAmount> outputs,
			String feePaymentAddress) {
		if (inputs == null || inputs.size() == 0) {
			throw new IllegalArgumentException("Input address list is null or contains no item");
		}

		if (outputs == null || outputs.size() == 0) {
			throw new IllegalArgumentException("Output address list is null or contains no item");
		}

		if (feePaymentAddress == null) {
			throw new IllegalArgumentException("FeePaymentAddress should not be null");
		}

		// if input address is not factoid address
		inputs.stream().filter(a -> !isFactoidAddress(a)).findFirst().ifPresent(a -> {
			throw new IllegalArgumentException(String.format(
					"Input address list can only contain factoid address, %s is not factoid address", a.getAddress()));
		});

		outputs.stream().filter(a -> !isECAddress(a) && !isFactoidAddress(a)).findFirst().ifPresent(a -> {
			throw new IllegalArgumentException(String.format("%s is not a vaild factom address", a.getAddress()));
		});

	}
	
	private boolean isFactoidAddress(AddressAmount addressAmount){
		return addressAmount.getAddress().startsWith("FA");
	}
	
	private boolean isECAddress(AddressAmount addressAmount){
		return addressAmount.getAddress().startsWith("EC");
	}
	
	private boolean isFeePaymentAddressInInputs(Collection<AddressAmount> inputs, String feePaymentAddress){
		return inputs.stream().anyMatch( a -> feePaymentAddress.equals(a.getAddress()));
	}
	
	protected void parallelOperateAmountRequests(Collection<AddressAmount> inputs, Collection<AddressAmount> outputs){
		List<Future<TransactionResult>> futureList = new LinkedList<>();
		
		futureList.addAll(inputs.stream().<Future<TransactionResult>>map((a) -> {
			return addInput(a.getAddress(), a.getAmount());
		}).collect(Collectors.toList()));
		

		List<AddressAmount> ecAddressAmounts = outputs.stream()
				.filter(a -> a.getAddress().startsWith("EC")).collect(Collectors.toList());
		futureList.addAll(ecAddressAmounts.stream().<Future<TransactionResult>>map((a) -> {
			return addEcOutput(a.getAddress(), a.getAmount());
		}).collect(Collectors.toList()));
		
		List<AddressAmount> factoidAddressAmounts = outputs.stream()
				.filter(a -> a.getAddress().startsWith("FA")).collect(Collectors.toList());
		
		futureList.addAll(factoidAddressAmounts.stream().<Future<TransactionResult>>map((a) -> {
			return addOutput(a.getAddress(), a.getAmount());
		}).collect(Collectors.toList()));
		
		for (Future<TransactionResult> f : futureList){
			FutureUtil.safeGet(f);
		}
		
	}
	
	public String getTxName() {
		return txName;
	}
	
}
