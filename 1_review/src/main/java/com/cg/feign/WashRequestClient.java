package com.cg.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cg.model.WashRequestDto;

import feign.Headers;


@Service
@FeignClient(name = "washrequest")
public interface WashRequestClient {

    @GetMapping("/washrequestcontroller/request/{id}")
    @Headers("Authorization: Bearer {token}")
    public ResponseEntity<WashRequestDto> getWashRequestById(@PathVariable String id);
}
