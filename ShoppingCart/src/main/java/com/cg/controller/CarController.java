package com.cg.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Car;
import com.cg.entity.UserProfile;
import com.cg.repository.CarRepository;
import com.cg.repository.UserProfileRepository;
import com.cg.service.CarService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/carController")
public class CarController {

    @Autowired
    CarService carService;
    
    @Autowired
    UserProfileRepository profileRepository;
    
    @Autowired
     CarRepository carRepository;
    
     @PostMapping("/addcars")
    @PreAuthorize("hasAuthority('customer')")    
    public String addCarDetails(Principal p, @RequestBody Car car) {
    	String name = p.getName();
    	UserProfile user = profileRepository.findByUsername(name);
    	if(user != null) {
    		car.setUser(user);
    		return carService.addCarDetails(car);
    	}
    	else
    	{
    		throw new RuntimeException("User not Registered");
    	}
    }
    
    @PutMapping("/updateCarDetails")
    @PreAuthorize("hasAuthority('customer')")
    public String updateCarDetails( Principal p , HttpServletRequest request,@RequestBody Car c) {
    	String name = p.getName();
    	UserProfile userProfile = profileRepository.findByUsername(name);
    		if(name.equals(userProfile.getUsername())) { 
    			return carService.updateCarDetails(c);
    		}
    		else
    		{
    			throw new RuntimeException("User not allowed to update the details");
    		}
    	
    }
    
    @GetMapping("/deleteCar/{carId}")
    @PreAuthorize("hasAuthority('customer')")
    public String deleteCar(Principal p,  @PathVariable String carId) {
    	String name = p.getName();
    	UserProfile userProfile = profileRepository.findByUsername(name);
    	
    		Optional<Car> car = carRepository.findById(carId);
    		if(car.isPresent())
    		{
    			return carService.deleteCar(carId);
    		}
    		else
    		{
    			throw new RuntimeException("User not registered this car");
    		}
    	
    } 
    
    @GetMapping("/getCarById/{carId}")
    @PreAuthorize("hasAnyAuthority('Admin','customer')")
    public Car getCarById(@PathVariable String carId) {
    	Optional<Car> car = carRepository.findById(carId);
    	if(car.isPresent()) {
    		return carService.getCarById(carId);
    	}
    	else
    	{
    		throw new RuntimeException("User Not registered the car details");
    	}
    }
    
    @GetMapping("/getAllCars")
    @PreAuthorize("hasAnyAuthority('Admin')")
    public List<Car> getAllCars(){
    	return carService.getAllCars();
    }
    
    
    @GetMapping("/getAllCustomerCars")
    @PreAuthorize("hasAnyAuthority('customer')")
    public List<Car> getAllCustomerCars(Principal p , HttpServletRequest request) {
    	return carService.getAllCustomerCars(p.getName());
    }
    
}
