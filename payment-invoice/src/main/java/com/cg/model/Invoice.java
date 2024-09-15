package com.cg.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long invoiceNumber;

	@NotNull(message = "Date cannot be null")
	private LocalDate date;

//	@Min(value = 1, message = "User ID must be greater than 0")
//	private int Userid;

//	@Min(value = 1, message = "Wash ID must be greater than 0")
	private String washid;

//	@NotBlank(message = "Username cannot be empty")
//	@Size(max = 255, message = "Username cannot exceed 255 characters")
//	private String Username;

//	@NotBlank(message = "Package type cannot be empty")
//	@Size(max = 255, message = "Package type cannot exceed 255 characters")
//	private String packagetype;

	private String addOn;
	
    
	  private String CustomerId;

	@DecimalMin(value = "0.0", inclusive = false, message = "Total amount must be greater than 0")
	private double totalamount;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "paymentid")
	
	private Payment payment;
	

}