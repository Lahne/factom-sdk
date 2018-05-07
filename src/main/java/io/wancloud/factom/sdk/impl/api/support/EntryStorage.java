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
import io.wancloud.factom.sdk.core.param.ComposeEntryParam;
import io.wancloud.factom.sdk.core.result.ComposeEntryResult;
import io.wancloud.factom.sdk.core.result.EntryCommitResult;
import io.wancloud.factom.sdk.core.result.RevealResult;
import io.wancloud.factom.sdk.util.FactomAssert;
import io.wancloud.factom.sdk.util.Hex;
import io.wancloud.factom.sdk.util.Sleeper;

/**
 * @author wanglei
 */
public class EntryStorage {
	
	protected static final Logger logger = LoggerFactory.getLogger(EntryStorage.class);
	
	private int sleepMillSecondsBeforeRevealEntry;
	
	private FactomCommand factomCommand;
	
	private ComposeEntryParam entryParams;
	
	private ComposeEntryResult result;

	private volatile int stage = NOT_START;

	public EntryStorage(FactomCommand factomCommand, ComposeEntryParam entryParam) {
		FactomAssert.notNull(factomCommand, "FactomCommand should not be null");
		FactomAssert.notNull(entryParam, "ComposeEntryParam should not be null");
		this.factomCommand = factomCommand;
		this.entryParams = entryParam;
		setSleepMillSecondsBeforeRevealEntry(sleepMillSecondsBeforeRevealEntry);
	}
	
	public EntryInfo store() throws FactomException{
		synchronized (this) {
			if (stage != NOT_START){
				throw new FactomException("Already working to create Entry");
			}
			setStage(WORKING);
		}
		
		ComposeEntryParam hexEntryParams = Hex.encode(entryParams);
	
		logger.debug("Compose Entry");
		result = factomCommand.composeEntry(hexEntryParams);
		setStage(COMPOSED);
		logger.debug("Commit Entry {}", result.getCommitMessage());
		
		EntryCommitResult cResult = factomCommand.commitEntry(result.getCommitMessage());
		setStage(COMMITTED);
		
		logger.debug("Sleep {} millseconds before reveal entry",sleepMillSecondsBeforeRevealEntry);
		//sleep several seconds to make sure reveal operation successful
		Sleeper.sleep(sleepMillSecondsBeforeRevealEntry);
		
		logger.debug("Reveal entry {}", result.getRevealEntry());
		RevealResult rResult = factomCommand.revealEntry(result.getRevealEntry());
		setStage(REVEALED);
		
		return new EntryInfo(hexEntryParams.getChainId(), cResult.getTxid(), rResult.getEntryHash());
	}
	
	protected void setStage(int stage){
		this.stage = stage;
	}
	
	public int getSleepMillSecondsBeforeRevealEntry() {
		return sleepMillSecondsBeforeRevealEntry;
	}

	public void setSleepMillSecondsBeforeRevealEntry(int sleepMillSecondsBeforeRevealEntry) {
		this.sleepMillSecondsBeforeRevealEntry = sleepMillSecondsBeforeRevealEntry;
	}


}
