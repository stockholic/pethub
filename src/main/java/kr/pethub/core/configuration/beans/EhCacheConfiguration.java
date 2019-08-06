package kr.pethub.core.configuration.beans;

import javax.annotation.PostConstruct;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.FilterType;

@Configuration
@EnableCaching
//@ComponentScan(basePackages = "kr.pethub.webapp")
@ComponentScan( basePackages = "kr.pethub.webapp", includeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Service.class) } )
public class EhCacheConfiguration {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Bean
	public CacheManager cacheManager() {
		return new EhCacheCacheManager(ehCacheCacheManager().getObject());
	}
	
	
	@Bean
	public EhCacheManagerFactoryBean ehCacheCacheManager() {
		EhCacheManagerFactoryBean emf = new EhCacheManagerFactoryBean();
		emf.setConfigLocation(new ClassPathResource("/config/ehcache-config.xml"));
		emf.setShared(true);
		return emf;
	}
	
	@PostConstruct
    public void postConstruct() {
		logger.info("Cache Created");
    }

}