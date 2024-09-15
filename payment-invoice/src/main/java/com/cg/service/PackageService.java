package com.cg.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.cg.ExceptionResponse.CustomException;
import com.cg.model.PackageType;

@Service
public interface PackageService {
	
	

	public String addPackageTypes(PackageType packageType) throws CustomException;

	public PackageType findPackageType(String packageType) throws CustomException;
	
    public String updatePackageName(PackageType packageType) throws CustomException;
	
    public String deletePackageBasedOnPackageName(String name) throws CustomException ;
}
