package com.cg.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "car")
public class Car {

	@Id
	private String carId;

	@NotBlank(message = "Car model is required")
	@Pattern(regexp = "^[A-Za-z0-9\\s]+$", message = "Car model must contain only alphanumeric characters and spaces")
	private String carModel;

	@NotBlank(message = "Car number plate is required")
	@Pattern(regexp = "^[A-Z0-9\\-]+$", message = "Car number plate must contain only uppercase letters, digits, and dashes")
	private String carNumberPlate;

	@ManyToOne
	@JoinColumn(name = "profileId") // This is the foreign key column in the `car` table
	private UserProfile user;
}