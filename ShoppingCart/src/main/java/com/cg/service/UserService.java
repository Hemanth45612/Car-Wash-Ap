package com.cg.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.entity.UserProfile;

@Service
public interface UserService {

	String deleteByUsername(String userName);
	String updateUserDetails(UserProfile profile);
	UserProfile getUser(String username);
    List<UserProfile> getAllCustomers();
}
