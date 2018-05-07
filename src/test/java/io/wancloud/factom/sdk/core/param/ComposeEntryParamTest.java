package io.wancloud.factom.sdk.core.param;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;

import io.wancloud.factom.sdk.TestBase;
import io.wancloud.factom.sdk.core.param.ComposeEntryParam;
import io.wancloud.factom.sdk.core.util.JsonUtil;

/**
 * @author wanglei
 * @date 2017-06-15
 */
public class ComposeEntryParamTest extends TestBase{

	public ComposeEntryParamTest() {
	}

	@Test
	public void testComposeEntryParams(){
		String actualAddress = "mockAddress";
		String actualContent = "mockContent";
		String actualChainId = "mockChainId";
		List<String> extIds = Arrays.asList("id1","id2","id3");
		ComposeEntryParam p = new ComposeEntryParam(actualChainId,actualAddress, actualContent);
		p.setExternalIds(extIds);
		String json = p.serialize();
		//System.out.println(json);
		JsonNode params = JsonUtil.deserializeToJsonNode(json);
		
		Assert.assertEquals(params.get("entry").get("chainid").asText(), actualChainId);
		Assert.assertEquals(params.get("entry").get("content").asText(), actualContent);
		Assert.assertEquals(params.get("entry").get("extids").size(), extIds.size());
		Assert.assertEquals(params.get("ecpub").asText(), actualAddress);
		
	} 
	
	
}
