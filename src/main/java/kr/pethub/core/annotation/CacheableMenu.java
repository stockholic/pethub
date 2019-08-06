package kr.pethub.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

import org.springframework.cache.annotation.Cacheable;

/**
 * 관리자 페이지 메뉴를 캐싱한다.
 * 키를 사용하여 권한별 메뉴를 캐싱한다.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
//@Cacheable(value = "menuCache", key = "#root.caches[0].name + '_' + #root.targetClass + '_' + #root.methodName + '_' + #role.roleCd")
@Cacheable(value = "menuCache", key = "#root.caches[0].name + '_' + #role.roleCd")
public @interface CacheableMenu {}