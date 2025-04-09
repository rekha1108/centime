package com.example.service3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class Service3Controller {

    private static final Logger log = LoggerFactory.getLogger(Service3Controller.class);


    @PostMapping("/service3")
    public ResponseEntity<JsonDto> getPassedJson(@RequestBody JsonDto jsonDto){
        String traceId = UUID.randomUUID().toString(); // unique trace ID per request
        log.info("[traceId: {}] Received POST request in service 3", traceId);
        try {
            return ResponseEntity.ok(jsonDto);
        }
        catch (Exception e) {
            log.error("[traceId: {}] Error during POST request in service 3 : {}", traceId, e.getMessage(), e);
            JsonDto errorDto = new JsonDto("Error", "Occurred");
            return ResponseEntity.internalServerError().body(errorDto);
        }
    }

}
