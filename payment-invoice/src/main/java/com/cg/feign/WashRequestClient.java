package com.cg.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cg.dto.WashRequestDto;

import feign.Headers;

@Component
@FeignClient(name = "washrequest")
public interface WashRequestClient {
	@GetMapping("/washrequestcontroller/request/{id}")
	//@Headers("Authorization: Bearer {token}")
	public WashRequestDto getWashRequestById(@PathVariable String id);
	
	
}
