package com.ll.program.practice.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@SuppressWarnings({"rawtypes","unchecked"})
public class DynamicProxy implements InvocationHandler {

	private static Class mapperInterface;

	public static void setMapperInterface(Class mapperInterface) {
		DynamicProxy.mapperInterface = mapperInterface;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// 执行方法
		Class[] paramsClasses = ReflectUtils.getClassParamsType(mapperInterface, "result");
		Method result = mapperInterface.getMethod("result", paramsClasses);
		Class[] params = result.getParameterTypes();
		Object obj = null;
		if (params.length > 1) {
			obj = CommonUtil.createInstance(params[0]);
		}
		return obj;
	}

	public static <T> T newMapperProxy(Class<T> mapperInterface) throws Exception {
		ClassLoader classLoader = mapperInterface.getClassLoader();
		Class<?>[] interfaces = new Class[] { mapperInterface };
		DynamicProxy proxy = new DynamicProxy();
		return (T) Proxy.newProxyInstance(classLoader, interfaces, proxy);
	}

}
