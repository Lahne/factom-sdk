package io.wancloud.factom.sdk.core.result;


public class ComposeEntryResult {
	
	private final String commitMessage;
	
	private final String revealEntry;
	
	public ComposeEntryResult(String commitMessage, String revealEntry) {
		super();
		this.commitMessage = commitMessage;
		this.revealEntry = revealEntry;
	}

	public String getCommitMessage() {
		return commitMessage;
	}

	public String getRevealEntry() {
		return revealEntry;
	}
}
