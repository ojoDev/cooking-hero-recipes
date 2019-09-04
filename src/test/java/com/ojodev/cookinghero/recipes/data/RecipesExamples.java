package com.ojodev.cookinghero.recipes.data;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.bson.types.Decimal128;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import com.ojodev.cookinghero.recipes.bean.PhotoRef;
import com.ojodev.cookinghero.recipes.bean.Recipe;
import com.ojodev.cookinghero.recipes.bean.RecipeRequest;
import com.ojodev.cookinghero.recipes.constants.RecipeConstants;
import com.ojodev.cookinghero.recipes.po.RecipePO;

@Component
public class RecipesExamples {
	

	public static final String RECIPE_ID_01 = "0123456789ab0123456789ab";
	public static final String RECIPE_NAME_01 = "Spanish ommelete";
	public static final String RECIPE_DESCRIPTION_01 = "Classic Spanish omelette filled with pan-fried potatoes and onion.";
	public static final String RECIPE_ID_02 = "0123456789ab0123456789cd";
	public static final String RECIPE_NAME_02 = "Spaguetti";
	public static final String RECIPE_DESCRIPTION_02 = "Italian pasta with tomato";
	public static final String RECIPE_01_CUISINE_TYPE_01 = "spanish";
	public static final String RECIPE_01_CUISINE_TYPE_02 = "veggie";
	
	public static final Decimal128 RECIPE_PREPARATION_TIME_01 = new Decimal128(15);
	public static final Decimal128 RECIPE_DIFFICULTY_01 = new Decimal128(3);
	
	public static final String RECIPE_PHOTO_ID_01 = "21344123123";
	//TODO DMS Add local server
	public static final String RECIPE_PHOTO_HREF_01 = RecipeConstants.MEDIA_PHOTO_URI+"/"+RECIPE_PHOTO_ID_01;
	
	public static final String RECIPE_01_PHOTO_REF = "0123456789ab01234111111aa";
	public static final String RECIPE_USER_01 = "admin";
	public static final String RECIPE_CREATION_DATE_01_STRING = "2019-02-12T21:39:45.618Z";
	public static final DateTime RECIPE_CREATION_DATE_01 = new DateTime(RECIPE_CREATION_DATE_01_STRING);

	public static final RecipePO RECIPE_01 = initRecipe01();
	public static final RecipePO RECIPE_02 = initRecipe02();
	public static final List<RecipePO> RECIPE_LIST_TWO_RECIPES = Arrays.asList(RECIPE_01, RECIPE_02);
	public static final List<RecipePO> RECIPE_LIST_ONE_RECIPE = Arrays.asList(RECIPE_02);
	
	public static final BigDecimal RECIPE_REQUEST_PREPARATION_TIME= new BigDecimal(15);
	public static final BigDecimal RECIPE_REQUEST_COOKING_TIME = new BigDecimal(30);
	public static final BigDecimal RECIPE_REQUEST_DIFFICULTY = new BigDecimal(3);
	public static final PhotoRef RECIPE_REQUEST_PHOTO = new PhotoRef(RECIPE_PHOTO_HREF_01);
	
	public static final RecipeRequest RECIPE_REQUEST = initRecipeRequest();
	public static final Recipe RECIPE = initRecipe();
	
	
	private static RecipePO initRecipe01() {
		RecipePO recipePO = new RecipePO();
		recipePO.setId(new ObjectId(RECIPE_ID_01));
		recipePO.setName(RECIPE_NAME_01);
		recipePO.setDescription(RECIPE_DESCRIPTION_01);
		recipePO.setCuisineType(Arrays.asList(RECIPE_01_CUISINE_TYPE_01, RECIPE_01_CUISINE_TYPE_02));
		recipePO.setPreparationTime(RECIPE_PREPARATION_TIME_01);
		recipePO.setDifficulty(RECIPE_DIFFICULTY_01);
		recipePO.setPhotoId(RECIPE_PHOTO_ID_01);
		recipePO.setSteps(Arrays.asList(StepsExamples.STEP_01, StepsExamples.STEP_02, StepsExamples.STEP_03));
		recipePO.setIngredients(Arrays.asList(IngredientsExamples.INGREDIENT_01, IngredientsExamples.INGREDIENT_02,
				IngredientsExamples.INGREDIENT_03, IngredientsExamples.INGREDIENT_04,
				IngredientsExamples.INGREDIENT_05));
		recipePO.setUser(RECIPE_USER_01);
		recipePO.setCreationDate(RECIPE_CREATION_DATE_01);
		return recipePO;
	}

	private static RecipePO initRecipe02() {
		RecipePO recipePO = new RecipePO();
		recipePO.setId(new ObjectId(RECIPE_ID_02));
		recipePO.setName(RECIPE_NAME_02);
		recipePO.setDescription(RECIPE_DESCRIPTION_02);
		return recipePO;
	}
	
	private static RecipeRequest initRecipeRequest() {
		RecipeRequest recipeRequest = new RecipeRequest();
		recipeRequest.setName(RECIPE_NAME_01);
		recipeRequest.setDescription(RECIPE_DESCRIPTION_01);
		recipeRequest.setCuisineType(Arrays.asList(RECIPE_01_CUISINE_TYPE_01, RECIPE_01_CUISINE_TYPE_02));
		recipeRequest.setPreparationTime(RECIPE_REQUEST_PREPARATION_TIME);;
		recipeRequest.setDifficulty(RECIPE_REQUEST_DIFFICULTY);
		recipeRequest.setPhoto(RECIPE_REQUEST_PHOTO);
		recipeRequest.setSteps(Arrays.asList(StepsExamples.STEP_REQUEST_01, StepsExamples.STEP_REQUEST_02));
		recipeRequest.setIngredients(Arrays.asList(IngredientsExamples.INGREDIENT_REQUEST_01, IngredientsExamples.INGREDIENT_REQUEST_02));
		recipeRequest.setUser(RECIPE_USER_01);
		return recipeRequest;
	}
	
	private static Recipe initRecipe() {
		Recipe recipe = new Recipe();
		recipe.setId(RECIPE_ID_01);
		recipe.setName(RECIPE_NAME_01);
		recipe.setDescription(RECIPE_DESCRIPTION_01);
		recipe.setCuisineType(Arrays.asList(RECIPE_01_CUISINE_TYPE_01, RECIPE_01_CUISINE_TYPE_02));
		recipe.setPreparationTime(RECIPE_REQUEST_PREPARATION_TIME);
		recipe.setDifficulty(RECIPE_REQUEST_DIFFICULTY);
		recipe.setPhoto(RECIPE_REQUEST_PHOTO);
		recipe.setSteps(Arrays.asList(StepsExamples.STEP_REQUEST_01, StepsExamples.STEP_REQUEST_02));
		recipe.setIngredients(Arrays.asList(IngredientsExamples.INGREDIENT_REQUEST_01, IngredientsExamples.INGREDIENT_REQUEST_02));
		recipe.setUser(RECIPE_USER_01);
		return recipe;
	}

}
