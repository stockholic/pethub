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
	
	 @Value("#{systemProperties['pethub.ip']}") 
	 private String ip;
	 
	 @Value("#{systemProperties['pethub.user']}") 
	 private String user;
	 
	 @Value("#{systemProperties['pethub.passwd']}") 
	 private String passwd;
	 
	 private Session session;
	
	@PostConstruct
	public void init() throws JSchException {
		
		if("local".equals(deploy)) {
			 JSchUtil js =new JSchUtil(ip,22, user ,passwd);
			 
			 Session session = js.getSession();
			 session.connect();
			 session.setPortForwardingL(3306, "localhost", 3306);
			 
			 logger.info("ip : {}",ip);
			 logger.info("deploy : {}, SSH forwarding {} To {}", deploy, "localhost","host");
		}
		
		logger.info("deploy : {}, SSH forwarding {} To {}", deploy, "localhost","host");
		
	}
	
	@PreDestroy
	public void close() {
		if("local".equals(deploy) && session != null) {
			if(session.isConnected() == false) {
				session.disconnect();
			}
			logger.info("deploy : {}, SSH forwarding disconnect", deploy);
		}
	}

}
