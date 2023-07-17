package com.masai.Model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int BookId;
	@NotEmpty
	@NotNull
	@NotBlank
	private String title;
	@NotEmpty
	@NotNull
	@NotBlank
	private String author;
	@NotEmpty
	@NotNull
	@NotBlank
	@org.hibernate.validator.constraints.ISBN
	@Column(unique = true)
	private String ISBN;
	@NotEmpty
	@NotNull
	@NotBlank
	private String description;
	@NotEmpty
	@NotNull
	@NotBlank
	private double price; 
	private LocalDate publicationdate;
	private boolean availabilitystatus;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "book")
	private List<User> user;

}
