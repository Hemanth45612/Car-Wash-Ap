package com.cg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.Car;
import com.cg.entity.UserProfile;
import com.cg.repository.CarRepository;
import com.cg.repository.UserProfileRepository;

@Service
public class CarServiceImpl implements CarService{

	@Autowired
	CarRepository carRepository;
	
	@Autowired
	UserProfileRepository profileRepository;
	

	@Override
	public String addCarDetails(Car car){
		Optional<Car> newCar = carRepository.findById(car.getCarId());
		if(newCar.isPresent()) {
			throw new RuntimeException("Car details already saved");
		}
		else {
			
			carRepository.save(car);	
			return "Car details added";
		}
		
	}


	@Override
	public String updateCarDetails(Car c) {
		Optional<Car> byId = carRepository.findById(c.getCarId());
		if(byId.isPresent()) {
			carRepository.save(c);
			return "Car details are updated";
		}
		else
		{
			throw new RuntimeException("Car With CarId is not found");
		}
		
	}

	@Override
	public String deleteCar(String carId) {
		Optional<Car> car = carRepository.findById(carId);
		if(car.isPresent()) {
			carRepository.deleteById(carId);
			return "Car details deleted";
		}
		else
		{
			throw new RuntimeException("Car details are not registered");
		}
	}

	@Override
	public Car getCarById(String carId) {
        Optional<Car> newCar = carRepository.findById(carId);
        if(newCar.isPresent()) {
        	return newCar.get();
        }
        else
        {
        	throw new RuntimeException("Car details not found");
        }
	}

	@Override
	public List<Car> getAllCars() {
        List<Car> all = carRepository.findAll();
        if(all.isEmpty()) {
        	throw new RuntimeException("no cars registered in the database");
        }
        else
        {
        	return all;
        }
	}


	@Override
	public List<Car> getAllCustomerCars(String username) {
		UserProfile userProfile = profileRepository.findByUsername(username);
		if(userProfile != null) {
			List<Car> byUser = carRepository.findByUser(userProfile);
			if(byUser.isEmpty()) {
				throw new RuntimeException("User not registered any cars");
			}
			else
			{
				return byUser;
			}
			
		}
		else
		{
			throw new RuntimeException("User not registered");
		}
	}

}
