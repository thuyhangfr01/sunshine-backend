package com.ute.sunshinebackend.config;

import com.ute.sunshinebackend.websocket.MyWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    //Http: client chi gui request toi server và server tra ve response, server khong the tu gui request toi bat cu client nao
    //Websocket: tao ket noi 2 chieu, su dung TCP socket

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new MyWebSocketHandler(), "/ws")
                .setAllowedOrigins("*")
                .withSockJS();
    }

}
