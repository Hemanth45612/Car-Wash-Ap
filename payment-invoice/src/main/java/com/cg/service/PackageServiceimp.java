package com.cg.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.cg.ExceptionResponse.CustomException;
import com.cg.model.PackageType;
import com.cg.repository.PackageRepository;


@Service
public class PackageServiceimp implements PackageService {

	@Autowired
	private PackageRepository packageRepository;

	@Autowired
	PackageType packageType;
	

	public String addPackageTypes(PackageType packageType) throws CustomException {
		
		PackageType byName = packageRepository.findByName(packageType.getName());
		
		
		if(byName!=null) {
			throw new CustomException("Package already Exist");
		}

		packageType.setName(packageType.getName());
		packageType.setPrice(packageType.getPrice());
		packageType.setGst(packageType.getGst());
		PackageType save = packageRepository.save(packageType);
		if(save!=null) {
			return "updated Sucessfully";
		}
		throw new CustomException("Update failed.Something went wrong");
		
	}
	
	
	public PackageType findPackageType(String name) throws CustomException  {
     
		PackageType byName = packageRepository.findByName(name);
		
		if(byName!=null) {
			return byName;
		}
		
		throw new CustomException("Package not found");
	}


	public String updatePackageName(PackageType packageType) throws CustomException {
		
		PackageType byName = packageRepository.findByName(packageType.getName());
		if(byName!=null) {
			byName.setName(packageType.getName());
			byName.setPrice(packageType.getPrice());
			byName.setGst(packageType.getGst());
			packageRepository.save(byName);
			return "Updated sucessfully";
		}
		
	
		throw new CustomException("No Package Found"); 
		
	}
	
	

	@Override
	public String deletePackageBasedOnPackageName(String name) throws CustomException {

		packageRepository.deleteById(name);

		return "deleted sucessfully";

	}

	
	
}
