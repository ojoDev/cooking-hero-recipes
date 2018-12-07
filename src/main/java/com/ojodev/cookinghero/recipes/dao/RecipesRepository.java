package com.ojodev.cookinghero.recipes.dao;

import java.util.List;

import com.ojodev.cookinghero.recipes.po.RecipePO;

public interface RecipesRepository {
    
	List<RecipePO> findRecipes();
	
	void addRecipe(RecipePO recipe);
}