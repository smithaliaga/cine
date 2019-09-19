package com.teamwork.cineperu.Util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class UtilObjectMapper {

	private static ObjectMapper objectMapper = new ObjectMapper();
	
	static {
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.setPropertyNamingStrategy(new UtilPropertyNamingStrategy());
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
	}

	public static ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	public static void setObjectMapper(ObjectMapper objectMapper) {
		UtilObjectMapper.objectMapper = objectMapper;
	}
	
	
}
