package com.ojodev.cookinghero.recipes.dao;

import java.util.List;

import com.ojodev.cookinghero.recipes.po.RecipePO;

public interface RecipesRepository {
    
	RecipePO findRecipe(String recipeId);
	
	List<RecipePO> findRecipes();
	
	/**
	 * Insert a new recipe in DB
	 * 
	 * @param recipe recipe object
	 * 
	 * @return internal ID of create object
	 */
	String addRecipe(RecipePO recipe);
	
	RecipePO deleteRecipe(String recipeId);
}