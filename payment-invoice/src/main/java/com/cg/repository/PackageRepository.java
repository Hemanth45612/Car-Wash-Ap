package com.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.model.PackageType;

@Repository
public interface PackageRepository extends JpaRepository<PackageType, String>{

	public PackageType findByName(String name);
}
