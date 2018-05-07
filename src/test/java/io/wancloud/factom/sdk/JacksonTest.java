package io.wancloud.factom.sdk;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import io.wancloud.factom.sdk.core.model.EntryInfo;

public class JacksonTest extends TestBase{

	public JacksonTest() {
		// TODO Auto-generated constructor stub
	}
	
	@Test
	public void testJacksonSerialize(){
		ObjectMapper mapper = new ObjectMapper();
		FilterProvider filters = new SimpleFilterProvider().addFilter("myFilter", new MyFilter());
		mapper.setFilterProvider(filters);
		Map<String,Object> map = new MyMap();
		map.put("name", "wang");
		map.put("nickname", "xiaowang");
		map.put("entryInfo", new EntryInfo("chainid", "txid", "hash"));
		try {
			String asString = mapper.writeValueAsString(map);
			System.out.println(asString);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@JsonFilter("myFilter")
	public static class MyMap extends HashMap<String, Object>{
		
	}
	
	public static class MyFilter extends SimpleBeanPropertyFilter {
		
		   @Override
		   protected boolean include(PropertyWriter writer) {
			  PropertyName name = writer.getFullName();
			  System.out.println("----> " +name.getNamespace() + ", " + name.getSimpleName());
			  
		      return false;
		   }
		
		   @Override
		   public void serializeAsField(Object pojo, JsonGenerator jgen, SerializerProvider provider, PropertyWriter writer)
		     throws Exception {
			   System.out.println(pojo.getClass().getName()+ ":" + pojo);
		      if (include(writer)) {
		         writer.serializeAsField(pojo, jgen, provider);
		      } else if (!jgen.canOmitFields()) { // since 2.3
		         writer.serializeAsOmittedField(pojo, jgen, provider);
		      }
		   }
	}

}
