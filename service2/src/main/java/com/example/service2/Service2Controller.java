package com.example.service2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class Service2Controller {

    private static final Logger log = LoggerFactory.getLogger(Service2Controller.class);

    @GetMapping("/service2")
    public ResponseEntity<String> get(){
        String traceId = UUID.randomUUID().toString(); // unique trace ID per request
        log.info("[traceId: {}] Received GET request in service 2", traceId);
        try {
            return ResponseEntity.ok("Hello");
        }
        catch (Exception e) {
            log.error("[traceId: {}] Error during GET request in service 2 : {}", traceId, e.getMessage(), e);
            return ResponseEntity.internalServerError().body("Error occurred while processing your request.");
        }
    }
}
