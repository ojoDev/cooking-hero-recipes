package com.ojodev.cookinghero.recipes.infrastructure.repository;

import java.util.List;

import com.ojodev.cookinghero.recipes.domain.enume.UpsertResultEnum;
import com.ojodev.cookinghero.recipes.infrastructure.po.RecipePO;

public interface RecipesRepository {
    
	RecipePO findRecipeById(String recipeId);
	
	List<RecipePO> findRecipes();
	
	List<RecipePO> findRecipes(String recipeName);
	
	/**
	 * Insert a new recipe in DB
	 * 
	 * @param recipe recipe object
	 * 
	 * @return internal ID of create object
	 */
	String addRecipe(RecipePO recipe);
	
	RecipePO deleteRecipe(String recipeId);

	UpsertResultEnum upsertRecipe(RecipePO recipe);
}