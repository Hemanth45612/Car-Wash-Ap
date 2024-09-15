package com.cg.model;



import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WashRequestDto {

	
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