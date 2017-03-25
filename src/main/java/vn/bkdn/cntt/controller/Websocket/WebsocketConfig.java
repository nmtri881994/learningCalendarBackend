package vn.bkdn.cntt.controller.Websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Tri on 3/25/2017.
 */

@Configuration
@EnableWebSocket
public class WebsocketConfig implements WebSocketConfigurer{
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(new ChatHandler(), "chats").setAllowedOrigins("*");
    }

    class ChatHandler extends TextWebSocketHandler{

        private List<WebSocketSession> webSocketSessions = new CopyOnWriteArrayList<>();

        @Override
        public void afterConnectionEstablished(WebSocketSession session) throws Exception {
            webSocketSessions.add(session);
        }

        @Override
        protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
            for (WebSocketSession webSocketSession:
                 webSocketSessions) {
                System.out.println("----------"+message);
                webSocketSession.sendMessage(message);
            }
        }
    }
}
