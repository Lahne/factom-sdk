package util;

import org.junit.Assert;
import org.junit.Test;

import io.wancloud.factom.sdk.TestBase;
import io.wancloud.factom.sdk.util.Hex;

public class HexTest extends TestBase{

	@Test
	public void testHex(){
		String src = "abcd12中国，中國";
		String dest = Hex.encode(src);
		System.out.println(dest);
		String back = Hex.decode(dest);
		System.out.println(back);
		Assert.assertEquals(src, back);
	}

}
