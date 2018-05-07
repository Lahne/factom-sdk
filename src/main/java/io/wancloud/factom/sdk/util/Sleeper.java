package io.wancloud.factom.sdk.util;

public class Sleeper {

	private Sleeper() {
	}

	
	public static void sleep(int millis){
		try {
			Thread.sleep(millis);
		} catch (InterruptedException ignored) {
		}
	}
	
	
	public static void sleep(int millis, int nanos){
		try {
			Thread.sleep(millis, nanos);
		} catch (InterruptedException ignored) {
			Thread.currentThread().interrupt();
		}
	}
	
	
}
