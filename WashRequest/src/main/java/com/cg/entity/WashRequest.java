package com.cg.entity;

import java.time.LocalDateTime;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WashRequest {

	@Id
	private String washRequestId;
	//@Enumerated(EnumType.STRING)
	private String washPackage;
	//@Enumerated(EnumType.ORDINAL)
	private String status;
	private LocalDateTime scheduleDate;
	private String addOns;
    private String profile_id;
    private String car_id;
	
	
	
	
	
	
	
	
	
	
	
}


//	@OneToOne(cascade = CascadeType.ALL)
//	private Car cars;
//	
//	@OneToOne(cascade = CascadeType.ALL)
//	private UserProfile customers;