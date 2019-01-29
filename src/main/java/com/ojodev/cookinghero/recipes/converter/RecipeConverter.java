package com.ojodev.cookinghero.recipes.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.Decimal128;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ojodev.cookinghero.recipes.bean.PhotoRef;
import com.ojodev.cookinghero.recipes.bean.Recipe;
import com.ojodev.cookinghero.recipes.bean.RecipeRequest;
import com.ojodev.cookinghero.recipes.po.RecipePO;

@Component
public class RecipeConverter {

	@Autowired
	private StepConverter stepConverter;
	
	@Autowired
	private IngredientConverter ingredientConverter;

	public Recipe toRecipe(RecipePO recipePO) {
		Recipe recipe = new Recipe();
		if (recipePO != null) {
			recipe.setId(recipePO.getId().toString());
			recipe.setName(recipePO.getName());
			recipe.setDescription(recipePO.getDescription());
			recipe.setCousineType(recipePO.getCousineType());
			recipe.setPreparationTime(recipePO.getPreparationTime()==null ? null : recipePO.getPreparationTime().bigDecimalValue());
			recipe.setCookingTime(recipePO.getCookingTime()==null ? null : recipePO.getCookingTime().bigDecimalValue());
			recipe.setDifficulty(recipePO.getCookingTime()==null ? null : recipePO.getDifficulty().bigDecimalValue());
			recipe.setPhoto(recipePO.getPhotoId()==null ? null : new PhotoRef(recipePO.getPhotoId()));
			recipe.setSteps(stepConverter.toSteps(recipePO.getSteps()));
			recipe.setIngredients(ingredientConverter.toIngredients(recipePO.getIngredients()));
			recipe.setUser(recipePO.getUser());
			recipe.setCreationDate(recipePO.getCreationDate());
			
		}
		return recipe;
	}
	
	public List<Recipe> toRecipes(List<RecipePO> recipesPO) {
		return recipesPO == null ? null : recipesPO.stream().map(e ->  toRecipe(e)).collect(Collectors.toList());
	}
	
	public RecipePO toRecipePO(RecipeRequest recipe) {
		RecipePO recipePO = new RecipePO();
		if (recipe != null) {
			recipePO.setName(recipe.getName());
			recipePO.setDescription(recipe.getDescription());
			recipePO.setCousineType(recipe.getCousineType());
			recipePO.setCookingTime(recipe.getCookingTime()==null ? null : new Decimal128(recipe.getCookingTime()));  //TODO: Cambiar por Optional, si procede con Mongo
			recipePO.setPreparationTime(recipe.getPreparationTime()==null ? null : new Decimal128(recipe.getPreparationTime()));		
			recipePO.setDifficulty(recipe.getDifficulty()==null ? null : new Decimal128(recipe.getDifficulty()));
			recipePO.setPhotoId(recipe.getPhoto()==null ? null : recipe.getPhoto().getHref());
			recipePO.setSteps(stepConverter.toStepsPO(recipe.getSteps()));
			recipePO.setIngredients(ingredientConverter.toIngredientsPO(recipe.getIngredients()));
			//TODO DMS: Obtain user from OAuth token
			recipePO.setUser("ADMIN");
			recipePO.setCreationDate(new DateTime());
			
		}
		return recipePO;
	}

}
