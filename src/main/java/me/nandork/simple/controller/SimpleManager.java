package me.nandork.simple.controller;

import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Component
@ManagedResource
public class SimpleManager {
    @ManagedOperation
    public void hello() {
        System.out.println("Hellooo");
    }
}
