package com.cg.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.UserProfile;
import com.cg.repository.UserProfileRepository;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class UserSericeImpl implements  UserService{

	Logger log=LoggerFactory.getLogger(UserSericeImpl.class);
	
	@Autowired
	UserProfileRepository userProfileRepository;
	
	@Override
	public String deleteByUsername(String username) {
		userProfileRepository.deleteByUsername(username);
		log.info("User deleted");
		return "user is deleted";
		
	}

	@Override
	public String updateUserDetails(UserProfile user) {
		Optional<UserProfile> byId = userProfileRepository.findById(user.getProfileId());
		
		if(byId.isPresent()) {
			UserProfile profile = byId.get();
			profile.setDateOfBirth(user.getDateOfBirth());
			profile.setAbout(user.getAbout());
			profile.setEmailId(user.getEmailId());
			profile.setGender(user.getGender());
			profile.setMobileNumber(user.getMobileNumber());
			profile.setPassword(user.getPassword());
			userProfileRepository.save(profile);
			log.info("User profile updated "+user.getProfileId() +" with this Id");
			return "User profile is updated";
		}
		else
		{
			throw new RuntimeException("User Not Found");
		}
	}

	@Override
	public UserProfile getUser(String username) {
		return userProfileRepository.findByUsername(username);
	}

	@Override
	public List<UserProfile> getAllCustomers() {
		List<UserProfile> all = userProfileRepository.findAll();
		ArrayList<UserProfile> list = new ArrayList<>();
		for(UserProfile u : all) {
			if(u.getRole().equals("customer")) {
				list.add(u);
			}
		}
		if(!list.isEmpty()) {
			log.info("Customers are found");
			return list;
		}
		else
		{
		   log.error("Customers Are not Found");
		   throw new RuntimeException("No Customers found");
			
		}
	}



}
