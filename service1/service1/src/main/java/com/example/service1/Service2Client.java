package com.example.service1;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "service-2", url = "http://localhost:8080") // Match with service-2's `spring.application.name`
public interface Service2Client {

    @GetMapping("/service2")
    public ResponseEntity<String> get();
}


