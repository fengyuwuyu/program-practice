package com.ll.program.practice.utils;

import java.lang.reflect.Method;

@SuppressWarnings("rawtypes")
public class ReflectUtils {

	public static Class[] getClassParamsType(Class clazz, String method){
		Method[] methods = clazz.getMethods();
		for (Method method2 : methods) {
			if(method2.getName().equals(method)){
				return method2.getParameterTypes();
			}
		}
		return null;
	}
}
