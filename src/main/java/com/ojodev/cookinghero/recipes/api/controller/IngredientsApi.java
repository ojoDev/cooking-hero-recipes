package com.ojodev.cookinghero.recipes.api.controller;


import com.ojodev.cookinghero.recipes.api.model.*;
import com.ojodev.cookinghero.recipes.api.model.Tag;
import com.ojodev.cookinghero.recipes.domain.exception.ApiException;
import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;
import io.micrometer.core.instrument.Tags;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Api(value = "recipes")
public interface IngredientsApi {

    @ApiOperation(value = "Get recipe ingredient list", nickname = "getIngredients", notes = "Search ingredients used to cook a recipe, with products and his measures. ", response = Ingredient.class, responseContainer = "List", tags = {Tag.RECIPES})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ingredients included in a recipe.", response = Ingredient.class, responseContainer = "List"),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource.", response = ApiError.class),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it.", response = ApiError.class),
            @ApiResponse(code = 404, message = "Not found.", response = ApiError.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ApiError.class)})
    @GetMapping(value = "/recipes/{recipe-id}/ingredients",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<List<Ingredient>> getIngredients(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId) throws NotFoundException;


    @ApiOperation(value = "Adds a new ingredient in a recipe or change a existing ingredient", nickname = "addIngredient", notes = "Adds a new ingredient in a recipe if ingredient name not exists.   If ingredient name exists, update quantity and/or measure. ", tags = {Tag.RECIPES})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Resource created."),
            @ApiResponse(code = 204, message = "Ingredient updated."),
            @ApiResponse(code = 400, message = "Bad input parameter.", response = ApiFieldsError.class),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource.", response = ApiError.class),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it.", response = ApiError.class),
            @ApiResponse(code = 404, message = "Not found.", response = ApiError.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ApiError.class)})
    @PostMapping(value = "/recipes/{recipe-id}/ingredients",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Void> addIngredient(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId,
                                       @ApiParam(value = "Ingredient to add.") @Valid @RequestBody IngredientNew body) throws ApiException;

    @ApiOperation(value = "Obtain ingredient details", nickname = "getIngredient", notes = "Obtain ingredient details. ", response = Ingredient.class, tags = {Tag.RECIPES})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ingredient.", response = Ingredient.class),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource.", response = ApiError.class),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it.", response = ApiError.class),
            @ApiResponse(code = 404, message = "Not found.", response = ApiError.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ApiError.class)})
    @GetMapping(value = "/recipes/{recipe-id}/ingredients/{ingredient-id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Ingredient> getIngredient(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId,
                                             @ApiParam(value = "Ingredient id.", required = true) @PathVariable("ingredient-id") String ingredientId) throws NotFoundException;



    @ApiOperation(value = "Update ingredient in a recipe.", nickname = "updateIngredient", notes = "Update ingredient info. ", tags = {Tag.RECIPES})
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Ingredient updated."),
            @ApiResponse(code = 400, message = "Bad input parameter.", response = ApiFieldsError.class),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource.", response = ApiError.class),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it.", response = ApiError.class),
            @ApiResponse(code = 404, message = "Not found.", response = ApiError.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ApiError.class)})
    @PatchMapping(value = "/recipes/{recipe-id}/ingredients/{ingredient-id}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Void> updateIngredient(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId,
                                          @ApiParam(value = "Ingredient id.", required = true) @PathVariable("ingredient-id") String ingredientId,
                                          @ApiParam(value = "Ingredient to update.") @Valid @RequestBody IngredientUpdate body);


    @ApiOperation(value = "Delete ingredient of a recipe", nickname = "deleteIngredient", notes = "Delete ingredient of a recipe. ", tags = {Tag.RECIPES})
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Ingredient deleted."),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource.", response = ApiError.class),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it.", response = ApiError.class),
            @ApiResponse(code = 404, message = "Not found.", response = ApiError.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ApiError.class)})
    @DeleteMapping(value = "/recipes/{recipe-id}/ingredients/{ingredient-id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Void> deleteIngredient(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId,
                                          @ApiParam(value = "Ingredient id.", required = true) @PathVariable("ingredient-id") String ingredientId) throws NotFoundException;


}

