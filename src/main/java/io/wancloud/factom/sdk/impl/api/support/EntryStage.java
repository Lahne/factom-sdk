package io.wancloud.factom.sdk.impl.api.support;

public class EntryStage {
	
	public static final int NOT_START = 0;
	public static final int WORKING = 1;
	public static final int COMPOSED = 1 << 1;
	public static final int COMMITTED = 1 << 2;
	public static final int REVEALED = 1 << 3;
	
}
