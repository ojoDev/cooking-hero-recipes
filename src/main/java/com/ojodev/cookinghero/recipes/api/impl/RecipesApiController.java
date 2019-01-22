package com.ojodev.cookinghero.recipes.api.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

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
import com.ojodev.cookinghero.recipes.bean.RecipeUpdate;
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

    public ResponseEntity<Void> addRecipe(@ApiParam(value = "Recipe to add"  )  @Valid @RequestBody Recipe body) {
        recipeBusiness.addRecipe(body);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    public ResponseEntity<Void> deleteRecipe(@ApiParam(value = "recipe id",required=true) @PathVariable("recipe-id") String recipeId) throws ApiException {
    	recipeBusiness.deleteRecipe(recipeId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Recipe> getRecipe(@ApiParam(value = "recipe id",required=true) @PathVariable("recipe-id") String recipeId) throws ApiException{
        Recipe recipe = recipeBusiness.getRecipe(recipeId);
        return new ResponseEntity<Recipe>(recipe, HttpStatus.OK);
    }

    public ResponseEntity<List<Recipe>> getRecipes(@ApiParam(value = "user that create the recipe") @Valid @RequestParam(value = "user", required = false) String user,@Min(0)@ApiParam(value = "number of records to skip for pagination", allowableValues = "") @Valid @RequestParam(value = "skip", required = false) Integer skip,@Min(0) @Max(50) @ApiParam(value = "maximum number of records to return", allowableValues = "") @Valid @RequestParam(value = "limit", required = false) Integer limit) {	
        List<Recipe> recipeList = recipeBusiness.getRecipes();
        return new ResponseEntity<List<Recipe>>(recipeList, HttpStatus.OK);
    }

    public ResponseEntity<Void> updateRecipe(@ApiParam(value = "recipe id",required=true) @PathVariable("recipe-id") String recipeId,@ApiParam(value = "Recipe to update"  )  @Valid @RequestBody RecipeUpdate body) {
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
