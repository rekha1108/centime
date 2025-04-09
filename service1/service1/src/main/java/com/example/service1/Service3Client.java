package com.example.service1;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "service-3", url = "http://localhost:8082")
public interface Service3Client {

    @PostMapping("/service3")
    public ResponseEntity<JsonDto> getPassedJson(@RequestBody JsonDto jsonDto);
}

