package kr.pethub.core.configuration.beans;


import java.net.URISyntaxException;

import javax.annotation.PostConstruct;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;



//@Aspect
//@Configuration
//@EnableAspectJAutoProxy
public class AopConfiguration {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
 	@Around("execution(* kr.pethub.webapp..service.*Service.*(..))")
    public Object serviceAop ( ProceedingJoinPoint joinPoint ) throws Throwable {
    	
 		logger.debug("hijacked : " + joinPoint.getSignature().getName());
		
		return joinPoint.proceed();
    	
    }
    
 	/**
 	 * 안됨 ???? 
 	 * @param joinPoint
 	 * @return
 	 * @throws Throwable
 	 */
 	@Around("execution(* kr.pethub.webapp..controller.*Controller.*(..))")
    public Object controllerAop(ProceedingJoinPoint joinPoint ) throws Throwable {
    	return joinPoint.proceed();
    }
    
    
    @PostConstruct
    public void postConstruct() throws URISyntaxException {
		logger.info("AopConfiguration Start");
    }
}
    
