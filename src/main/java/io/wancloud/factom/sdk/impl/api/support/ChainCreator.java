package io.wancloud.factom.sdk.impl.api.support;


import static io.wancloud.factom.sdk.impl.api.support.EntryStage.COMMITTED;
import static io.wancloud.factom.sdk.impl.api.support.EntryStage.COMPOSED;
import static io.wancloud.factom.sdk.impl.api.support.EntryStage.NOT_START;
import static io.wancloud.factom.sdk.impl.api.support.EntryStage.REVEALED;
import static io.wancloud.factom.sdk.impl.api.support.EntryStage.WORKING;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.wancloud.factom.sdk.FactomException;
import io.wancloud.factom.sdk.api.sync.FactomCommand;
import io.wancloud.factom.sdk.core.model.EntryInfo;
import io.wancloud.factom.sdk.core.param.ComposeChainParam;
import io.wancloud.factom.sdk.core.result.ComposeChainResult;
import io.wancloud.factom.sdk.core.result.EntryCommitResult;
import io.wancloud.factom.sdk.core.result.RevealResult;
import io.wancloud.factom.sdk.support.TimeoutException;
import io.wancloud.factom.sdk.util.FactomAssert;
import io.wancloud.factom.sdk.util.Hex;
import io.wancloud.factom.sdk.util.Sleeper;

/**
 * @author wanglei
 */
public class ChainCreator {
	
	protected static final Logger logger = LoggerFactory.getLogger(ChainCreator.class);
	
	private int sleepMillSecondsBeforeRevealChain;
	
	private ComposeChainParam chainParams;
	
	private ComposeChainResult result;
	
	private FactomCommand factomCommand;
	
	private volatile int stage = NOT_START;
	
    public ChainCreator(FactomCommand factomCommand, ComposeChainParam chainParam) {
		FactomAssert.notNull(factomCommand, "FactomCommand should not be null");
		FactomAssert.notNull(chainParam, "ComposeChainParam should not be null");
		this.factomCommand = factomCommand;
		this.chainParams = chainParam;
	}

	public EntryInfo create() throws TimeoutException, FactomException{
		synchronized (this) {
			if (stage != NOT_START){
				throw new FactomException("Already working to create chain");
			}
			addStage(WORKING);
		}

		ComposeChainParam hexChainParams = Hex.encode(chainParams);
		
		logger.debug("Compose chain");
		result = factomCommand.composeChain(hexChainParams);
		addStage(COMPOSED);
		
		logger.debug("Commit chain {}", result.getCommitMessage());
		EntryCommitResult cResult = null;

		cResult = factomCommand.commitChain(result.getCommitMessage());
		addStage(COMMITTED);
		
		logger.debug("Sleep {} millseconds before reveal chain",sleepMillSecondsBeforeRevealChain);
		//sleep several seconds to make sure reveal operation successful
		Sleeper.sleep(sleepMillSecondsBeforeRevealChain);
			
		logger.debug("Reveal chain {}", result.getRevealEntry());
		RevealResult rResult = factomCommand.revealChain(result.getRevealEntry());
		addStage(REVEALED);
		
		logger.debug("Created chain id is {}", rResult.getChainid());

		return new EntryInfo(rResult.getChainid(), cResult.getTxid(), rResult.getEntryHash());
	}
	
	protected void addStage(int stage){
		this.stage &= stage;
	}
	
	public int getSleepMillSecondsBeforeRevealChain() {
		return sleepMillSecondsBeforeRevealChain;
	}

	public void setSleepMillSecondsBeforeRevealChain(int sleepMillSecondsBeforeRevealChain) {
		this.sleepMillSecondsBeforeRevealChain = sleepMillSecondsBeforeRevealChain;
	}

}
