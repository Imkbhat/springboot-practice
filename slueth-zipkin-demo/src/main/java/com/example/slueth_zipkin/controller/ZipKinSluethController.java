package com.example.slueth_zipkin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ZipKinSluethController {
	
	private static final Logger log = LoggerFactory.getLogger(ZipKinSluethController.class);

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hi")
    public String hi() {
        log.info("Inside hi() method");
        String response = restTemplate.getForObject("http://localhost:8080/call", String.class);
        return "Hi! Got: " + response;
    }

    @GetMapping("/call")
    public String call() {
        log.info("Inside call() method");
        return "Hello from /call";
    }
}
