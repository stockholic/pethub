package kr.pethub.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;

import org.springframework.cache.annotation.CacheEvict;

/**
 * 주식 코드 캐싱을 제거한다.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
//@CacheEvict(value = "stockCodeCache", allEntries = true)
@CacheEvict(value = "stockCodeCache",  key = "#root.caches[0].name")
public @interface CacheEvictStockCode {}