package io.wancloud.factom.sdk.core;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.wancloud.factom.sdk.core.util.JsonUtil;

/**
 * @author wanglei
 */
public class FactomResponseHandler implements ResponseHandler<FactomResponse>{

	protected Logger logger = LoggerFactory.getLogger(FactomResponseHandler.class);
	
	public FactomResponseHandler() {
	}

    @Override
    public FactomResponse handleResponse(final HttpResponse response) 
            throws HttpResponseException, IOException {
    	logger.debug("HttpResponse with status: {} {}", response.getStatusLine().getStatusCode(), response.getStatusLine().getReasonPhrase());
    	final HttpEntity entity = response.getEntity();
    	if (entity == null)
    		return null;
    	
		String responseBody = EntityUtils.toString(entity);
		logger.debug("Json Response: {}",responseBody);
		return JsonUtil.deserialize(responseBody, FactomResponse.class);
    }
	

}
