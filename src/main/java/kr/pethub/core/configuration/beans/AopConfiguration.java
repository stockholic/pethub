package kr.pethub.core.configuration.beans;


import java.net.URISyntaxException;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;



//@Aspect
//@Configuration
//@EnableAspectJAutoProxy
public class AopConfiguration {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
 	@Around("execution(* kr.pethub.webapp..service.*Service.*(..))")
    public Object serviceAop ( ProceedingJoinPoint joinPoint ) throws Throwable {
    	
 		logger.debug("hijacked : " + joinPoint.getSignature().getName());
 		
 		
 		HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
 		
 		
 		Map<String, String[]> params =   request.getParameterMap();
 		
 		for (String key : params.keySet()) {
 		    String[] strArr = (String[]) params.get(key);
 		    for (String val : strArr) {
 		        System.out.println(key + "=" + val);
 		    }
 		}
 		
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
    
