package kr.pethub.core.configuration.beans;

import java.net.URISyntaxException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import kr.pethub.core.configuration.handler.ConsoleSocketHandler;
import kr.pethub.core.module.service.ConsoleLog;


@Configuration
@EnableWebSocket
public class WebSocketConfiguration  implements WebSocketConfigurer {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		
    	// nginx 연결시 소켓 타임아웃 설정해 줘야 한다.
    	/*
    	  location / {
                proxy_pass  http://127.0.0.1:8080;
                proxy_set_header   Host             $host;
                #proxy_set_header X-Real-IP $remote_addr;
                proxy_set_header X-Forwarded-For $remote_addr;
                proxy_http_version 1.1;

                # use websocket
                proxy_set_header Upgrade $http_upgrade;
                proxy_set_header Connection "upgrade";

                # websocket timeout ex. 1d days ,  86400s	1days
                proxy_connect_timeout 1d;
                proxy_send_timeout 1d;
                proxy_read_timeout 1d;

                client_max_body_size 500M;
        	}
    	 */
    	registry.addHandler(consoleSocketHandler(), "/console");
    	
    }
    
    @Bean
    public ConsoleSocketHandler consoleSocketHandler() {
    	return new ConsoleSocketHandler();
    }
    
    // 콘솔 로그 WebSocket 접속
	@Bean
	public ConsoleLog consoleLog() {
		return new ConsoleLog();
	}
    
    
    @PostConstruct
    public void postConstruct() throws URISyntaxException {
    	
    	//웹소켓 생성 후 연결
    	consoleLog().consoleConnect();
    	
		logger.info("WebSocket Created");
    }
    
    @PreDestroy
    public void preDestroy() {
		logger.info("WebSocket shutdown");
    }
    
}
