package io.wancloud.factom.sdk;

public class ClientContext {
	
	public static final int DEFAULT_SLEEP_MILL_SECONDS_BEFORE_REVEAL_ENTRY = 2000;

	public static final int DEFAULT_SLEEP_MILL_SECONDS_BEFORE_REVEAL_CHAIN = 5000;

	private int sleepMillSecondsBeforeRevealEntry = DEFAULT_SLEEP_MILL_SECONDS_BEFORE_REVEAL_ENTRY;

	private int sleepMillSecondsBeforeRevealChain = DEFAULT_SLEEP_MILL_SECONDS_BEFORE_REVEAL_CHAIN;

	public int getSleepMillSecondsBeforeRevealEntry() {
		return sleepMillSecondsBeforeRevealEntry;
	}

	public void setSleepMillSecondsBeforeRevealEntry(int sleepMillSecondsBeforeRevealEntry) {
		this.sleepMillSecondsBeforeRevealEntry = Math.max(sleepMillSecondsBeforeRevealEntry, DEFAULT_SLEEP_MILL_SECONDS_BEFORE_REVEAL_ENTRY);
	}

	public int getSleepMillSecondsBeforeRevealChain() {
		return sleepMillSecondsBeforeRevealChain;
	}

	public void setSleepMillSecondsBeforeRevealChain(int sleepMillSecondsBeforeRevealChain) {
		this.sleepMillSecondsBeforeRevealChain =  Math.max(sleepMillSecondsBeforeRevealChain, DEFAULT_SLEEP_MILL_SECONDS_BEFORE_REVEAL_CHAIN);
	}

	public static int getDefaultSleepMillSecondsBeforeRevealEntry() {
		return DEFAULT_SLEEP_MILL_SECONDS_BEFORE_REVEAL_ENTRY;
	}

	public static int getDefaultSleepMillSecondsBeforeRevealChain() {
		return DEFAULT_SLEEP_MILL_SECONDS_BEFORE_REVEAL_CHAIN;
	}
	
}
