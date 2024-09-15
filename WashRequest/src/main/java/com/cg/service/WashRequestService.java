package com.cg.service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.entity.UserProfile;
import com.cg.entity.WashRequest;

import jakarta.servlet.http.HttpServletRequest;


public interface WashRequestService {

	String addWashRequest(WashRequest wash,String username, String carId);
	 public List<WashRequest> getAllWashRequests() ;
	 public WashRequest getWashRequestById(String id) ;
	 public String updateWashRequest(String id, WashRequest washRequest, String username) ;
	 public String deleteWashRequest(String id, String username);
	
	 
	 
	 UserProfile getUserDetails(String username);
}
