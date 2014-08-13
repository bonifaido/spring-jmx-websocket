package me.nandork.simple.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.Set;

@RestController
public class MBeanController {

    @RequestMapping("jmx")
    public Set<ObjectName> objectNames() {
        return ManagementFactory.getPlatformMBeanServer().queryNames(null, null);
    }

    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    }
}
