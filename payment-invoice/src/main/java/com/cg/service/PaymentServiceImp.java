package com.cg.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cg.ExceptionResponse.CustomException;
import com.cg.dto.WashRequestDto;
import com.cg.feign.WashRequestClient;
import com.cg.model.Invoice;
import com.cg.model.PackageType;
import com.cg.model.Payment;
import com.cg.model.PaymentType;
import com.cg.repository.PaymentRepository;

import jakarta.transaction.Transactional;

@Component
public class PaymentServiceImp implements PaymentService {

//	@Autowired
	// private Payment paym;

	@Autowired
	private InvoiceService invoiceService;

	@Autowired
	private PackageService packageService;

	@Autowired
	private PaymentRepository paymentRepository;

//    public boolean processPayment(Payment payment) {
//    
//        if (payment != null && payment.getAmount() > 0) {
//        	
//        	
//            System.out.println("Payment processed successfully for userId: " + payment.getUserId());
//            return true;
//        } else {
//            System.out.println("Payment failed for userId: " + payment.getUserId());
//            return false;
//        }
//    
//    }

	@Override
	public Invoice createPaymet(WashRequestDto Dto) throws CustomException {
		Payment paym = new Payment();
		paym.setCustomerId(Dto.getProfile_id());
		paym.setDate(LocalDateTime.now());
		paym.setPaymentType(Dto.getWashPackage());

		PackageType packageType2 = packageService.findPackageType(Dto.getWashPackage());

		paym.setTax(packageType2.getGst() * packageType2.getPrice());

		paym.setAmount(packageType2.getPrice() + paym.getTax());
		boolean paymentSuccess = processPayment(paym);
		if (paymentSuccess) {

			Invoice genrateInvoice = invoiceService.genrateInvoice(paym, Dto.getCar_id(), Dto.getAddOns(),
					Dto.getWashRequestId());

			return genrateInvoice;
		} else {
			throw new CustomException("Something went worng.please try again");
		}
	}

	public boolean processPayment(Payment payment) throws CustomException {

		Payment save = paymentRepository.save(payment);
		if (save != null && save.getAmount() > 0) {
			return true;
		} else {
			throw new CustomException("Something went worng");
		}

	}

	@Autowired
	WashRequestClient requestClient;

	@Override
	public Invoice getWash(String washId) throws CustomException {

		WashRequestDto wash = requestClient.getWashRequestById(washId);

		if (wash.getStatus().equalsIgnoreCase("completed")) {
			Invoice invoice = createPaymet(wash);
			if (invoice == null) {
				throw new CustomException("Something went wrong.please try again.");
			}
			return invoice;
		} else {
			throw new CustomException("Something went wrong.please try again.");
		}

	}

}
