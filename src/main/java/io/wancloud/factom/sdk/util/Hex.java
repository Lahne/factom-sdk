package io.wancloud.factom.sdk.util;

import java.util.LinkedList;
import java.util.List;

import io.wancloud.factom.sdk.FactomException;
import io.wancloud.factom.sdk.core.param.ComposeChainParam;
import io.wancloud.factom.sdk.core.param.ComposeEntryParam;
import io.wancloud.factom.sdk.core.result.EntryResult;

public class Hex {

	private Hex() {
	}

	public static List<String> encode(List<String> srcs) {
		if (srcs == null) {
			return null;
		}

		LinkedList<String> hexDest = new LinkedList<>();
		for (String src : srcs) {
			hexDest.add(encode(src));
		}
		return hexDest;
	}

	public static ComposeEntryParam encode(ComposeEntryParam params) {
		if (params == null) {
			return null;
		}
		String hexContent = encode(params.getContent());
		List<String> hexIds = encode(params.getExternalIds());
		return new ComposeEntryParam(params.getChainId(), params.getEntryCreditAddress(), hexContent, hexIds);
	}

	public static ComposeChainParam encode(ComposeChainParam params) {
		if (params == null) {
			return null;
		}
		String hexContent = encode(params.getContent());
		List<String> hexIds = encode(params.getExternalIds());
		return new ComposeChainParam(params.getEntryCreditAddress(), hexContent, hexIds);
	}

	public static String encode(String src) {
		if (src == null) {
			return null;
		}
		return org.apache.commons.codec.binary.Hex.encodeHexString(src.getBytes());
	}

	public static String decode(String src) {
		try {
			return new String(org.apache.commons.codec.binary.Hex.decodeHex(src.toCharArray()));
		} catch (Exception e) {
			throw new FactomException("Hex decode error", e);
		}
	}

	public static List<String> decode(List<String> hexedSrcs) {
		if (hexedSrcs == null) {
			return null;
		}

		LinkedList<String> origs = new LinkedList<>();
		for (String src : hexedSrcs) {
			origs.add(decode(src));
		}
		return origs;
	}

	public static EntryResult decode(EntryResult entryResult) {
		if (entryResult == null) {
			return null;
		}
		return new EntryResult(entryResult.getChainid(), decode(entryResult.getExtids()),
				decode(entryResult.getContent()));
	}

}
