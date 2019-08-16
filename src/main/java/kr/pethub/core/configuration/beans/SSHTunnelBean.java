package kr.pethub.core.configuration.beans;

import javax.annotation.PostConstruct;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import kr.pethub.core.utils.JSchUtil;

@Component
public class SSHTunnelBean {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Value("${deploy}")
	 private  String deploy;
	
	 @Value("#{systemProperties['adminTool.key']}") 
	 private String key;
	 
	 private  Session session;
	
	@PostConstruct
	public void init() throws JSchException {
		
		if("local".equals(deploy)) {
			 JSchUtil js = new JSchUtil( "host",22,"user","password");
			 
			 Session session = js.getSession();
			 session.connect();
			 session.setPortForwardingL(000, "localhost", 000);
			 
			 logger.info("key : {}",key);
			 logger.info("deploy : {}, SSH forwarding {} To {}", deploy, "localhost","host");
		}
		
		logger.info("deploy : {}, SSH forwarding {} To {}", deploy, "localhost","host");
		
	}
	
	@PreDestroy
	public void close() {
		if("local".equals(deploy) && session != null) {
			session.disconnect();
			logger.info("deploy : {}, SSH forwarding disconnect", deploy);
		}
	}

}
