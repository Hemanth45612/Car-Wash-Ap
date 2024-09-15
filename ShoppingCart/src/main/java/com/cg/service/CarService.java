package com.cg.service;

import java.util.List;

import com.cg.entity.Car;

public interface CarService {
  
   String addCarDetails(Car c);
   String updateCarDetails(Car c);
   String deleteCar(String carId);
   Car getCarById(String carId);
   List<Car>getAllCustomerCars(String username);
   List<Car> getAllCars();
  
}
