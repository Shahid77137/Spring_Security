package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
	
}
