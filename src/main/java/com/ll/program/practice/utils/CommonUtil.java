package com.ll.program.practice.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommonUtil {

	@SuppressWarnings("rawtypes")
	public static final Class[] BASECLASS = { Integer.class, String.class, Long.class, List.class, Boolean.class,
			byte[].class, Double.class };

	@SuppressWarnings("unchecked")
	public static <T> List<T> createPersons(int size, Class<T> clazz) throws Exception {
		if (size <= 0) {
			return Collections.emptyList();
		}

		List<T> list = new ArrayList<T>();
		for (int i = 0; i < size; i++) {
			T t = (T) createInstance(clazz);
			list.add(t);
		}
		return list;
	}

	@SuppressWarnings("rawtypes")
	public static Object createInstance(Class clazz) throws Exception {
		if (clazz.isInterface() && clazz != List.class) {
			return createInterfaceInstance(clazz);
		}
		return createClassInstance(clazz);
	}

	@SuppressWarnings("rawtypes")
	public static Object createBaseClass(Class clazz) {
		if (clazz == Integer.class) {
			return RandomUtil.randomInt(0, Integer.MAX_VALUE / 100);
		} else if (clazz == Long.class) {
			return Math.abs(RandomUtil.randomLong());
		} else if (clazz == String.class) {
			return RandomUtil.randomString(32);
		} else if (clazz == List.class) {
			return new ArrayList<>();
		} else if (clazz == Boolean.class) {
			return true;
		} else if (clazz == Double.class) {
			return Math.abs(Double.valueOf(RandomUtil.randomFloat(0.0F, Float.MAX_VALUE) + ""));
		}
		return null;

	}

	@SuppressWarnings("rawtypes")
	public static boolean contains(Class clazz) {
		for (Class class1 : BASECLASS) {
			if (class1 == clazz) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("rawtypes")
	public static Object createClassInstance(Class clazz) throws Exception {
		if (contains(clazz)) {
			return createBaseClass(clazz);
		} else if (clazz.isEnum()) {
			Object[] objs = clazz.getEnumConstants();
			if (objs != null && objs.length > 0) {
				return objs[0];
			} else {
				return null;
			}
		} else {
			Object instance = clazz.newInstance();
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				Object obj = createClassInstance(field.getType());
				field.setAccessible(true);
				field.set(instance, obj);
			}
			return instance;
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object createInterfaceInstance(Class clazz) throws Exception {
		DynamicProxy.setMapperInterface(clazz);
		return DynamicProxy.newMapperProxy(clazz);

	}

	public static String headToUpper(String str) {
		if (StringUtil.isNullEmpty(str)) {
			return null;
		}

		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
	
	public static int hash(Object key) {
        int h;
		return (h = key.hashCode()) ^ (h >>> 16);
	}
	
	public static void main(String[] args) {
		String str = "asdasda";
		System.out.println(str.hashCode());
		System.out.println(hash(str));
	}
}
