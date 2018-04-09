package com.ll.program.practice.autoconfig;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.cache.CacheBuilder;

@Configuration
@EnableCaching
public class AutoConfiguration {

	@Bean
	public CacheManager cacheManager() {
		GuavaCacheManager cacheManager = new GuavaCacheManager();
		cacheManager.setCacheBuilder(CacheBuilder.newBuilder().expireAfterAccess(30, TimeUnit.MINUTES).maximumSize(100000));
		return cacheManager;
	}
	
	public static void main(String[] args) throws InterruptedException {
		GuavaCacheManager cacheManager = new GuavaCacheManager();
		cacheManager.setCacheBuilder(CacheBuilder.newBuilder().expireAfterAccess(10, TimeUnit.SECONDS).maximumSize(100000));
		
		Cache cache = cacheManager.getCache("");
		cache.put("test", "hello guava");
		Thread.sleep(11000L);
		String s = cache.get("test", String.class);
		System.out.println(s);
		
	}
}
