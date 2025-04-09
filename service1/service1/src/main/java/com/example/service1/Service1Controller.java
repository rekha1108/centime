package com.example.service1;


import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/service1")
@Validated
public class Service1Controller {

    private static final Logger log = LoggerFactory.getLogger(Service1Controller.class);

    @Autowired
    Service2Client service2Client;

    @Autowired
    Service3Client service3Client;

    @GetMapping("/health")
    public ResponseEntity<String> get(){
        return ResponseEntity.ok("Up");
    }

    @PostMapping
    public ResponseEntity<ResponseDto> processJson(@Valid @RequestBody  JsonDto dto){
        String traceId = UUID.randomUUID().toString(); // unique trace ID per request
        log.info("[traceId: {}] Received POST request with payload: {}", traceId, dto);
        try {
            String hello = service2Client.get().getBody();
            JsonDto jsonDto = service3Client.getPassedJson(dto).getBody();
            String result = hello + " " + jsonDto.getFirstName() + " " + jsonDto.getSecondName();
            ResponseDto responseDto = new ResponseDto(result);
            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            log.error("[traceId: {}] Error during orchestration: {}", traceId, e.getMessage(), e);
            ResponseDto errorDto = new ResponseDto("Error occurred while processing your request.");
            return ResponseEntity.internalServerError().body(errorDto);
        }
    }


}
