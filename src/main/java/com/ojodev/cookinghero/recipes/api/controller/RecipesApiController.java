package com.ojodev.cookinghero.recipes.api.controller;

import com.ojodev.cookinghero.recipes.api.model.*;
import com.ojodev.cookinghero.recipes.business.IngredientsBusiness;
import com.ojodev.cookinghero.recipes.business.RecipesBusiness;
import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;
import com.ojodev.cookinghero.recipes.mapper.IngredientsMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Controller
@Api(tags = "recipes", description = "Hero recipes")
public class RecipesApiController implements RecipesApi {

    @Autowired
    private RecipesBusiness recipeBusiness;

    @Autowired
    private IngredientsMapper ingredientsMapper;

    @Autowired
    private IngredientsBusiness ingredientsBusiness;

    /**
     * public ResponseEntity<List<Recipe>> getRecipes(
     *
     * @ApiParam(value = "initial date of recipe creation, useful for multiple page lists searches") @Valid @RequestParam(value = "createdFrom", required = false) DateTime createdFrom,
     * @ApiParam(value = "recipe name, partial searches allowed") @Valid @RequestParam(value = "name", required = false) String name,
     * @ApiParam(value = "user id that create the recipe") @Valid @RequestParam(value = "userId", required = false) String userId,
     * @ApiParam(value = "recipe fields returned in response, separated by ','") @Valid @RequestParam(value = "fields", required = false) String fields,
     * @Min(1) @ApiParam(value = "number of page for skip (pagination)", allowableValues = "") @Valid @RequestParam(value = "skip", required = false) Integer skip,
     * @Min(1) @Max(50) @ApiParam(value = "maximum number of records returned, by default 20", allowableValues = "") @Valid @RequestParam(value = "limit", required = false) Integer limit) {
     * List<Recipe> recipeList = recipeBusiness.getRecipes(name);
     * return recipeList.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(recipeList, HttpStatus.OK);
     * }
     * <p>
     * public ResponseEntity<Recipe> getRecipe(String recipeId) throws NotFoundException {
     * Recipe recipe = recipeBusiness.getRecipe(recipeId);
     * return new ResponseEntity<>(recipe, HttpStatus.OK);
     * }
     * <p>
     * public ResponseEntity<Void> addRecipe(@ApiParam(value = "Recipe to add") @Valid @RequestBody RecipeRequest body) {
     * recipeBusiness.addRecipe(body);
     * return new ResponseEntity<>(HttpStatus.CREATED);
     * <p>
     * public ResponseEntity<Void> updateRecipe(
     * @ApiParam(value = "recipe id", required = true) @PathVariable("recipe-id") String recipeId,
     * @ApiParam(value = "recipe to update") @Valid @RequestBody Recipe recipe) {
     * UpsertResultEnum result = recipeBusiness.updateRecipe(recipe);
     * return new ResponseEntity<>(UpsertResultEnum.CREATED.equals(result) ? HttpStatus.CREATED : HttpStatus.NO_CONTENT);
     * }
     * <p>
     * public ResponseEntity<Void> deleteRecipe(
     * @ApiParam(value = "recipe id", required = true) @PathVariable("recipe-id") String recipeId)
     * throws ApiException {
     * recipeBusiness.deleteRecipe(recipeId);
     * return new ResponseEntity<>(HttpStatus.NO_CONTENT);
     * }
     * }
     */

    @Override
    public ResponseEntity<List<Recipe>> getRecipes(@ApiParam(value = "Recipe name, partial searches allowed.") @Valid @RequestParam(value = "name", required = false) String name,
                                                   @ApiParam(value = "User id that create the recipe.") @Valid @RequestParam(value = "userId", required = false) String userId,
                                                   @ApiParam(value = "Recipe status.") @Valid @RequestParam(value = "status", required = false) RecipeStatusEnum status,
                                                   @ApiParam(value = "Recipe fields returned in response, separated by ','.") @Valid @RequestParam(value = "fields", required = false) String fields,
                                                   @Min(1) @Max(100) @ApiParam(value = "Maximum number of records returned, by default 10.", defaultValue = "10") @Valid @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
                                                   @ApiParam(value = "Cursor for pagination. Include in the API call to point to an specific recipe.") @Valid @RequestParam(value = "cursor", required = false) String cursor) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> addRecipe(@ApiParam(value = "Recipe to add.") @Valid @RequestBody RecipeNew body) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Recipe> getRecipe(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId) throws NotFoundException {
        //TODO Hacer
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> updateRecipe(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId,
                                             @ApiParam(value = "Recipe to update.") @Valid @RequestBody RecipeUpdate body) {
        //TODO Hacer
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> deleteRecipe(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId) {
        //TODO Hacer
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @Override
    public ResponseEntity<List<Ingredient>> getIngredients(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId) throws NotFoundException {
        //TODO Hacer
        return ResponseEntity.status(HttpStatus.OK)
                .body(ingredientsMapper.toIngredientList(ingredientsBusiness.getIngredients(recipeId)));
    }

    @Override
    public ResponseEntity<Ingredient> getIngredient(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId,
                                                    @ApiParam(value = "Ingredient id.", required = true) @PathVariable("ingredient-id") String ingredientId) {
        //TODO Hacer
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> addIngredient(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId,
                                              @ApiParam(value = "Ingredient to add.") @Valid @RequestBody Ingredient body) {
        //TODO Hacer
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> deleteIngredient(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId,
                                                 @ApiParam(value = "Ingredient id.", required = true) @PathVariable("ingredient-id") String ingredientId) {
        //TODO Hacer
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> updateIngredient(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId,
                                                 @ApiParam(value = "Ingredient id.", required = true) @PathVariable("ingredient-id") String ingredientId,
                                                 @ApiParam(value = "Ingredient to update.") @Valid @RequestBody Ingredient body) {
        //TODO Hacer
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<List<Step>> getSteps(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId) {
        //TODO Hacer
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> addStep(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId,
                                        @ApiParam(value = "Step to add.") @Valid @RequestBody Step body) {
        //TODO Hacer
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> updateStep(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId,
                                           @Min(1) @ApiParam(value = "Step number in the step list of the recipe (first: 1).", required = true) @PathVariable("step-number") Integer stepNumber,
                                           @ApiParam(value = "Step description to update.") @Valid @RequestBody Step body) {
        //TODO Hacer
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> deleteStep(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId,
                                           @Min(1) @ApiParam(value = "Step number in the step list of the recipe (first: 1).", required = true) @PathVariable("step-number") Integer stepNumber) {
        //TODO Hacer
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
