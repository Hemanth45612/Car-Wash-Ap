package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.exception.InvalidReviewException;
import com.cg.model.Review;
import com.cg.model.WashRequestDto;
import com.cg.service.ReviewService;
import com.cg.service.WashRequestServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @Autowired
    WashRequestServiceImpl requestServiceImpl;
    
    @PostMapping("/add/{washId}")
    @PreAuthorize("hasAuthority('customer')")
    public ResponseEntity<Review> addReview( @PathVariable String washId ,@RequestBody @Valid Review review) throws InvalidReviewException {
       WashRequestDto wash = requestServiceImpl.getWash(washId);
        Review review2 = reviewService.addReview(review, wash.getProfile_id());
        return new ResponseEntity<Review>(review2 ,HttpStatus.OK);
       
    }
    
    @GetMapping("/getAllReviews")
    @PreAuthorize("hasAnyAuthority('Admin','customer')")
    public ResponseEntity<?> getAllReviews() throws InvalidReviewException{
         List<Review> list = reviewService.getAllReviews();
         return new ResponseEntity<Object>(list,HttpStatus.OK);
    }
   
}
