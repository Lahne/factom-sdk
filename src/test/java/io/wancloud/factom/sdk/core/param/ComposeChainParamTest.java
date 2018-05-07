package io.wancloud.factom.sdk.core.param;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;

import io.wancloud.factom.sdk.TestBase;
import io.wancloud.factom.sdk.core.util.JsonUtil;

/**
 * @author wanglei
 * @date 2017-06-15
 */
public class ComposeChainParamTest extends TestBase{

	public ComposeChainParamTest() {
	}

	@Test
	public void testComposeChainParams(){
		String actualAddress = "mockAddress";
		String actualContent = "mockContent";
		List<String> extIds = Arrays.asList("id1","id2","id3");
		ComposeChainParam p = new ComposeChainParam(actualAddress, actualContent);
		p.setExternalIds(extIds);
		String json = p.serialize();
		System.out.println(json);
		JsonNode params = JsonUtil.deserializeToJsonNode(json);
		
		Assert.assertEquals(params.get("chain").get("firstentry").get("content").asText(), actualContent);
		Assert.assertEquals(params.get("chain").get("firstentry").get("extids").size(), extIds.size());
		Assert.assertEquals(params.get("ecpub").asText(), actualAddress);
	} 
	
	
}
