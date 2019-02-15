package com.ojodev.cookinghero.recipes.api;



import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.joda.time.DateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ojodev.cookinghero.recipes.bean.ApiError;
import com.ojodev.cookinghero.recipes.bean.ApiException;
import com.ojodev.cookinghero.recipes.bean.Recipe;
import com.ojodev.cookinghero.recipes.bean.RecipeRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "recipes")
public interface RecipesApi {
	

  @ApiOperation(value = "searches recipes", nickname = "getRecipes", notes = "Search for recipe list ", response = Recipe.class, responseContainer = "array", tags={ "recipes", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "search results matching criteria", response = Recipe.class, responseContainer = "List"),
        @ApiResponse(code = 204, message = "no results"),
        @ApiResponse(code = 400, message = "bad input parameter", response = ApiError.class),
        @ApiResponse(code = 500, message = "internal server error", response = ApiError.class) })
    @GetMapping(value = "/recipes",
        produces = { "application/json" })
    ResponseEntity<List<Recipe>> getRecipes(@ApiParam(value = "initial date of recipe creation, useful for multiple page lists searches") @Valid @RequestParam(value = "createdFrom", required = false) DateTime createdFrom,@ApiParam(value = "recipe name, partial searches allowed") @Valid @RequestParam(value = "name", required = false) String name,@ApiParam(value = "user id that create the recipe") @Valid @RequestParam(value = "userId", required = false) String userId,@ApiParam(value = "recipe fields returned in response, separated by ','") @Valid @RequestParam(value = "fields", required = false) String fields,@Min(1)@ApiParam(value = "number of page for skip (pagination)", allowableValues = "") @Valid @RequestParam(value = "skip", required = false) Integer skip,@Min(1) @Max(50) @ApiParam(value = "maximum number of records returned, by default 20", allowableValues = "") @Valid @RequestParam(value = "limit", required = false) Integer limit);
        

    @ApiOperation(value = "adds a recipe", nickname = "addRecipe", notes = "Adds an recipe to recipe book", tags={ "recipes", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "recipe created"),
        @ApiResponse(code = 400, message = "invalid input, object invalid", response = ApiError.class),
        @ApiResponse(code = 500, message = "server error", response = ApiError.class) })
    @PostMapping(value = "/recipes",
        produces = { "application/json" }, 
        consumes = { "application/json" })
    ResponseEntity<Void> addRecipe(@ApiParam(value = "Recipe to add"  )  @Valid @RequestBody RecipeRequest body);

    
    @ApiOperation(value = "get recipe by id", nickname = "getRecipe", notes = "Search recipe by id ", response = ApiError.class, tags={ "recipes", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "search results matching criteria", response = ApiError.class),
        @ApiResponse(code = 400, message = "bad input parameter", response = ApiError.class),
        @ApiResponse(code = 404, message = "not found", response = ApiError.class),
        @ApiResponse(code = 500, message = "server error", response = ApiError.class) })
    @GetMapping(value = "/recipes/{recipe-id}",
        produces = { "application/json" })
    ResponseEntity<Recipe> getRecipe(@ApiParam(value = "recipe id",required=true) @PathVariable("recipe-id") String recipeId) throws ApiException;

    @ApiOperation(value = "update recipe", nickname = "updateRecipe", notes = "Update recipe info ", tags={ "recipes", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "recipe updated"),
        @ApiResponse(code = 400, message = "bad input parameter", response = ApiError.class),
        @ApiResponse(code = 404, message = "not found", response = ApiError.class),
        @ApiResponse(code = 500, message = "server error", response = ApiError.class) })
    @PutMapping(value = "/recipes/{recipe-id}",
        produces = { "application/json" }, 
        consumes = { "application/json" })
    ResponseEntity<Void> updateRecipe(@ApiParam(value = "recipe id",required=true) @PathVariable("recipe-id") String recipeId,@ApiParam(value = "Recipe to update"  )  @Valid @RequestBody RecipeRequest body);


    @ApiOperation(value = "delete recipe", nickname = "deleteRecipe", notes = "Delete recipe ", tags={ "recipes", })
    @ApiResponses(value = { 
        @ApiResponse(code = 202, message = "recipe deleted"),
        @ApiResponse(code = 400, message = "bad input parameter", response = ApiError.class),
        @ApiResponse(code = 404, message = "not found", response = ApiError.class),
        @ApiResponse(code = 500, message = "server", response = ApiError.class) })
    @DeleteMapping(value = "/recipes/{recipe-id}",
        produces = { "application/json" })
    ResponseEntity<Void> deleteRecipe(@ApiParam(value = "recipe id",required=true) @PathVariable("recipe-id") String recipeId) throws ApiException;
    
}

