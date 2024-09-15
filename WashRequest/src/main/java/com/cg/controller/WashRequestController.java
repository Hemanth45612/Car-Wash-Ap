package com.cg.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.UserProfile;
import com.cg.entity.WashRequest;
import com.cg.service.WashRequestService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/washrequestcontroller")
public class WashRequestController {

	@Autowired
	private WashRequestService requestService;

	@PostMapping("/addWashRequest/{carId}")
	@PreAuthorize("hasAuthority('customer')")
	public ResponseEntity<String> addWashRequest(Principal principal, @RequestBody WashRequest wash,
			@PathVariable String carId) {
		String result = requestService.addWashRequest(wash, carId, principal.getName());
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	
	@GetMapping("/allRequests")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<List<WashRequest>> getAllWashRequests() {
        List<WashRequest> requests = requestService.getAllWashRequests();
        return ResponseEntity.ok(requests);
    }

	@GetMapping("/request/{id}")
	@PreAuthorize("hasAnyAuthority('Admin', 'customer')")
	//@PreAuthorize("hasAuthority('customer')")
	public ResponseEntity<WashRequest> getWashRequestById(@PathVariable String id) {
		WashRequest request = requestService.getWashRequestById(id);
		return ResponseEntity.ok(request);
	}

	@PutMapping("/updateRequest/{id}")
	@PreAuthorize("hasAuthority('customer')")
	public ResponseEntity<String> updateWashRequest(@PathVariable String id, @RequestBody WashRequest washRequest,
			Principal principal) {
		String result = requestService.updateWashRequest(id, washRequest, principal.getName());
		return ResponseEntity.ok(result);
	}

	@DeleteMapping("/deleteRequest/{id}")
	@PreAuthorize("hasAuthority('customer')")
	public ResponseEntity<String> deleteWashRequest(@PathVariable String id, Principal principal) {
		String result = requestService.deleteWashRequest(id, principal.getName());
		return ResponseEntity.ok(result);
	}
	
	
	@GetMapping("/getuser")
	@PreAuthorize("hasAuthority('customer')")
    public UserProfile getUserByUsername(Principal p , HttpServletRequest request) {
		String name = p.getName();
        return requestService.getUserDetails(name);
    }
	
	
}
