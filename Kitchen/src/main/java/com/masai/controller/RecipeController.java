package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.RecipeException;
import com.masai.exception.UserNotFoundException;
import com.masai.model.Recipe;
import com.masai.service.RecipeService;
/**
 * The RecipeController class handles the HTTP requests related to recipe operations.
 * It exposes endpoints for creating, retrieving, updating, and deleting recipes, as well as bookmarking and rating recipes
 *
 * Endpoints:
 * - POST /recipes: Create a new recipe.
 * - GET /recipes/{recipeId}: Retrieve a specific recipe by ID.
 * - GET /recipes: Retrieve a list of all recipes.
 * - PUT /recipes/{recipeId}: Update an existing recipe.
 * - DELETE /recipes/{recipeId}: Delete a recipe by ID.
 * - POST /recipes/{recipeId}/bookmark: Bookmark a recipe.
 * - POST /recipes/{recipeId}/rate: Rate a recipe.
 * 
 * Important - All endpoints require authentication and the user ID is passed as a parameter.
 * 
 * The RecipeController returns ResponseEntity objects with appropriate HTTP status codes
 * and response bodies containing the recipe information or error messages.
 * 
 * @author HoshiyarJyani
 */
@RestController
@RequestMapping("/recipes")
public class RecipeController {
	@Autowired
	private RecipeService recipeService;

	
   /**
     * Create a new recipe.
     *
     * @param recipe The recipe object to be created.
     * @param userId The ID of the user creating the recipe.
     * @return The created recipe.
     * @throws RecipeException       If there is an error while creating the recipe.
     * @throws UserNotFoundException If the user with the given ID is not found.
     * 
     * @author HoshiyarJyani
     */
	
	@PostMapping
	public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe, @RequestParam Integer userId)
			throws RecipeException, UserNotFoundException {

		Recipe myRecipe = recipeService.createRecipe(recipe, userId);
		return new ResponseEntity<>(myRecipe, HttpStatus.CREATED);
	}

	 /**
     * Retrieve a specific recipe by ID.
     *
     * @param recipeId The ID of the recipe to retrieve.
     * @return The recipe with the specified ID.
     * @throws RecipeException If the recipe with the given ID is not found.
     * 
     * @author HoshiyarJyani
     */
	@GetMapping("/{recipeId}")
	public ResponseEntity<Recipe> getRecipeById(@PathVariable Integer recipeId) throws RecipeException {

		Recipe recipe = recipeService.getRecipeById(recipeId);
		return new ResponseEntity<>(recipe, HttpStatus.OK);

	}
	 /**
     * Retrieve a list of all recipes.
     *
     * @return A list of all recipes.
     * @throws RecipeException If no recipes are found.
     * 
     * @author HoshiyarJyani
     */
	@GetMapping
	public ResponseEntity<List<Recipe>> getAllRecipes() throws RecipeException {

		List<Recipe> recipes = recipeService.getAllRecipes();
		return new ResponseEntity<>(recipes, HttpStatus.OK);

	}

   /**
     * Update an existing recipe.
     *
     * @param recipeId The ID of the recipe to update.
     * @param recipe   The updated recipe object.
     * @param userId   The ID of the user updating the recipe.
     * @return The updated recipe.
     * @throws RecipeException       If there is an error while updating the recipe.
     * @throws UserNotFoundException If the user with the given ID is not found.
     * 
     * @author HoshiyarJyani
     */
	@PutMapping("/{recipeId}")
	public ResponseEntity<Recipe> updateRecipe(@PathVariable Integer recipeId, @RequestBody Recipe recipe,
			@RequestParam Integer userId) throws RecipeException, UserNotFoundException {

		Recipe updatedRecipe = recipeService.updateRecipe(recipeId, recipe, userId);
		return new ResponseEntity<>(updatedRecipe, HttpStatus.OK);

	}

   /**
     * Delete a recipe by ID.
     *
     * @param recipeId The ID of the recipe to delete.
     * @param userId   The ID of the user deleting the recipe.
     * @return The deleted recipe.
     * @throws RecipeException       If there is an error while deleting the recipe.
     * @throws UserNotFoundException If the user with the given ID is not found.
     * 
     * @author HoshiyarJyani
     * 
     */
	@DeleteMapping("/{recipeId}")
	public ResponseEntity<Recipe> deleteRecipe(@PathVariable Integer recipeId, @RequestParam Integer userId)
			throws RecipeException, UserNotFoundException {

		Recipe deletedRecipe = recipeService.deleteRecipe(recipeId, userId);
		return new ResponseEntity<>(deletedRecipe, HttpStatus.OK);

	}

	
   /**
     * Bookmark a recipe.
     *
     * @param recipeId The ID of the recipe to bookmark.
     * @param userId   The ID of the user bookmarking the recipe.
     * @return A message indicating the success of the bookmark operation.
     * @throws UserNotFoundException If the user with the given ID is not found.
     * @throws RecipeException       If the recipe with the given ID is not found.
     * 
     * @author HoshiyarJyani
     */
	@PostMapping("/{recipeId}/bookmark")
	public ResponseEntity<String> bookmarkRecipe(@PathVariable Integer recipeId, @RequestParam Integer userId)
			throws UserNotFoundException, RecipeException {

		String msg = recipeService.bookmarkRecipe(recipeId, userId);
		return new ResponseEntity<>(msg, HttpStatus.OK);

	}

	/**
     * Rate a recipe.
     *
     * @param recipeId The ID of the recipe to rate.
     * @param rating   The rating value.
     * @param userId   The ID of the user rating the recipe.
     * @return A message indicating the success of the rating operation.
     * @throws UserNotFoundException If the user with the given ID is not found.
     * @throws RecipeException       If the recipe with the given ID is not found.
     * 
     * @author HoshiyarJyani
     */
	@PostMapping("/{recipeId}/rate")
	public ResponseEntity<String> rateRecipe(@PathVariable Integer recipeId, @RequestParam Integer rating,
			@RequestParam Integer userId) throws UserNotFoundException, RecipeException {

		String message = recipeService.rateRecipe(recipeId, rating, userId);
		return new ResponseEntity<>(message, HttpStatus.OK);

	}
}
