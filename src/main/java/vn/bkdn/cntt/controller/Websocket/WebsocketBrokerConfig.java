package vn.bkdn.cntt.controller.Websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Created by XuanVinh on 3/30/2017.
 */

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketBrokerConfig extends AbstractWebSocketMessageBrokerConfigurer{
    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        stompEndpointRegistry.addEndpoint("/week-calendar/edit").setAllowedOrigins("*").withSockJS();
        stompEndpointRegistry.addEndpoint("/calendar").setAllowedOrigins("*").withSockJS();
        stompEndpointRegistry.addEndpoint("/register").setAllowedOrigins("*").withSockJS();
        stompEndpointRegistry.addEndpoint("/student/register").setAllowedOrigins("*").withSockJS();
        stompEndpointRegistry.addEndpoint("/calendar/auto-generate").setAllowedOrigins("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app")
                .enableSimpleBroker("/socket", "/queue");
    }


}
