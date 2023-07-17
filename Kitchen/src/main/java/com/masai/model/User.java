package com.masai.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer UserId;
	@NotBlank(message = "Username is Required")
	@Size(min = 4, max = 20, message = "Size Should between 4-20 char")
	@Column(unique = true)
	private String username;
	
	@NotBlank(message = "This Is Required Field")
	@Size(min = 4, max = 20, message = "Size Should between 4-20 char")
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=]).*$", message = "Password should contain at least 1 uppercase letter, 1 lowercase letter, 1 special character, and 1 digit")
	private String password;
	@Email
	@NotBlank
	private String email;

	private LocalDate registrationDate;
}
