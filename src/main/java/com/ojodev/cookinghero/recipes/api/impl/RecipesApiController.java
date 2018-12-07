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
import com.ojodev.cookinghero.recipes.bean.ApiError;
import com.ojodev.cookinghero.recipes.bean.Recipe;
import com.ojodev.cookinghero.recipes.bean.RecipeUpdate;
import com.ojodev.cookinghero.recipes.business.RecipesBusiness;

import io.swagger.annotations.ApiParam;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-12-06T11:16:24.847Z[GMT]")

@Controller
public class RecipesApiController implements RecipesApi {

    private static final Logger log = LoggerFactory.getLogger(RecipesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
	@Autowired
	private RecipesBusiness recipeBusiness;
	

    @Autowired
    public RecipesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> addRecipe(@ApiParam(value = "Recipe to add"  )  @Valid @RequestBody Recipe body) {
        String accept = request.getHeader("Accept");
        recipeBusiness.addRecipe(body);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    public ResponseEntity<Void> deleteRecipe(@ApiParam(value = "recipe id",required=true) @PathVariable("recipe_id") String recipeId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ApiError> getRecipe(@ApiParam(value = "recipe id",required=true) @PathVariable("recipe_id") String recipeId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<ApiError>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Recipe>> getRecipes(@ApiParam(value = "user that create the recipe") @Valid @RequestParam(value = "user", required = false) String user,@Min(0)@ApiParam(value = "number of records to skip for pagination", allowableValues = "") @Valid @RequestParam(value = "skip", required = false) Integer skip,@Min(0) @Max(50) @ApiParam(value = "maximum number of records to return", allowableValues = "") @Valid @RequestParam(value = "limit", required = false) Integer limit) {
        String accept = request.getHeader("Accept");	
        List<Recipe> recipeList = recipeBusiness.getRecipes();
        return new ResponseEntity<List<Recipe>>(recipeList, HttpStatus.OK);
    }

    public ResponseEntity<Void> updateRecipe(@ApiParam(value = "recipe id",required=true) @PathVariable("recipe_id") String recipeId,@ApiParam(value = "Recipe to update"  )  @Valid @RequestBody RecipeUpdate body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
