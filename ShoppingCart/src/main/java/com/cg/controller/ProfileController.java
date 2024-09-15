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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.UserProfile;
import com.cg.repository.UserProfileRepository;
import com.cg.service.UserService;

@RestController
@RequestMapping("/profileController")
public class ProfileController {

	@Autowired
	UserService userService;

	@Autowired
	UserProfileRepository  profileRepository;
	
	@DeleteMapping("/delete")
	@PreAuthorize("hasAnyAuthority('Admin')")
	public ResponseEntity<?> deleteByUsername(Principal principal) {
		String userName = principal.getName();
		userService.deleteByUsername(userName);
		return new ResponseEntity<String>("user is deleted", HttpStatus.OK);
	}
	
	
	@PutMapping("/updateUser")
	@PreAuthorize("hasAuthority('customer')")
	public ResponseEntity<?>updateUser(@RequestBody UserProfile user){
		String details = userService.updateUserDetails(user);
		return new ResponseEntity<Object>(details , HttpStatus.OK);
	}
	
	@GetMapping("/getAllCustomers")
	@PreAuthorize("hasAuthority('Admin')")
	public ResponseEntity<?> getAllCustomers(){
		List<UserProfile> list = userService.getAllCustomers();
		return new ResponseEntity<Object>(list,HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/user/{username}")
	public ResponseEntity<UserProfile> getUserByUsername(@PathVariable String username) {
		return ResponseEntity.ok(userService.getUser(username));
	}
	
	
	@GetMapping("/getuserbyname")
	@PreAuthorize("hasAnyAuthority('Admin','customer')")
	public ResponseEntity<UserProfile> getUserByUsername1(@PathVariable String username) {
		UserProfile user = profileRepository.findByUsername(username);
		return new ResponseEntity<UserProfile>(user, HttpStatus.OK);
	}
	
}

