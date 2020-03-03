package com.ojodev.cookinghero.recipes.api.controller;


import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.ojodev.cookinghero.recipes.api.model.*;
import org.joda.time.DateTime;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ojodev.cookinghero.recipes.domain.exception.ApiException;
import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "recipes")
public interface RecipesApi {

    public static final String TAG_RECIPES = "recipes";

    /**
     * @ApiOperation(value = "searches recipes", nickname = "getRecipes", notes = "Search for recipe list ", response = Recipe.class, responseContainer = "array", tags={ TAG_RECIPES, })
     * @ApiResponses(value = {
     * @ApiResponse(code = 200, message = "search results matching criteria", response = Recipe.class, responseContainer = "List"),
     * @ApiResponse(code = 204, message = "no results"),
     * @ApiResponse(code = 400, message = "bad input parameter", response = ApiError.class),
     * @ApiResponse(code = 500, message = "internal server error", response = ApiError.class) })
     * @GetMapping(value = "/recipes",
     * produces = { MediaType.APPLICATION_JSON_VALUE })
     * @CrossOrigin(origins = "*")
     * ResponseEntity<List<Recipe>> getRecipes(@ApiParam(value = "initial date of recipe creation, useful for multiple page lists searches") @Valid @RequestParam(value = "createdFrom", required = false) DateTime createdFrom,@ApiParam(value = "recipe name, partial searches allowed") @Valid @RequestParam(value = "name", required = false) String name,@ApiParam(value = "user id that create the recipe") @Valid @RequestParam(value = "userId", required = false) String userId,@ApiParam(value = "recipe fields returned in response, separated by ','") @Valid @RequestParam(value = "fields", required = false) String fields,@Min(1)@ApiParam(value = "number of page for skip (pagination)") @Valid @RequestParam(value = "skip", required = false) Integer skip,@Min(1) @Max(50) @ApiParam(value = "maximum number of records returned, by default 20") @Valid @RequestParam(value = "limit", required = false) Integer limit);
     * @ApiOperation(value = "adds a recipe", nickname = "addRecipe", notes = "Adds an recipe to recipe book", tags={ TAG_RECIPES, })
     * @ApiResponses(value = {
     * @ApiResponse(code = 201, message = "recipe created"),
     * @ApiResponse(code = 400, message = "invalid input, object invalid", response = ApiError.class),
     * @ApiResponse(code = 500, message = "server error", response = ApiError.class) })
     * @PostMapping(value = "/recipes",
     * produces = { MediaType.APPLICATION_JSON_VALUE },
     * consumes = { MediaType.APPLICATION_JSON_VALUE })
     * @CrossOrigin(origins = "*")
     * ResponseEntity<Void> addRecipe(@ApiParam(value = "Recipe to add"  )  @Valid @RequestBody RecipeRequest body);
     * @ApiOperation(value = "get recipe by id", nickname = "getRecipe", notes = "Search recipe by id ", response = ApiError.class, tags={ TAG_RECIPES, })
     * @ApiResponses(value = {
     * @ApiResponse(code = 200, message = "search results matching criteria", response = ApiError.class),
     * @ApiResponse(code = 400, message = "bad input parameter", response = ApiError.class),
     * @ApiResponse(code = 404, message = "not found", response = ApiError.class),
     * @ApiResponse(code = 500, message = "server error", response = ApiError.class) })
     * @GetMapping(value = "/recipes/{recipe-id}",
     * produces = { MediaType.APPLICATION_JSON_VALUE })
     * @CrossOrigin(origins = "*")
     * ResponseEntity<Recipe> getRecipe(@ApiParam(value = "recipe id",required=true) @PathVariable("recipe-id") String recipeId) throws NotFoundException;
     * @ApiOperation(value = "update recipe", nickname = "updateRecipe", notes = "Update recipe info ", tags={ TAG_RECIPES, })
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
     * @ApiOperation(value = "delete recipe", nickname = "deleteRecipe", notes = "Delete recipe ", tags={ TAG_RECIPES, })
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


    @ApiOperation(value = "Searches recipes", nickname = "getRecipes", notes = "Search recipes owned by **Heroes**. ", response = Recipe.class, responseContainer = "List", tags = {TAG_RECIPES})
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


    @ApiOperation(value = "Adds a new recipe", nickname = "addRecipe", notes = "Adds an new recipe to recipe book.", tags = {TAG_RECIPES})
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

    @ApiOperation(value = "Get recipe by id", nickname = "getRecipe", notes = "Obtain recipe details by recipe id. ", response = Recipe.class, tags = {TAG_RECIPES})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Recipe details.", response = Recipe.class),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource.", response = ApiError.class),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it.", response = ApiError.class),
            @ApiResponse(code = 404, message = "Not found.", response = ApiError.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ApiError.class)})
    @GetMapping(value = "/recipes/{recipe-id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Recipe> getRecipe(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId) throws NotFoundException;


    @ApiOperation(value = "Update recipe fields", nickname = "updateRecipeFields", notes = "Update partially recipe info. ", tags = {TAG_RECIPES})
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


    @ApiOperation(value = "Delete recipe", nickname = "deleteRecipe", notes = "Delete recipe. ", tags = {TAG_RECIPES})
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Recipe deleted."),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource.", response = ApiError.class),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it.", response = ApiError.class),
            @ApiResponse(code = 404, message = "Not found.", response = ApiError.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ApiError.class)})
    @DeleteMapping(value = "/recipes/{recipe-id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Void> deleteRecipe(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId);


    @ApiOperation(value = "Get recipe ingredient list", nickname = "getIngredients", notes = "Search ingredients used to cook a recipe, with products and his measures. ", response = Ingredient.class, responseContainer = "List", tags = {TAG_RECIPES})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ingredients included in a recipe.", response = Ingredient.class, responseContainer = "List"),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource.", response = ApiError.class),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it.", response = ApiError.class),
            @ApiResponse(code = 404, message = "Not found.", response = ApiError.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ApiError.class)})
    @GetMapping(value = "/recipes/{recipe-id}/ingredients",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<List<Ingredient>> getIngredients(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId) throws NotFoundException;


    @ApiOperation(value = "Obtain ingredient details", nickname = "getIngredient", notes = "Obtain ingredient details. ", response = Ingredient.class, tags = {TAG_RECIPES})
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


    @ApiOperation(value = "Adds a new ingredient in a recipe or change a existing ingredient", nickname = "addIngredient", notes = "Adds a new ingredient in a recipe if ingredient name not exists.   If ingredient name exists, update quantity and/or measure. ", tags = {TAG_RECIPES})
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
                                       @ApiParam(value = "Ingredient to add.") @Valid @RequestBody Ingredient body);


    @ApiOperation(value = "Delete ingredient of a recipe", nickname = "deleteIngredient", notes = "Delete ingredient of a recipe. ", tags = {TAG_RECIPES})
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Ingredient deleted."),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource.", response = ApiError.class),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it.", response = ApiError.class),
            @ApiResponse(code = 404, message = "Not found.", response = ApiError.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ApiError.class)})
    @DeleteMapping(value = "/recipes/{recipe-id}/ingredients/{ingredient-id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Void> deleteIngredient(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId,
                                          @ApiParam(value = "Ingredient id.", required = true) @PathVariable("ingredient-id") String ingredientId);


    @ApiOperation(value = "Update ingredient in a recipe.", nickname = "updateIngredient", notes = "Update ingredient info. ", tags = {TAG_RECIPES})
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Ingredient updated."),
            @ApiResponse(code = 400, message = "Bad input parameter.", response = ApiFieldsError.class),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource.", response = ApiError.class),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it.", response = ApiError.class),
            @ApiResponse(code = 404, message = "Not found.", response = ApiError.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ApiError.class)})
    @PutMapping(value = "/recipes/{recipe-id}/ingredients/{ingredient-id}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Void> updateIngredient(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId,
                                          @ApiParam(value = "Ingredient id.", required = true) @PathVariable("ingredient-id") String ingredientId,
                                          @ApiParam(value = "Ingredient to update.") @Valid @RequestBody Ingredient body);


    @ApiOperation(value = "Get steps to cook the recipe", nickname = "getSteps", notes = "Search steps for a recipe. ", response = Step.class, responseContainer = "List", tags = {TAG_RECIPES})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Steps for cook a recipe.", response = Step.class, responseContainer = "List"),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource.", response = ApiError.class),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it.", response = ApiError.class),
            @ApiResponse(code = 404, message = "Not found.", response = ApiError.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ApiError.class)})
    @GetMapping(value = "/recipes/{recipe-id}/steps",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<List<Step>> getSteps(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId);


    @ApiOperation(value = "Adds a step to a recipe step list", nickname = "addStep", notes = "Adds a new step (in order) to the list of steps in a recipe.", tags = {TAG_RECIPES})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Resource created."),
            @ApiResponse(code = 400, message = "Bad input parameter.", response = ApiFieldsError.class),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource.", response = ApiError.class),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it.", response = ApiError.class),
            @ApiResponse(code = 404, message = "Not found.", response = ApiError.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ApiError.class)})
    @PostMapping(value = "/recipes/{recipe-id}/steps",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Void> addStep(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId,
                                 @ApiParam(value = "Step to add.") @Valid @RequestBody Step body);


    @ApiOperation(value = "Update step in a recipe.", nickname = "updateStep", notes = "Update step info. ", tags = {TAG_RECIPES})
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Step updated."),
            @ApiResponse(code = 400, message = "Bad input parameter.", response = ApiFieldsError.class),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource.", response = ApiError.class),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it.", response = ApiError.class),
            @ApiResponse(code = 404, message = "Not found.", response = ApiError.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ApiError.class)})
    @PutMapping(value = "/recipes/{recipe-id}/steps/{step-number}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Void> updateStep(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId,
                                    @Min(1) @ApiParam(value = "Step number in the step list of the recipe (first: 1).", required = true) @PathVariable("step-number") Integer stepNumber,
                                    @ApiParam(value = "Step description to update.") @Valid @RequestBody Step body);


    @ApiOperation(value = "Delete step of a recipe", nickname = "deleteStep", notes = "Delete step of a recipe and reorder other steps in the recipe. ", tags = {TAG_RECIPES})
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Step deleted and other steps ordered."),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource.", response = ApiError.class),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it.", response = ApiError.class),
            @ApiResponse(code = 404, message = "Not found.", response = ApiError.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ApiError.class)})
    @DeleteMapping(value = "/recipes/{recipe-id}/steps/{step-number}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Void> deleteStep(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId,
                                    @Min(1) @ApiParam(value = "Step number in the step list of the recipe (first: 1).", required = true) @PathVariable("step-number") Integer stepNumber);


}

