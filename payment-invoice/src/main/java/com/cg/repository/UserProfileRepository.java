package com.cg.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.model.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer>{
Optional<UserProfile> findUserIdByUsername(String username);
	
	UserProfile findByUsername(String username);
}
