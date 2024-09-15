package com.cg.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ExceptionResponse.CustomException;
import com.cg.dto.WashRequestDto;
import com.cg.feign.WashRequestClient;
import com.cg.model.Invoice;
import com.cg.model.PackageType;
import com.cg.model.PaymentRequestDTO;
import com.cg.model.PaymentType;
import com.cg.service.InvoiceService;
import com.cg.service.PackageService;
import com.cg.service.PackageServiceimp;
import com.cg.service.PaymentService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private PackageService packageService;

	@Autowired
	InvoiceService invoiceService;

	
	
	@PostMapping("/admin/addpackages")
	@PreAuthorize("hasAnyAuthority('admin')")
	public ResponseEntity<String> addpackages(@Valid @RequestBody PackageType type) throws CustomException {
		String packageTypes = packageService.addPackageTypes(type);
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(packageTypes, HttpStatus.OK);
		return responseEntity;

	}
	

	@PostMapping("/Updatepackages")
	@PreAuthorize("hasAnyAuthority('admin')")
	public ResponseEntity<String> Updatepackage(@RequestBody PackageType type) throws CustomException {
		String packageTypes = packageService.updatePackageName(type);
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(packageTypes, HttpStatus.OK);
		return responseEntity;

	}

	
	@GetMapping("/getwash/{wash}")
	@PreAuthorize("hasAnyAuthority('customer')")
	public ResponseEntity<Invoice> getWash(@PathVariable String wash) throws CustomException {         
		Invoice invoice = paymentService.getWash(wash);
		ResponseEntity<Invoice> responseEntity=new ResponseEntity<Invoice>(invoice, HttpStatus.OK);
		return responseEntity;
	}
	
	
	//user
//	@PostMapping("/process-payment")
//	public ResponseEntity<Invoice> processPayment(@Valid @RequestBody PaymentRequestDTO dto) throws Exception {
//
//		
//		ResponseEntity<Invoice> responseEntity = new ResponseEntity<Invoice>(paymet, HttpStatus.OK);
//		return responseEntity;
//
//	}
	
	//this method is use for display all bills by customer id
	
		//admin//user
		@GetMapping("/getBillsByCustomerId/{id}")
		@PreAuthorize("hasAnyAuthority('admin', 'customer')")
		public ResponseEntity<List<Invoice>> getBillsByCustomerId(@PathVariable String id) throws Exception {
			List<Invoice> displaybills = invoiceService.getInvoicesByCustomerId(id);
			ResponseEntity<List<Invoice>> responseEntity = new ResponseEntity<List<Invoice>>(displaybills, HttpStatus.OK);
			return responseEntity;

		}

	
	@GetMapping("/getpackageType/{name}")
	@PreAuthorize("hasAnyAuthority('admin')")
	public ResponseEntity<PackageType> findbyname( @PathVariable String name) throws Exception {
		PackageType packageType = packageService.findPackageType(name);
		ResponseEntity<PackageType> responseEntity = new ResponseEntity<PackageType>(packageType, HttpStatus.OK);
		return responseEntity;

	}

	//this method implementing displaying all customers bills
	
		
		@GetMapping("/getAllBills")
		@PreAuthorize("hasAnyAuthority('admin')")
		public ResponseEntity<List<Invoice>> getAllBills() throws Exception {
			List<Invoice> displaybills = invoiceService.getAllInvoices();
			ResponseEntity<List<Invoice>> responseEntity = new ResponseEntity<List<Invoice>>(displaybills, HttpStatus.OK);
			return responseEntity;

		}

		// this method is used for display latest bill
		
	
		@GetMapping("/latestBill/{id}")
		@PreAuthorize("hasAnyAuthority('admin', 'customer')")
		public ResponseEntity<Invoice> latestBill(@PathVariable String id) throws CustomException {
			Invoice latestBill = invoiceService.getLatestBill(id);
			ResponseEntity<Invoice> responseEntity = new ResponseEntity<Invoice>(latestBill, HttpStatus.OK);

			return responseEntity;

		}
	
	//this method returns the total amount between the given date to current date
	
	@GetMapping("/gettotalAmountBydate/{date}")
	@PreAuthorize("hasAnyAuthority('admin', 'customer')")
	public ResponseEntity<Double> getTotalAmount(@PathVariable String date) throws CustomException {
		System.out.println("hlo");
	double amount = invoiceService.calculateAmountByDate(date);
	ResponseEntity<Double> responseEntity = new ResponseEntity<Double>(amount, HttpStatus.OK);

		return responseEntity;	

	}
	
	
	
	@DeleteMapping("/delete/{packageName}")
	@PreAuthorize("hasAnyAuthority('customer')")
	public ResponseEntity<String> deletePackageByName(@PathVariable String packageName) throws CustomException{
		String msg = packageService.deletePackageBasedOnPackageName(packageName);
		ResponseEntity<String> responseEntity=new ResponseEntity<String>(msg, HttpStatus.OK);
		return responseEntity;
	}
	
}
	
	
	
	
	

