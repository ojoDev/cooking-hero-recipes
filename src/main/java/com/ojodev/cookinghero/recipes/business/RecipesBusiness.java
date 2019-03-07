package com.ojodev.cookinghero.recipes.business;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ojodev.cookinghero.recipes.bean.Recipe;
import com.ojodev.cookinghero.recipes.bean.RecipeRequest;
import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.converter.mapper.RecipeMapper;
import com.ojodev.cookinghero.recipes.dao.RecipesRepository;
import com.ojodev.cookinghero.recipes.exception.NotFoundException;
import com.ojodev.cookinghero.recipes.po.RecipePO;


@Component
public class RecipesBusiness {
	
	@Autowired
	private RecipesRepository recipesRepository;
	
	@Autowired
	private RecipeMapper recipeMapper;
	
	@Autowired
	private Messages messages;

	public List<Recipe> getRecipes() {
		List<RecipePO> recipesPOList =recipesRepository.findRecipes();
		return recipesPOList.stream().map(recipePO -> recipeMapper.toRecipe(recipePO))
				.collect(Collectors.toList());
	}
	
	public List<Recipe> getRecipes(String recipeName) {
		List<RecipePO> recipesPOList =recipesRepository.findRecipes(recipeName);
		return recipesPOList.stream().map(recipePO -> recipeMapper.toRecipe(recipePO))
							.collect(Collectors.toList());
	}
	
	public Recipe getRecipe(String recipeId) throws NotFoundException {
		RecipePO recipePO = recipesRepository.findRecipeById(recipeId);
       if (recipePO==null || recipePO.getId() == null) {
        	throw new NotFoundException();
        }
		return recipeMapper.toRecipe(recipePO);
	}


	public void addRecipe(RecipeRequest recipeRequest) {
		recipesRepository.addRecipe(recipeMapper.toRecipePO(recipeRequest));
	}
	
	public void deleteRecipe(String recipeId) throws NotFoundException {
		RecipePO recipe = recipesRepository.deleteRecipe(recipeId);
		if (recipe == null) {
			throw new NotFoundException();
		}
	}
	
	  
}
