package com.ojodev.cookinghero.recipes.api;



import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ojodev.cookinghero.recipes.bean.ApiError;
import com.ojodev.cookinghero.recipes.bean.ApiException;
import com.ojodev.cookinghero.recipes.bean.Recipe;
import com.ojodev.cookinghero.recipes.bean.RecipeUpdate;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-12-06T11:16:24.847Z[GMT]")

@Api(value = "recipes", description = "the recipes API")
public interface RecipesApi {

    @ApiOperation(value = "adds a recipe", nickname = "addRecipe", notes = "Adds an recipe to recipe book", tags={ "recipes", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "recipe created"),
        @ApiResponse(code = 400, message = "invalid input, object invalid", response = ApiError.class),
        @ApiResponse(code = 500, message = "server error", response = ApiError.class) })
    @RequestMapping(value = "/recipes",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> addRecipe(@ApiParam(value = "Recipe to add"  )  @Valid @RequestBody Recipe body);


    @ApiOperation(value = "delete recipe", nickname = "deleteRecipe", notes = "Delete recipe ", tags={ "recipes", })
    @ApiResponses(value = { 
        @ApiResponse(code = 202, message = "recipe deleted"),
        @ApiResponse(code = 400, message = "bad input parameter", response = ApiError.class),
        @ApiResponse(code = 404, message = "not found", response = ApiError.class),
        @ApiResponse(code = 500, message = "server", response = ApiError.class) })
    @RequestMapping(value = "/recipes/{recipe-id}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteRecipe(@ApiParam(value = "recipe id",required=true) @PathVariable("recipe-id") String recipeId) throws ApiException;
    
    @ApiOperation(value = "get recipe by id", nickname = "getRecipe", notes = "Search recipe by id ", response = ApiError.class, tags={ "recipes", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "search results matching criteria", response = ApiError.class),
        @ApiResponse(code = 400, message = "bad input parameter", response = ApiError.class),
        @ApiResponse(code = 404, message = "not found", response = ApiError.class),
        @ApiResponse(code = 500, message = "server error", response = ApiError.class) })
    @RequestMapping(value = "/recipes/{recipe-id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Recipe> getRecipe(@ApiParam(value = "recipe id",required=true) @PathVariable("recipe-id") String recipeId) throws ApiException;;


    @ApiOperation(value = "searches recipes", nickname = "getRecipes", notes = "Search for recipe list ", response = Recipe.class, responseContainer = "List", tags={ "recipes", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "search results matching criteria", response = Recipe.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "bad input parameter"),
        @ApiResponse(code = 500, message = "server error") })
    @RequestMapping(value = "/recipes",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Recipe>> getRecipes(@ApiParam(value = "user that create the recipe") @Valid @RequestParam(value = "user", required = false) String user,@Min(0)@ApiParam(value = "number of records to skip for pagination", allowableValues = "") @Valid @RequestParam(value = "skip", required = false) Integer skip,@Min(0) @Max(50) @ApiParam(value = "maximum number of records to return", allowableValues = "") @Valid @RequestParam(value = "limit", required = false) Integer limit);


    @ApiOperation(value = "update recipe", nickname = "updateRecipe", notes = "Update recipe info ", tags={ "recipes", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "recipe updated"),
        @ApiResponse(code = 400, message = "bad input parameter", response = ApiError.class),
        @ApiResponse(code = 404, message = "not found", response = ApiError.class),
        @ApiResponse(code = 500, message = "server error", response = ApiError.class) })
    @RequestMapping(value = "/recipes/{recipe-id}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Void> updateRecipe(@ApiParam(value = "recipe id",required=true) @PathVariable("recipe-id") String recipeId,@ApiParam(value = "Recipe to update"  )  @Valid @RequestBody RecipeUpdate body);

}

