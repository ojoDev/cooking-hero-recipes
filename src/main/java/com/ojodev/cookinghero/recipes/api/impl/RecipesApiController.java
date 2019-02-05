package com.ojodev.cookinghero.recipes.api.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ojodev.cookinghero.recipes.api.RecipesApi;
import com.ojodev.cookinghero.recipes.bean.ApiException;
import com.ojodev.cookinghero.recipes.bean.Recipe;
import com.ojodev.cookinghero.recipes.bean.RecipeRequest;
import com.ojodev.cookinghero.recipes.business.RecipesBusiness;

import io.swagger.annotations.ApiParam;

@Controller
public class RecipesApiController implements RecipesApi {

	private static final Logger log = LoggerFactory.getLogger(RecipesApiController.class);

	@Autowired
	private RecipesBusiness recipeBusiness;

	@Autowired
	public RecipesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
	}

	public ResponseEntity<Void> addRecipe(@ApiParam(value = "Recipe to add") @Valid @RequestBody Recipe body) {
		recipeBusiness.addRecipe(body);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	public ResponseEntity<Void> deleteRecipe(
			@ApiParam(value = "recipe id", required = true) @PathVariable("recipe-id") String recipeId)
			throws ApiException {
		recipeBusiness.deleteRecipe(recipeId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	public ResponseEntity<Recipe> getRecipe(
			@ApiParam(value = "recipe id", required = true) @PathVariable("recipe-id") String recipeId)
			throws ApiException {
		Recipe recipe = recipeBusiness.getRecipe(recipeId);
		return new ResponseEntity<Recipe>(recipe, HttpStatus.OK);
	}

	public ResponseEntity<List<Recipe>> getRecipes(
			@ApiParam(value = "initial date of recipe creation, useful for multiple page lists searches") @Valid @RequestParam(value = "createdFrom", required = false) DateTime createdFrom,
			@ApiParam(value = "recipe name, partial searches allowed") @Valid @RequestParam(value = "name", required = false) String name,
			@ApiParam(value = "user id that create the recipe") @Valid @RequestParam(value = "userId", required = false) String userId,
			@ApiParam(value = "recipe fields returned in response, separated by ','") @Valid @RequestParam(value = "fields", required = false) String fields,
			@Min(1) @ApiParam(value = "number of page for skip (pagination)", allowableValues = "") @Valid @RequestParam(value = "skip", required = false) Integer skip,
			@Min(1) @Max(50) @ApiParam(value = "maximum number of records returned, by default 20", allowableValues = "") @Valid @RequestParam(value = "limit", required = false) Integer limit) {
		List<Recipe> recipeList = recipeBusiness.getRecipes();
		return new ResponseEntity<List<Recipe>>(recipeList, HttpStatus.OK);
	}

	public ResponseEntity<Void> updateRecipe(
			@ApiParam(value = "recipe id", required = true) @PathVariable("recipe-id") String recipeId,
			@ApiParam(value = "Recipe to update") @Valid @RequestBody RecipeRequest body) {
		return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
	}

}
