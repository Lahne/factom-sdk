package util;

import org.junit.Test;

import io.wancloud.factom.sdk.TestBase;
import io.wancloud.factom.sdk.util.RandomString;

public class RandomStringTest extends TestBase{

	public RandomStringTest() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	@Test
	public void test(){
		for(int i=0; i < 1000; i++){
			System.out.println(RandomString.randomString32());
		}
	}

}
