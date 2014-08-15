package me.nandork.simple;

import me.nandork.simple.controller.EchoWebSocketHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableWebSocket
@EnableScheduling()
public class JmxWebApplication implements WebSocketConfigurer {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(JmxWebApplication.class, args);
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new EchoWebSocketHandler(), "/ws");
    }
}
