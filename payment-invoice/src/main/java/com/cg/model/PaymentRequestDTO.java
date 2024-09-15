package com.cg.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class PaymentRequestDTO {

		

//	    @NotNull(message = "User ID cannot be null")
//	    @Positive(message = "User ID must be positive")
//	    private int userid;

	    @NotBlank(message = "Name cannot be empty")
	    @Size(max = 100, message = "Name cannot exceed 100 characters")
	    private String name;

	    @NotNull(message = "Wash ID cannot be null")
	    @Positive(message = "Wash ID must be positive")
	    private int washid;

	    @NotBlank(message = "Package Type cannot be empty")
	 //   @Pattern(regexp = "basic|premium|deluxe", message = "Invalid package type")
	    private String packageType;

	    @NotBlank(message = "Payment Type cannot be empty")
	    @Pattern(regexp = "CREDIT|DEBIT|UPI", message = "Invalid payment type")
	    private String paymentType;
//		public int getUserid() {
//			return userid;
//		}
//		public void setUserid(int userid) {
//			this.userid = userid;
//		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getWashid() {
			return washid;
		}
		public void setWashid(int washid) {
			this.washid = washid;
		}
		public String getPackageType() {
			return packageType;
		}
		public void setPackageType(String packageType) {
			this.packageType = packageType;
		}
		public String getPaymentType() {
			return paymentType;
		}
		public void setPaymentType(String paymentType) {
			this.paymentType = paymentType;
		}
		public PaymentRequestDTO(
//				@NotNull(message = "User ID cannot be null") @Positive(message = "User ID must be positive") int userid,
				@NotBlank(message = "Name cannot be empty") @Size(max = 100, message = "Name cannot exceed 100 characters") String name,
				@NotNull(message = "Wash ID cannot be null") @Positive(message = "Wash ID must be positive") int washid,
				@NotBlank(message = "Package Type cannot be empty") @Pattern(regexp = "basic|premium|deluxe", message = "Invalid package type") String packageType,
				@NotBlank(message = "Payment Type cannot be empty") @Pattern(regexp = "credit|debit|upi", message = "Invalid payment type") String paymentType) {
			super();
//			this.userid = userid;
			this.name = name;
			this.washid = washid;
			this.packageType = packageType;
			this.paymentType = paymentType;
		}
	    
	    
	public	PaymentRequestDTO(){
			
		}
	   
	}


