package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.RecipeException;
import com.masai.exception.UserNotFoundException;
import com.masai.model.Recipe;
import com.masai.model.User;
import com.masai.repository.RecipeRepository;
import com.masai.repository.UserRepository;

/**
 * 
 * The RecipeServiceImpl class is the implementation of the RecipeService
 * interface. It provides the functionality to create, retrieve, update, and
 * delete recipes, as well as bookmark and rate recipes. This class uses the
 * RecipeRepository and UserRepository for data access and manipulation.
 * 
 * @Author HoshiyarJyani
 * 
 */
@Service
public class RecipeServiceImpl implements RecipeService {
	@Autowired
	private RecipeRepository recipeRepository;
	@Autowired
	private UserRepository userRepository;

	/**
	 * Creates a new recipe and adds it to the database.
	 *
	 * @param recipe The recipe object containing the details of the recipe to be
	 *               created.
	 * @param userId The ID of the user creating the recipe.
	 * @return The created recipe object.
	 * @throws RecipeException       If an error occurs while creating the recipe.
	 * @throws UserNotFoundException If the user with the specified ID is not found.
	 * 
	 * @author HoshiyarJyani
	 */
	@Override
	public Recipe createRecipe(Recipe recipe, Integer userId) throws RecipeException, UserNotFoundException {

		Optional<User> user = userRepository.findById(userId);
		if (user.isEmpty()) {
			throw new UserNotFoundException("User not found who's User ID is " + userId + " Try with valid user.");
		} else {
			return recipeRepository.save(recipe);
		}

	}

	/**
	 * Retrieves a recipe by its ID.
	 *
	 * @param recipeId The ID of the recipe to be retrieved.
	 * @return The retrieved recipe object.
	 * @throws RecipeException If an error occurs while retrieving the recipe.
	 * 
	 * @author HoshiyarJyani
	 */
	@Override
	public Recipe getRecipeById(Integer recipeId) throws RecipeException {
		Optional<Recipe> recipe = recipeRepository.findById(recipeId);
		if (recipe.isEmpty()) {
			throw new RecipeException("Recipe not found who's Recipe ID is " + recipeId + " Try with valid Id");
		}
		return recipe.get();
	}

	/**
	 * Retrieves a list of all recipes.
	 *
	 * @return The list of all recipe objects.
	 * @throws RecipeException If an error occurs while retrieving the recipes.
	 * 
	 * @author HoshiyarJyani
	 */
	@Override
	public List<Recipe> getAllRecipes() throws RecipeException {
		List<Recipe> list = recipeRepository.findAll();
		if (list.size() == 0) {
			throw new RecipeException("Recipe not found");
		} else {
			return list;
		}

	}

	/**
	 * Updates a recipe with the specified ID.
	 *
	 * @param recipeId The ID of the recipe to be updated.
	 * @param recipe   The updated recipe object.
	 * @param userId   The ID of the user updating the recipe.
	 * @return The updated recipe object.
	 * @throws RecipeException       If an error occurs while updating the recipe.
	 * @throws UserNotFoundException If the user with the specified ID is not found.
	 * 
	 * @author HoshiyarJyani
	 */
	@Override
	public Recipe updateRecipe(Integer recipeId, Recipe recipe, Integer userId)
			throws RecipeException, UserNotFoundException {

		Optional<User> user = userRepository.findById(userId);
		if (user.isEmpty()) {
			throw new UserNotFoundException("User not found who's User ID is " + userId + " Try with valid user.");
		}

		Optional<Recipe> rec = recipeRepository.findById(recipeId);
		if (rec.isEmpty()) {
			throw new RecipeException(
					"Recipe not found who's Recipe ID is " + recipe.getRecipeId() + ". So Unable Update Opration.");
		}

		Recipe exRecipe = rec.get();
		exRecipe.setTitle(recipe.getTitle());
		exRecipe.setDescription(recipe.getDescription());
		exRecipe.setIngredients(recipe.getIngredients());
		exRecipe.setInstructions(recipe.getInstructions());
		exRecipe.setPreparationTime(recipe.getPreparationTime());
		exRecipe.setDifficultyLevel(recipe.getDifficultyLevel());
		return recipeRepository.save(exRecipe);

	}

	/**
	 * Deletes a recipe with the specified ID.
	 *
	 * @param recipeId The ID of the recipe to be deleted.
	 * @param userId   The ID of the user deleting the recipe.
	 * @return The deleted recipe object.
	 * @throws RecipeException       If an error occurs while deleting the recipe.
	 * @throws UserNotFoundException If the user with the specified ID is not found.
	 * 
	 * @author HoshiyarJyani
	 */
	@Override
	public Recipe deleteRecipe(Integer recipeId, Integer userId) throws RecipeException, UserNotFoundException {
		Optional<User> user = userRepository.findById(userId);
		if (user.isEmpty()) {
			throw new UserNotFoundException("User not found who's User ID is " + userId + " Try with valid user.");
		}

		Optional<Recipe> recipe = recipeRepository.findById(recipeId);
		if (recipe.isEmpty()) {
			throw new RecipeException(
					"Recipe not found who's Recipe ID is " + recipeId + ". So Unable Delete Opration.");
		}

		Recipe rec = recipe.get();

		recipeRepository.delete(rec);
		return rec;
	}

	/**
	 * Bookmark a recipe with the specified ID.
	 *
	 * @param recipeId The ID of the recipe to be bookmarked.
	 * @param userId   The ID of the user bookmarking the recipe.
	 * @return A message indicating that the recipe has been successfully
	 *         bookmarked.
	 * @throws UserNotFoundException If the user with the specified ID is not found.
	 * @throws RecipeException       If an error occurs while bookmarking the
	 *                               recipe.
	 * 
	 * @author HoshiyarJyani
	 */
	@Override
	public String bookmarkRecipe(Integer recipeId, Integer userId) throws UserNotFoundException, RecipeException {
		Optional<User> user = userRepository.findById(userId);
		if (user.isEmpty()) {
			throw new UserNotFoundException("User not found who's User ID is " + userId + " Try with valid user.");
		}
		Optional<Recipe> recipe = recipeRepository.findById(recipeId);
		if (recipe.isEmpty()) {
			throw new RecipeException("Recipe not found who's Recipe ID is " + recipeId + ". So Unable to Bookmark.");
		}
		return recipe.get().getTitle() + " Recipe is bookmarked successfully.";
	}

	/**
	 * Rate a recipe with the specified ID.
	 *
	 * @param recipeId The ID of the recipe to be rated.
	 * @param rating   The rating value to be assigned to the recipe.
	 * @param userId   The ID of the user rating the recipe.
	 * @return A message indicating that the recipe has been successfully rated.
	 * @throws UserNotFoundException If the user with the specified ID is not found.
	 * @throws RecipeException       If an error occurs while rating the recipe.
	 * 
	 * @author HoshiyarJyani
	 */
	@Override
	public String rateRecipe(Integer recipeId, Integer rating, Integer userId)
			throws UserNotFoundException, RecipeException {
		Optional<User> user = userRepository.findById(userId);
		if (user.isEmpty()) {
			throw new UserNotFoundException("User not found who's User ID is " + userId + " Try with valid user.");
		}
		Optional<Recipe> recipe = recipeRepository.findById(recipeId);
		if (recipe.isEmpty()) {
			throw new RecipeException("Recipe not found who's Recipe ID is " + recipeId + ". So Unable to Rating.");
		}
		return "Recipe " + recipe.get().getTitle() + " is Rated " + rating + " * successfully.";
	}

}
