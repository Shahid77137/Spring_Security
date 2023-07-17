package com.masai.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer recipeId;
	@NotBlank(message = "Title is Required Field")
	private String title;
	@NotBlank
	private String description;
	@NotBlank(message = "Ingredients is Required Field")
	private String ingredients;

	private String instructions;
	@NotBlank
	@Positive(message = "Preparation Time must be a positive number")
	private Integer preparationTime;

	@Enumerated(EnumType.STRING)
	private DifficultyLevel difficultyLevel;

}
