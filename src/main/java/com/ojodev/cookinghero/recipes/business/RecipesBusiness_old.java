package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.api.model.Recipe;
import com.ojodev.cookinghero.recipes.api.model.RecipeRequest;
import com.ojodev.cookinghero.recipes.domain.enume.UpsertResultEnumBO;
import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface RecipesBusiness_old {

	List<Recipe> getRecipes();

	boolean existsRecipe(String recipeId);
	
	List<Recipe> getRecipes(String recipeName);
	
	Recipe getRecipe(String recipeId) throws NotFoundException;

	void addRecipe(RecipeRequest recipeRequest);
	
	UpsertResultEnumBO updateRecipe(Recipe recipe);
	
	void deleteRecipe(String recipeId) throws NotFoundException;
	  
}
