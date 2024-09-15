package com.cg.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.ExceptionResponse.CustomException;
import com.cg.model.Invoice;
import com.cg.model.PackageType;
import com.cg.model.Payment;
import com.cg.model.PaymentType;

import jakarta.validation.Valid;

@Service
public interface InvoiceService {

	public Invoice genrateInvoice(Payment payment, String carId,String addOn, String washid) throws CustomException;

	public List<Invoice> getInvoicesByCustomerId(String id) throws CustomException;
	
    public Invoice getLatestBill(String id) throws CustomException;
    
    public List<Invoice> getAllInvoices() throws CustomException;

    public double calculateAmountByDate(String date) throws CustomException;

	
}
