package com.example.tracingdemo;

import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class HelloController {

  private static final Logger log = LoggerFactory.getLogger(HelloController.class);

  @Autowired
  private WebClient.Builder webClientBuilder;

  @Autowired
  private Tracer tracer;

  @GetMapping("/hi")
  public Mono<String> hi() {
    Span span = tracer.nextSpan().name("custom-hi-span").start();
    try (Tracer.SpanInScope scope = tracer.withSpan(span)) {
      log.info("Inside /hi endpoint");

      return webClientBuilder.build()
          .get()
          .uri("http://localhost:8080/call")
          .retrieve()
          .bodyToMono(String.class)
          .map(response -> {
            log.info("Received response from /call");
            return "Hi there! Got: " + response;
          });
    } finally {
      span.end();
    }
  }

  @GetMapping("/call")
  public Mono<String> call() {
    Span span = tracer.nextSpan().name("custom-call-span").start();
    try (Tracer.SpanInScope scope = tracer.withSpan(span)) {
      log.info("Inside /call endpoint");
      return Mono.just("Hello from /call!");
    } finally {
      span.end();
    }
  }
}
