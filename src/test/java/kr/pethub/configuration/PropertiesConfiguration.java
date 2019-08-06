package kr.pethub.configuration;


import java.io.IOException;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.jasypt.spring31.properties.EncryptablePropertySourcesPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import kr.pethub.core.constrants.SystemConstants;



@Configuration
public class PropertiesConfiguration {
	
	
	@Bean
	public  EnvironmentStringPBEConfig environmentVariableConfiguration() {
		EnvironmentStringPBEConfig envConfig = new EnvironmentStringPBEConfig();
		envConfig.setAlgorithm("PBEWithMD5AndDES");
		envConfig.setPassword(SystemConstants.ENCRYPT_KEY);
		return envConfig;
	}
	
	@Bean
	public  StandardPBEStringEncryptor configurationEncryptor() {
		StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
		standardPBEStringEncryptor.setConfig(environmentVariableConfiguration());

		return standardPBEStringEncryptor;
	}
	
	 @Bean
	public  PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() throws IOException   {
		
		 EncryptablePropertySourcesPlaceholderConfigurer configurer = new EncryptablePropertySourcesPlaceholderConfigurer(configurationEncryptor());
//		 	PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
			ResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
			Resource[] locations = patternResolver.getResources("classpath:application.properties");
			configurer.setLocations(locations);
			configurer.setIgnoreUnresolvablePlaceholders(true);
			configurer.setIgnoreResourceNotFound(true);

		return configurer;
	}
    
    
    
}