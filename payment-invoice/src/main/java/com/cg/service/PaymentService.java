package com.cg.service;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cg.ExceptionResponse.CustomException;
import com.cg.dto.WashRequestDto;
import com.cg.model.Invoice;
import com.cg.model.PackageType;
import com.cg.model.Payment;
import com.cg.model.PaymentType;


@Service
public interface PaymentService  {
	
	public Invoice createPaymet(WashRequestDto dto) throws CustomException ;
	
	public boolean processPayment(Payment payment) throws CustomException;
	
	public Invoice getWash(String washId) throws CustomException;


	



	
	

}
