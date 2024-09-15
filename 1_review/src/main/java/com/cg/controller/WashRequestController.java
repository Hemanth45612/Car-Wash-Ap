package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.cg.model.WashRequestDto;
import com.cg.service.WashRequestServiceImpl;

@RestController
@RequestMapping("/washrequestcontroller")
public class WashRequestController {

    @Autowired
    private WashRequestServiceImpl washRequestService;

    @GetMapping("/request/{id}")
    @PreAuthorize("hasAnyAuthority('customer')")
    public ResponseEntity<WashRequestDto> getWashRequest(@PathVariable String id) {
        WashRequestDto washRequest = washRequestService.getWash(id);
        return new ResponseEntity<WashRequestDto>(washRequest, HttpStatus.OK); 
    }
}
