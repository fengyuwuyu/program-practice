package com.ll.program.practice.utils;

import java.util.HashMap;
import java.util.Map;

public class MapUtils {

	public static Map<String, Object> createMap(Object... obj) {
		if (obj == null || obj.length % 2 != 0) {
			return new HashMap<>();
		}
		Map<String, Object> result = new HashMap<>();
		for (int i = 0; i < obj.length; i = i + 2) {
			if (!(obj[i] instanceof String)) {
				throw new IllegalArgumentException("奇数项必须是String类型！");
			}
			result.put((String) obj[i], obj[i + 1]);
		}
		return result;

	}
	
	public static Map<String, Object> createSuccessMap(Object... obj) {
		Map<String, Object> result = createMap(obj);
		result.put("success", true);
		return result;

	}

	public static Map<String, Object> createFailMap(Object... obj) {
		Map<String, Object> result = createMap(obj);
		result.put("success", false);
		return result;

	}
}
