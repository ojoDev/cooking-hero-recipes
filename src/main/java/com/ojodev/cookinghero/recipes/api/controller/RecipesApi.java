package com.ojodev.cookinghero.recipes.api.controller;


import com.ojodev.cookinghero.recipes.api.model.Tag;
import com.ojodev.cookinghero.recipes.api.model.*;
import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Api(value = "recipes")
public interface RecipesApi {




    /**
     * @ApiOperation(value = "searches recipes", nickname = "getRecipes", notes = "Search for recipe list ", response = Recipe.class, responseContainer = "array", tags={ Tag.RECIPES, })
     * @ApiResponses(value = {
     * @ApiResponse(code = 200, message = "search results matching criteria", response = Recipe.class, responseContainer = "List"),
     * @ApiResponse(code = 204, message = "no results"),
     * @ApiResponse(code = 400, message = "bad input parameter", response = ApiError.class),
     * @ApiResponse(code = 500, message = "internal server error", response = ApiError.class) })
     * @GetMapping(value = "/recipes",
     * produces = { MediaType.APPLICATION_JSON_VALUE })
     * @CrossOrigin(origins = "*")
     * ResponseEntity<List<Recipe>> getRecipes(@ApiParam(value = "initial date of recipe creation, useful for multiple page lists searches") @Valid @RequestParam(value = "createdFrom", required = false) DateTime createdFrom,@ApiParam(value = "recipe name, partial searches allowed") @Valid @RequestParam(value = "name", required = false) String name,@ApiParam(value = "user id that create the recipe") @Valid @RequestParam(value = "userId", required = false) String userId,@ApiParam(value = "recipe fields returned in response, separated by ','") @Valid @RequestParam(value = "fields", required = false) String fields,@Min(1)@ApiParam(value = "number of page for skip (pagination)") @Valid @RequestParam(value = "skip", required = false) Integer skip,@Min(1) @Max(50) @ApiParam(value = "maximum number of records returned, by default 20") @Valid @RequestParam(value = "limit", required = false) Integer limit);
     * @ApiOperation(value = "adds a recipe", nickname = "addRecipe", notes = "Adds an recipe to recipe book", tags={ Tag.RECIPES, })
     * @ApiResponses(value = {
     * @ApiResponse(code = 201, message = "recipe created"),
     * @ApiResponse(code = 400, message = "invalid input, object invalid", response = ApiError.class),
     * @ApiResponse(code = 500, message = "server error", response = ApiError.class) })
     * @PostMapping(value = "/recipes",
     * produces = { MediaType.APPLICATION_JSON_VALUE },
     * consumes = { MediaType.APPLICATION_JSON_VALUE })
     * @CrossOrigin(origins = "*")
     * ResponseEntity<Void> addRecipe(@ApiParam(value = "Recipe to add"  )  @Valid @RequestBody RecipeRequest body);
     * @ApiOperation(value = "get recipe by id", nickname = "getRecipe", notes = "Search recipe by id ", response = ApiError.class, tags={ Tag.RECIPES, })
     * @ApiResponses(value = {
     * @ApiResponse(code = 200, message = "search results matching criteria", response = ApiError.class),
     * @ApiResponse(code = 400, message = "bad input parameter", response = ApiError.class),
     * @ApiResponse(code = 404, message = "not found", response = ApiError.class),
     * @ApiResponse(code = 500, message = "server error", response = ApiError.class) })
     * @GetMapping(value = "/recipes/{recipe-id}",
     * produces = { MediaType.APPLICATION_JSON_VALUE })
     * @CrossOrigin(origins = "*")
     * ResponseEntity<Recipe> getRecipe(@ApiParam(value = "recipe id",required=true) @PathVariable("recipe-id") String recipeId) throws NotFoundException;
     * @ApiOperation(value = "update recipe", nickname = "updateRecipe", notes = "Update recipe info ", tags={ Tag.RECIPES, })
     * @ApiResponses(value = {
     * @ApiResponse(code = 204, message = "recipe updated"),
     * @ApiResponse(code = 400, message = "bad input parameter", response = ApiError.class),
     * @ApiResponse(code = 404, message = "not found", response = ApiError.class),
     * @ApiResponse(code = 500, message = "server error", response = ApiError.class) })
     * @PutMapping(value = "/recipes/{recipe-id}",
     * produces = { MediaType.APPLICATION_JSON_VALUE },
     * consumes = { MediaType.APPLICATION_JSON_VALUE })
     * @CrossOrigin(origins = "*")
     * ResponseEntity<Void> updateRecipe(@ApiParam(value = "recipe id",required=true) @PathVariable("recipe-id") String recipeId,@ApiParam(value = "Recipe to update"  )  @Valid @RequestBody Recipe recipe);
     * @ApiOperation(value = "delete recipe", nickname = "deleteRecipe", notes = "Delete recipe ", tags={ Tag.RECIPES, })
     * @ApiResponses(value = {
     * @ApiResponse(code = 202, message = "recipe deleted"),
     * @ApiResponse(code = 400, message = "bad input parameter", response = ApiError.class),
     * @ApiResponse(code = 404, message = "not found", response = ApiError.class),
     * @ApiResponse(code = 500, message = "server", response = ApiError.class) })
     * @DeleteMapping(value = "/recipes/{recipe-id}",
     * produces = { MediaType.APPLICATION_JSON_VALUE })
     * @CrossOrigin(origins = "*")
     * ResponseEntity<Void> deleteRecipe(@ApiParam(value = "recipe id",required=true) @PathVariable("recipe-id") String recipeId) throws ApiException;
     */


    @ApiOperation(value = "Searches recipes", nickname = "getRecipes", notes = "Search recipes owned by **Heroes**. ", response = Recipe.class, responseContainer = "List", tags = {Tag.RECIPES})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Search recipes matching criteria.", response = Recipe.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad input parameter.", response = ApiFieldsError.class),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource.", response = ApiError.class),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it.", response = ApiError.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ApiError.class)})
    @GetMapping(value = "/recipes",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<List<Recipe>> getRecipes(@ApiParam(value = "Recipe name, partial searches allowed.") @Valid @RequestParam(value = "name", required = false) String name,
                                            @ApiParam(value = "User id that create the recipe.") @Valid @RequestParam(value = "userId", required = false) String userId,
                                            @ApiParam(value = "Recipe status.") @Valid @RequestParam(value = "status", required = false) RecipeStatusEnum status,
                                            @ApiParam(value = "Recipe fields returned in response, separated by ','.") @Valid @RequestParam(value = "fields", required = false) String fields,
                                            @Min(1) @Max(100) @ApiParam(value = "Maximum number of records returned, by default 10.", defaultValue = "10") @Valid @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
                                            @ApiParam(value = "Cursor for pagination. Include in the API call to point to an specific recipe.") @Valid @RequestParam(value = "cursor", required = false) String cursor);


    @ApiOperation(value = "Adds a new recipe", nickname = "addRecipe", notes = "Adds an new recipe to recipe book.", tags = {Tag.RECIPES})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Resource created."),
            @ApiResponse(code = 400, message = "Bad input parameter.", response = ApiFieldsError.class),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource.", response = ApiError.class),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it.", response = ApiError.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ApiError.class)})
    @PostMapping(value = "/recipes",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Void> addRecipe(@ApiParam(value = "Recipe to add.") @Valid @RequestBody RecipeNew body);

    @ApiOperation(value = "Get recipe by id", nickname = "getRecipe", notes = "Obtain recipe details by recipe id. ", response = Recipe.class, tags = {Tag.RECIPES})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Recipe details.", response = Recipe.class),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource.", response = ApiError.class),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it.", response = ApiError.class),
            @ApiResponse(code = 404, message = "Not found.", response = ApiError.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ApiError.class)})
    @GetMapping(value = "/recipes/{recipe-id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Recipe> getRecipe(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId) throws NotFoundException;


    @ApiOperation(value = "Update recipe fields", nickname = "updateRecipeFields", notes = "Update partially recipe info. ", tags = {Tag.RECIPES})
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Recipe updated."),
            @ApiResponse(code = 400, message = "Bad input parameter.", response = ApiFieldsError.class),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource.", response = ApiError.class),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it.", response = ApiError.class),
            @ApiResponse(code = 404, message = "Not found.", response = ApiError.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ApiError.class)})
    @PatchMapping(value = "/recipes/{recipe-id}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Void> updateRecipe(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId,
                                      @ApiParam(value = "Recipe to update.") @Valid @RequestBody RecipeUpdate body);


    @ApiOperation(value = "Delete recipe", nickname = "deleteRecipe", notes = "Delete recipe. ", tags = {Tag.RECIPES})
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Recipe deleted."),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource.", response = ApiError.class),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it.", response = ApiError.class),
            @ApiResponse(code = 404, message = "Not found.", response = ApiError.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ApiError.class)})
    @DeleteMapping(value = "/recipes/{recipe-id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Void> deleteRecipe(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId);


}

