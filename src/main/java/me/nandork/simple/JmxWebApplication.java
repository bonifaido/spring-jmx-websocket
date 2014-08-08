package me.nandork.simple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class JmxWebApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(JmxWebApplication.class, args);
    }
}
