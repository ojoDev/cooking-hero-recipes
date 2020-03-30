package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.api.model.Recipe;
import com.ojodev.cookinghero.recipes.api.model.RecipeRequest;
import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.domain.enume.UpsertResultEnum;
import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;
import com.ojodev.cookinghero.recipes.infrastructure.repository.RecipesRepository;
import com.ojodev.cookinghero.recipes.mapper.RecipeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class RecipesBusinessImpl_old implements RecipesBusiness_old {
	
	@Autowired
	private RecipesRepository recipesRepository;
	
	@Autowired
	private RecipeMapper recipeMapper;
	
	@Autowired
	private Messages messages;

	public List<Recipe> getRecipes() {
//		List<RecipePO> recipesPOList =recipesRepository.findRecipes();
//		return recipesPOList.stream().map(recipePO -> recipeMapper.toRecipe(recipePO))
//				.collect(Collectors.toList());
		return null;
	}

	@Override
	public boolean existsRecipe(String recipeId) {
		return recipesRepository.existsByObjectId(recipeId);
	}

	public List<Recipe> getRecipes(String recipeName) {
//		List<RecipePO> recipesPOList =recipesRepository.findRecipes(recipeName);
//		return recipesPOList.stream().map(recipePO -> recipeMapper.toRecipe(recipePO))
//							.collect(Collectors.toList());
		return null;
	}
	
	public Recipe getRecipe(String recipeId) throws NotFoundException {
//		RecipePO recipePO = recipesRepository.findRecipeById(recipeId);
//       if (recipePO==null || recipePO.getId() == null) {
//		   throw new NotFoundException(messages.get("error.notfound.code"), messages.get("error.notfound.desc"));
//        }
//		return recipeMapper.toRecipe(recipePO);
		return null;
	}


	public void addRecipe(RecipeRequest recipeRequest) {
//		recipesRepository.addRecipe(recipeMapper.toRecipePO(recipeRequest));
	}
	
	public UpsertResultEnum updateRecipe(Recipe recipe) {
//		return recipesRepository.upsertRecipe(recipeMapper.toRecipePO(recipe));
		return null;
	}
	
	public void deleteRecipe(String recipeId) throws NotFoundException {
//		RecipePO recipe = recipesRepository.deleteRecipe(recipeId);
//		if (recipe == null) {
//			throw new NotFoundException(messages.get("error.notfound.code"), messages.get("error.notfound.desc"));
//		}
	}
	
	  
}
