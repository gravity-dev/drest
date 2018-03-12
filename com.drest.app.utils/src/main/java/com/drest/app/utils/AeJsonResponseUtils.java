package com.drest.app.utils;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AeJsonResponseUtils {

	public static enum STATUS {
		Success, Failure
	}

	public static final String CODE = "code";

	public static final String STATUS = "status";

	public static final String MESSAGE = "message";

	public static String errorResponse(String code, STATUS status, String message)
			throws Exception {
		HashMap<String, String> map = new HashMap<String, String>(3);
		map.put(CODE, code);
		map.put(STATUS, status.name());
		map.put(MESSAGE, message);

		return new ObjectMapper().writeValueAsString(map);
	}

	public static String keyResponse(STATUS status) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>(1);
		map.put("status", status.name());
		return new ObjectMapper().writeValueAsString(map);
	}

	public static String keyResponse(String key, String value) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>(1);
		map.put(key, value);
		return new ObjectMapper().writeValueAsString(map);
	}

	public static String response(Map<String, String> mapObj) throws Exception {
		return new ObjectMapper().writeValueAsString(mapObj);
	}
}
