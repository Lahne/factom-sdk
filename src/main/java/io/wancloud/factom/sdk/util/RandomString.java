package io.wancloud.factom.sdk.util;

import java.security.SecureRandom;

public class RandomString {

	private static final SecureRandom random = new SecureRandom();
	
	private static final char[] alphaNumbers = {'0','1','2','3','4','5','6','7','8','9',
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	
	private RandomString() {
	}

	public static String randomString(int len){
		StringBuilder sb = new StringBuilder(len);
		for( int i = 0; i < len; i++ ) 
			sb.append(alphaNumbers[random.nextInt(alphaNumbers.length)]);
		return sb.toString();
	}
	
	public static String randomString32(){
		return randomString(32);
	}
}
