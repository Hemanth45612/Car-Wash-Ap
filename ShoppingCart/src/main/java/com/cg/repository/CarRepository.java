package com.cg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entity.Car;
import com.cg.entity.UserProfile;

@Repository
public interface CarRepository extends JpaRepository<Car, String>{
    List<Car> findByUser(UserProfile user);
}
