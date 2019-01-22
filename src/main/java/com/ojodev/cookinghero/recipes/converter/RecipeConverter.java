package com.ojodev.cookinghero.recipes.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.Decimal128;
import org.springframework.stereotype.Component;

import com.ojodev.cookinghero.recipes.bean.Recipe;
import com.ojodev.cookinghero.recipes.po.RecipePO;

@Component
public class RecipeConverter {

	public Recipe toRecipe(RecipePO recipePO) {
		Recipe recipe = new Recipe();
		if (recipePO != null) {
			recipe.setId(recipePO.getId().toString());
			recipe.setName(recipePO.getName());
			recipe.setDescription(recipePO.getDescription());
			recipe.setCousineType(recipePO.getCousineType());
			recipe.setLength(recipePO.getLength().bigDecimalValue());
			recipe.setDifficulty(recipePO.getDifficulty().bigDecimalValue());			
		}
		return recipe;
	}
	
	public List<Recipe> toRecipes(List<RecipePO> recipesPO) {
		return recipesPO == null ? null : recipesPO.stream().map(e ->  toRecipe(e)).collect(Collectors.toList());
	}
	
	public RecipePO toRecipePO(Recipe recipe) {
		RecipePO recipePO = new RecipePO();
		if (recipe != null) {
			recipePO.setName(recipe.getName());
			recipePO.setDescription(recipe.getDescription());
			recipePO.setCousineType(recipe.getCousineType());
			recipePO.setLength(new Decimal128(recipe.getLength()));
			recipePO.setDifficulty(new Decimal128(recipe.getDifficulty()));
		}
		return recipePO;
	}

}
