package kr.pethub.core.configuration.beans;


import java.net.URISyntaxException;

import javax.annotation.PostConstruct;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



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
    
