package com.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer> {
	public Review findByCustomerId(String customerid);

}