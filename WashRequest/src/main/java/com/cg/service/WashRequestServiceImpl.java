package com.cg.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.cg.entity.UserProfile;
import com.cg.entity.WashRequest;
//import com.cg.feign.ProfileAuthentication;
import com.cg.repository.UserProfileRepository;
import com.cg.repository.WashRequestRepository;

import jakarta.persistence.Id;
import jakarta.servlet.http.HttpServletRequest;

@Service
@Configuration
public class WashRequestServiceImpl implements WashRequestService {

	@Autowired
	private WashRequestRepository requestRepository;
//	@Autowired
//	private ProfileAuthentication profileClient;

	@Autowired
	UserProfileRepository profileRepository;

	@Override
	public String addWashRequest(WashRequest wash, String carId, String username) {
	
	  wash.setProfile_id(username);
	  wash.setCar_id(carId);
	
		wash.setScheduleDate(LocalDateTime.now());
		
		requestRepository.save(wash);
		return "Wash Request added successfully";
	}

	@Override
	public List<WashRequest> getAllWashRequests() {
		return requestRepository.findAll();
	}

	@Override
	public WashRequest getWashRequestById(String id) {
		return requestRepository.findById(id).orElseThrow(() -> new RuntimeException("Wash Request not found"));
	}

	@Override
	public String updateWashRequest(String id, WashRequest washRequest, String username) {
		WashRequest existingRequest = getWashRequestById(id);
		if (existingRequest.getProfile_id().equals(username)) {
			throw new RuntimeException("You are not authorized to update this wash request");
		}

		existingRequest.setWashPackage(washRequest.getWashPackage());
		existingRequest.setScheduleDate(washRequest.getScheduleDate());
		existingRequest.setAddOns(washRequest.getAddOns());

		requestRepository.save(existingRequest);
		return "Wash Request updated successfully";
	}

	@Override
	public String deleteWashRequest(String id, String username) {
		WashRequest existingRequest = getWashRequestById(id);
//		UserProfile profile = profileClient.getUserByUsername(username);
//        if (existingRequest.getProfile_id() != profile.getProfileId()) {
//            throw new RuntimeException("You are not authorized to delete this wash request");
//        }

		requestRepository.deleteById(id);
		return "Wash Request deleted successfully";
	}

//
//	@Autowired
//	WashRequestRepository requestRepository;
//	
//	@Autowired
//	ProfileServiceClient profileClient;
//	
//	UserProfileRepository profileRepository;
//	@Override
//	public List<WashRequest> disPlayAllRequests() {
//          List<WashRequest> list = requestRepository.findAll();
//          return list;
//	}
//
//	@Override
//	public String updateWashPackage(String washRequestId , WashPackage pack) {
//		Optional<WashRequest> wash= requestRepository.findById(washRequestId);
//		if(wash.isPresent()) {
//			WashRequest request = wash.get();
//			request.setWashPackage(pack);
//			requestRepository.save(request);
//			return "Wash request package is updated";
//		}
//		return "Wash request is not found";
//		
//	}
//
//	@Override
//	public String deleteWashRequest(String washRequestId) {
//	    Optional<WashRequest> request = requestRepository.findById(washRequestId);
//	    if(request.isPresent()) {
//	    	requestRepository.deleteById(washRequestId);
//	    	return "Wash request deleted";
//	    }
//		return "wash request not found";
//	}
//
//	@Override
//	public String updateDate(String washRequestId, LocalDateTime date) {
//		Optional<WashRequest> request = requestRepository.findById(washRequestId);
//		if(request.isPresent()) {
//			WashRequest washRequest = request.get();
//			washRequest.setScheduleDate(date);
//			requestRepository.save(washRequest);
//			return "Wash request date is updated";
//		}
//		return "wash request not found";
//	}
//
//	@Override
//	public String updateAddOns(String washRequestId, String addOns) {
//		Optional<WashRequest> request = requestRepository.findById(washRequestId);
//	    if(request.isPresent()) {
//	    	WashRequest request2 = request.get();
//	    	request2.setAddOns(addOns);
//	    	requestRepository.save(request2);
//	    	return"wash request add ons updated";
//	    }
//		return "Wash request not updated";
//	}
//
//	@Override
//	public WashRequest updateWashRequest(String washRequestId, WashRequest wash, String userId, long carId) {
//		return null;
//	}
//
//	//dto operations
//	//to call another rest api
//	@Bean
//	public RestTemplate getRestTemplate() {
//		return new RestTemplate();
//	}
//
////	@Override
////	public String createWashRequest(WashRequest request , String custId ,long carId ){
////		WashRequest request2 = requestRepository.save(request);
////		
////		String carUrl="http://localhost:8081/car/getdetails/"+carId;
////		ResponseEntity<CarDto> entity = getRestTemplate().getForEntity(carUrl, CarDto.class);
////		CarDto carDto = entity.getBody();
////		
////		if(request2 != null) {
////			return "Request successfully placed";
////		}
////		return "request failed";
////	}
//
//	@Override
//	public String addWashRequest(WashRequest wash,String carId) {
//		
//		Optional<WashRequest> washRequest = requestRepository.findById(wash.getWashRequestId());
//		if(!washRequest.isPresent()) {
//			UserProfile profile = profileClient.getUserByUsername(carId);
//			CustomerDto customerDto = new CustomerDto();
//			customerDto.setProfileId(profile.getProfileId());
//			wash.setProfile_id(customerDto.getProfileId());
//			wash.setCar_id(carId);
//			WashRequest save = requestRepository.save(wash);
//			return "Wash Request added";
//		}
//		else
//		{
//			throw new RuntimeException("Wash Request already present");
//		}
//		
//	}
//
//
//	
//	
//
////	@Override
////	public WashRequest updateWashRequest(String washRequestId, WashRequest wash ,String customerId , long carId) {
////		Optional<WashRequest> request = requestRepository.findById(washRequestId);
////		if(request.isPresent()) {
////			WashRequest request2 = request.get();
////			request2.setAddOns(wash.getAddOns());
////			request2.setWashPackage(wash.getWashPackage());
////			request2.setScheduleDate(wash.getScheduleDate());
////			request2.setAddOns(wash.getAddOns());
////			request2.setCustomerId(customerId);
////			request2.setCarId(carId);
////			return requestRepository.save(request2);
////		}
////		else
////		{
////			return null;
////		}
////	}
//
//	
//	
	@Override
	public UserProfile getUserDetails(String username) {
//		UserProfile userProfile = profileClient.getUserByUsername(username);
		UserProfile byUsername = profileRepository.findByUsername(username);
		return byUsername;
	}
//	
//	
}
