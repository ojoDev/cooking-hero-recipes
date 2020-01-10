package com.ojodev.cookinghero.recipes.business;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ojodev.cookinghero.recipes.api.model.Recipe;
import com.ojodev.cookinghero.recipes.api.model.RecipeRequest;
import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.mapper.RecipeMapper;
import com.ojodev.cookinghero.recipes.infrastructure.repository.RecipesRepository;
import com.ojodev.cookinghero.recipes.domain.enume.UpsertResultEnum;
import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;
import com.ojodev.cookinghero.recipes.infrastructure.po.RecipePO;


@Component
public interface RecipesBusiness {

	List<Recipe> getRecipes();
	
	List<Recipe> getRecipes(String recipeName);
	
	Recipe getRecipe(String recipeId) throws NotFoundException;

	void addRecipe(RecipeRequest recipeRequest);
	
	UpsertResultEnum updateRecipe(Recipe recipe);
	
	void deleteRecipe(String recipeId) throws NotFoundException;
	  
}
