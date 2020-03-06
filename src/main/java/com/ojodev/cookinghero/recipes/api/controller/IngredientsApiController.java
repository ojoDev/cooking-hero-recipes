package com.ojodev.cookinghero.recipes.api.controller;

import com.ojodev.cookinghero.recipes.api.model.*;
import com.ojodev.cookinghero.recipes.business.IngredientsBusiness;
import com.ojodev.cookinghero.recipes.business.RecipesBusiness;
import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;
import com.ojodev.cookinghero.recipes.domain.model.IngredientBO;
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
public class IngredientsApiController implements IngredientsApi {


    @Autowired
    private IngredientsMapper ingredientsMapper;

    @Autowired
    private IngredientsBusiness ingredientsBusiness;

    @Autowired
    private RecipesBusiness recipesBusiness;

    @Autowired
    private Messages messages;


    @Override
    public ResponseEntity<List<Ingredient>> getIngredients(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId) throws NotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ingredientsMapper.toIngredientList(ingredientsBusiness.getIngredients(recipeId)));
    }

    @Override
    public ResponseEntity<Ingredient> getIngredient(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId,
                                                    @ApiParam(value = "Ingredient id.", required = true) @PathVariable("ingredient-id") String ingredientId) throws NotFoundException {
        throwErrorIfRecipeNotFound(recipeId);

        IngredientBO ingredientBO = ingredientsBusiness.getIngredient(recipeId, ingredientId).orElseThrow((() -> new NotFoundException(messages.get("error.notfound.ingredient.code"), messages.get("error.notfound.ingredient.desc"))));

        return ResponseEntity.status(HttpStatus.OK)
                .body(ingredientsMapper.toIngredient(ingredientBO));
    }

    private void throwErrorIfRecipeNotFound(String recipeId) throws NotFoundException {
        if (!recipesBusiness.existsRecipe(recipeId)) {
            throw new NotFoundException(messages.get("error.notfound.recipe.code"), messages.get("error.notfound.recipe.desc"));
        }
    }

    @Override
    public ResponseEntity<Void> addIngredient(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId,
                                              @ApiParam(value = "Ingredient to add.") @Valid @RequestBody Ingredient body) {
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
    public ResponseEntity<Void> deleteIngredient(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId,
                                                 @ApiParam(value = "Ingredient id.", required = true) @PathVariable("ingredient-id") String ingredientId) throws NotFoundException {
        ingredientsBusiness.deleteIngredient(recipeId, ingredientId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
