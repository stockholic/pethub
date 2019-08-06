package kr.pethub.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;

import org.springframework.cache.annotation.CacheEvict;

/**
 * 관리자 페이지 메뉴 캐싱을 제거한다.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
//@CacheEvict(value = "menuCache", allEntries = true)
@CacheEvict(value = "menuCache",  key = "#root.caches[0].name + '_' + #arrMenu.authCd")
public @interface CacheEvictMenu {}