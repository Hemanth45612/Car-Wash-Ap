package com.cg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import com.cg.feign.WashRequestClient;
import com.cg.model.WashRequestDto;


@Component
public class WashRequestServiceImpl {

	@Autowired
	private WashRequestClient requestClient;
	
	public WashRequestDto getWash(String username) {
	   ResponseEntity<WashRequestDto> washRequestById = requestClient.getWashRequestById(username);
		return washRequestById.getBody();
	}
}
