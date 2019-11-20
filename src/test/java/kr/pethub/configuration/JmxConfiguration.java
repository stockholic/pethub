package kr.pethub.configuration;


import java.net.URISyntaxException;

import javax.annotation.PostConstruct;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class JmxConfiguration {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${jmx.rmi.host}")
    private String rmiHost;

    @Value("${jmx.rmi.port}")
    private Integer rmiPort;

    
    @Bean
    public JMXConnector jmxConnector() throws Exception {
        JMXServiceURL url = new JMXServiceURL(String.format("service:jmx:rmi://%s:%s/jndi/rmi://%s:%s/jmxrmi", rmiHost, rmiPort, rmiHost, rmiPort));
		JMXConnector jmxConnector = JMXConnectorFactory.connect(url, null);
        return jmxConnector;
    }
    
    @PostConstruct
    public void postConstruct() throws URISyntaxException {
		logger.info("JMX Client Created");
    }
}
    
