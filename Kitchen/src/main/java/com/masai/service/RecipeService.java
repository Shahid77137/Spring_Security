package com.masai.service;

import java.util.List;

import com.masai.exception.RecipeException;
import com.masai.exception.UserNotFoundException;
import com.masai.model.Recipe;

/**
 * 
 * The RecipeService interface defines the contract for interacting with
 * recipe-related functionality. This interface provides methods
 * for adding, retrieving, updating, and deleting recipes, as well as
 * bookmarking and rating recipes. The implementation of this interface should
 * handle any exceptions related to recipe operations and user not found.
 * 
 * @author HoshiyarJyani
 * 
 */
public interface RecipeService {
	
	/**
	 * Creates a new recipe and adds it to the database.
	 *
	 * @param recipe The recipe object containing the details of the recipe to be created.
	 * @param userId The ID of the user creating the recipe.
	 * @return The created recipe object.
	 * @throws RecipeException      If an error occurs while creating the recipe.
	 * @throws UserNotFoundException If the user with the specified ID is not found.
	 * 
	 * @author HoshiyarJyani
	 */
	public Recipe createRecipe(Recipe recipe, Integer userId) throws RecipeException, UserNotFoundException;

	/**
	 * Retrieves a recipe by its ID.
	 *
	 * @param recipeId The ID of the recipe to be retrieved.
	 * @return The retrieved recipe object.
	 * @throws RecipeException If an error occurs while retrieving the recipe.
	 * 
	 * @author HoshiyarJyani
	 */

	public Recipe getRecipeById(Integer recipeId) throws RecipeException;


	

/**
 * Retrieves a list of all recipes.
 *
 * @return The list of all recipe objects.
 * @throws RecipeException If an error occurs while retrieving the recipes.
 * 
 * @author HoshiyarJyani
 */
	public List<Recipe> getAllRecipes() throws RecipeException;

	
	/**
	 * Updates a recipe with the specified ID.
	 *
	 * @param recipeId The ID of the recipe to be updated.
	 * @param recipe   The updated recipe object.
	 * @param userId   The ID of the user updating the recipe.
	 * @return The updated recipe object.
	 * @throws RecipeException      If an error occurs while updating the recipe.
	 * @throws UserNotFoundException If the user with the specified ID is not found.
	 * 
	 * @author HoshiyarJyani
	 */
	public Recipe updateRecipe(Integer recipeId, Recipe recipe, Integer userId)
			throws RecipeException, UserNotFoundException;

	/**
	 * Deletes a recipe with the specified ID.
	 *
	 * @param recipeId The ID of the recipe to be deleted.
	 * @param userId   The ID of the user deleting the recipe.
	 * @return The deleted recipe object.
	 * @throws RecipeException      If an error occurs while deleting the recipe.
	 * @throws UserNotFoundException If the user with the specified ID is not found.
	 * 
	 * @author HoshiyarJyani
	 */
	public Recipe deleteRecipe(Integer recipeId, Integer userId) throws RecipeException, UserNotFoundException;


	/**
	 * Bookmark a recipe with the specified ID.
	 *
	 * @param recipeId The ID of the recipe to be bookmarked.
	 * @param userId   The ID of the user bookmarking the recipe.
	 * @return A message indicating that the recipe has been successfully bookmarked.
	 * @throws UserNotFoundException If the user with the specified ID is not found.
	 * @throws RecipeException      If an error occurs while bookmarking the recipe.
	 * 
	 * @author HoshiyarJyani
	 */
	public String bookmarkRecipe(Integer recipeId, Integer userId) throws UserNotFoundException, RecipeException;

	/**
	 * Rate a recipe with the specified ID.
	 *
	 * @param recipeId The ID of the recipe to be rated.
	 * @param rating   The rating value to be assigned to the recipe.
	 * @param userId   The ID of the user rating the recipe.
	 * @return A message indicating that the recipe has been successfully rated.
	 * @throws UserNotFoundException If the user with the specified ID is not found.
	 * @throws RecipeException      If an error occurs while rating the recipe.
	 * 
	 * @author HoshiyarJyani
	 */
	public String rateRecipe(Integer recipeId, Integer rating, Integer userId)
			throws UserNotFoundException, RecipeException;
}
