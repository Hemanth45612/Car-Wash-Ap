package com.cg.service;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cg.exception.InvalidReviewException;
import com.cg.model.Review;
import com.cg.repository.ReviewRepository;

import lombok.extern.slf4j.Slf4j;

@Service
//@Slf4j
public class ReviewServiceImpl implements ReviewService {

	Logger log = LoggerFactory.getLogger(ReviewServiceImpl.class);
	
	@Autowired
	private ReviewRepository reviewRepository;


	@Override
	public Review addReview(Review r,String customerId)throws InvalidReviewException {
		Review s=r;
		    s.setCustomerId(customerId);
		    log.info("Review Saved");
		    Review review = reviewRepository.save(s);
		    return review;
	}

	@Override
	public List<Review> getAllReviews() throws InvalidReviewException {
		List<Review> list = reviewRepository.findAll();
		
		if(!list.isEmpty()) {
			log.info("All reviews Are Found");
			return list;
		}
		else
		{
			log.error("No reviews are found");
			throw new InvalidReviewException("No reviews found");
		}
	}

}


	