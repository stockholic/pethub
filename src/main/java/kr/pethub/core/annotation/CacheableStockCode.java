package kr.pethub.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

import org.springframework.cache.annotation.Cacheable;

/**
 * 주식 코드를 캐싱한다.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Cacheable(value = "stockCodeCache", key = "#root.caches[0].name")
public @interface CacheableStockCode {}