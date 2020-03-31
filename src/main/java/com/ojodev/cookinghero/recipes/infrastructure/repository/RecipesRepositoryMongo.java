package com.ojodev.cookinghero.recipes.infrastructure.repository;

import java.util.List;

import com.ojodev.cookinghero.recipes.domain.enume.UpsertResultEnumBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.RecipeMongoPO;

public interface RecipesRepositoryMongo {
    
	RecipeMongoPO findRecipeById(String recipeId);
	
	List<RecipeMongoPO> findRecipes();
	
	List<RecipeMongoPO> findRecipes(String recipeName);
	
	/**
	 * Insert a new recipe in DB
	 * 
	 * @param recipe recipe object
	 * 
	 * @return internal ID of create object
	 */
	String addRecipe(RecipeMongoPO recipe);
	
	RecipeMongoPO deleteRecipe(String recipeId);

	UpsertResultEnumBO upsertRecipe(RecipeMongoPO recipe);
}