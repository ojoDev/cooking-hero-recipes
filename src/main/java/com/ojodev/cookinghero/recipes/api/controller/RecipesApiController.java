package com.ojodev.cookinghero.recipes.api.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import io.swagger.annotations.Api;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ojodev.cookinghero.recipes.domain.exception.ApiException;
import com.ojodev.cookinghero.recipes.api.model.Recipe;
import com.ojodev.cookinghero.recipes.api.model.RecipeRequest;
import com.ojodev.cookinghero.recipes.business.RecipesBusiness;
import com.ojodev.cookinghero.recipes.domain.enume.UpsertResultEnum;
import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;

import io.swagger.annotations.ApiParam;

@Controller
@Api(tags = "recipes", description = "Hero recipes")
public class RecipesApiController implements RecipesApi {

    @Autowired
    private RecipesBusiness recipeBusiness;


    public ResponseEntity<List<Recipe>> getRecipes(
            @ApiParam(value = "initial date of recipe creation, useful for multiple page lists searches") @Valid @RequestParam(value = "createdFrom", required = false) DateTime createdFrom,
            @ApiParam(value = "recipe name, partial searches allowed") @Valid @RequestParam(value = "name", required = false) String name,
            @ApiParam(value = "user id that create the recipe") @Valid @RequestParam(value = "userId", required = false) String userId,
            @ApiParam(value = "recipe fields returned in response, separated by ','") @Valid @RequestParam(value = "fields", required = false) String fields,
            @Min(1) @ApiParam(value = "number of page for skip (pagination)", allowableValues = "") @Valid @RequestParam(value = "skip", required = false) Integer skip,
            @Min(1) @Max(50) @ApiParam(value = "maximum number of records returned, by default 20", allowableValues = "") @Valid @RequestParam(value = "limit", required = false) Integer limit) {
        List<Recipe> recipeList = recipeBusiness.getRecipes(name);
        return recipeList.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(recipeList, HttpStatus.OK);
    }

    public ResponseEntity<Void> addRecipe(@ApiParam(value = "Recipe to add") @Valid @RequestBody RecipeRequest body) {
        recipeBusiness.addRecipe(body);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<Recipe> getRecipe(String recipeId) throws NotFoundException {
        Recipe recipe = recipeBusiness.getRecipe(recipeId);
        return new ResponseEntity<>(recipe, HttpStatus.OK);
    }

    public ResponseEntity<Void> updateRecipe(
            @ApiParam(value = "recipe id", required = true) @PathVariable("recipe-id") String recipeId,
            @ApiParam(value = "recipe to update") @Valid @RequestBody Recipe recipe) {
        UpsertResultEnum result = recipeBusiness.updateRecipe(recipe);
        return new ResponseEntity<>(UpsertResultEnum.CREATED.equals(result) ? HttpStatus.CREATED : HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Void> deleteRecipe(
            @ApiParam(value = "recipe id", required = true) @PathVariable("recipe-id") String recipeId)
            throws ApiException {
        recipeBusiness.deleteRecipe(recipeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
