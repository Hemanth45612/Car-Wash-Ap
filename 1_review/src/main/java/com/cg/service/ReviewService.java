package com.cg.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.exception.InvalidReviewException;
import com.cg.model.Review;

@Service
public interface ReviewService {
	public Review addReview(Review r,String customerId) throws InvalidReviewException;
	public List<Review> getAllReviews()throws InvalidReviewException;

}
