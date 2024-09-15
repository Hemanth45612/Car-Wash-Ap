package com.cg.dto;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WashRequestDto {

	@Id
	private String washRequestId;
	private String washPackage;
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