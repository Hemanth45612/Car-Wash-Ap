package com.cg.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "userlogin")
public class UserProfile {

	@Id
	//@NotNull(message = "Profile Id is required")
	//@Min(value = 1, message = "Profile Id must be greaterthan zero")
	private int profileId;
	//@NotBlank(message = "username is required")
	//@Size(max = 30, message = "Username must be less than or equal to 30 characters")
	@Column(nullable = false , unique = true)
	private String username;
	//@Email(message="Valid email is required")
	private String emailId;
	//@Column(unique=true)
	//@NotNull(message = "Mobile number is required")
	//@Min(value=6000000000L, message="invalid mobile number")
	//@Max(value=9999999999L,message="invalid mobile number")
	private long mobileNumber;
	private String about;
	private LocalDate dateOfBirth;
	private String gender;
	private String role;
	private String password;
	
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Car> cars;
	
	
	
	
	
}

//@OneToMany(cascade = CascadeType.ALL)
//@JoinColumn(name="profileId")