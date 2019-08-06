package kr.pethub.core.configuration.handler;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


public class ConsoleSocketHandler extends TextWebSocketHandler {
	
	Logger logger =  LoggerFactory.getLogger(this.getClass());
	
	CopyOnWriteArrayList<WebSocketSession> sessionList = new CopyOnWriteArrayList<>();
	
	
	 @Override
    public void afterConnectionEstablished(WebSocketSession session)   throws Exception {
		//the messages will be broadcasted to all users.
		 sessionList.add(session);
    	logger.info("connect sessionId : {} , count :  {}", session.getId(), sessionList.size());
    	
    }
    
    @Override
    public void afterConnectionClosed(WebSocketSession session,  CloseStatus status) throws Exception {
    	sessionList.remove(session);
        logger.info("close sessionId : {}, count :  {}", session.getId(), sessionList.size());
    }
    
    /**
     * 클라이언트 메세지 수신
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    	outputMessage(message);
    }
    
    public void outputMessage(TextMessage msg) {
    	
    	// 메세지 송신
		for (WebSocketSession webSocketSession : sessionList) {
			try {
				webSocketSession.sendMessage( msg );
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }
    
    
}
