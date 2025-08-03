package com.example.E_commerceApplication.implementation;

import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class CacheInspectionService {

	@Autowired
	private CacheManager cacheManager;

	public void printCacheContents(String cacheName) {
		Cache cache = cacheManager.getCache(cacheName);

		if (cache == null) {
			System.out.println("❌ No cache found with name: " + cacheName);
			return;
		}

		System.out.println("🧠 Inspecting cache: " + cacheName);
		Object nativeCache = cache.getNativeCache();

		if (nativeCache instanceof ConcurrentMap<?, ?> map) {
			System.out.println("✅ Cached entries:");
			map.forEach((key, value) -> {
				System.out.println("Key: " + key + " | Value: " + value);
			});
		} else {
			System.out.println("⚠️ This cache type doesn't support direct inspection.");
			System.out.println("Native cache class: " + nativeCache.getClass());
		}
	}
}
