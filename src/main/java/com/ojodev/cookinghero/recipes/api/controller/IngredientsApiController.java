package com.ojodev.cookinghero.recipes.api.controller;

import com.ojodev.cookinghero.recipes.api.model.Ingredient;
import com.ojodev.cookinghero.recipes.api.model.IngredientNew;
import com.ojodev.cookinghero.recipes.api.model.IngredientUpdate;
import com.ojodev.cookinghero.recipes.business.IngredientsBusiness;
import com.ojodev.cookinghero.recipes.business.RecipesBusiness_old;
import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.domain.exception.ApiException;
import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;
import com.ojodev.cookinghero.recipes.domain.model.IngredientBO;
import com.ojodev.cookinghero.recipes.domain.model.IngredientNewBO;
import com.ojodev.cookinghero.recipes.mapper.IngredientsMapper;
import com.ojodev.cookinghero.recipes.mapper.IngredientsNewMapper;
import com.ojodev.cookinghero.recipes.mapper.IngredientsPatchMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Controller
@Api(tags = "recipes", description = "Hero recipes")
public class IngredientsApiController implements IngredientsApi {


    @Autowired
    private IngredientsMapper ingredientsMapper;

    @Autowired
    private IngredientsNewMapper ingredientsNewMapper;

    @Autowired
    private IngredientsPatchMapper ingredientsPatchMapper;

    @Autowired
    private IngredientsBusiness ingredientsBusiness;

    @Autowired
    private RecipesBusiness_old recipesBusiness;

    @Autowired
    private Messages messages;


    @Override
    public ResponseEntity<List<Ingredient>> getIngredients(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId) throws NotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ingredientsMapper.toIngredientList(ingredientsBusiness.getIngredients(recipeId)));
    }


    @Override
    public ResponseEntity<Void> addIngredient(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId,
                                              @ApiParam(value = "Ingredient to add.") @Valid @RequestBody IngredientNew body) throws ApiException {
        String id = saveIngredient(body, recipeId);
        return ResponseEntity.created(generateLocationHeader(id)).build();
    }

    @Override
    public ResponseEntity<Ingredient> getIngredient(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId,
                                                    @ApiParam(value = "Ingredient id.", required = true) @PathVariable("ingredient-id") String ingredientId) throws NotFoundException {
        throwErrorIfRecipeNotFound(recipeId);

        IngredientBO ingredientBO = ingredientsBusiness.getIngredient(recipeId, ingredientId).orElseThrow((() -> new NotFoundException(messages.get("error.notfound.ingredient.code"), messages.get("error.notfound.ingredient.desc"))));

        return ResponseEntity.status(HttpStatus.OK)
                .body(ingredientsMapper.toIngredient(ingredientBO));
    }


    @Override
    public ResponseEntity<Void> updateIngredient(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId,
                                                 @ApiParam(value = "Ingredient id.", required = true) @PathVariable("ingredient-id") String ingredientId,
                                                 @ApiParam(value = "Ingredient to update.") @Valid @RequestBody IngredientUpdate body) throws ApiException {
        throwErrorIfRecipeNotFound(recipeId);

        IngredientBO ingredientBO = ingredientsBusiness.getIngredient(recipeId, ingredientId).orElseThrow((() -> new NotFoundException(messages.get("error.notfound.ingredient.code"), messages.get("error.notfound.ingredient.desc"))));
        IngredientNewBO ingredientPatched = ingredientsPatchMapper.patch(ingredientBO, body);
        ingredientPatched.setRecipeId(recipeId);
        ingredientsBusiness.addOrReplaceIngredient(ingredientPatched);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @Override
    public ResponseEntity<Void> deleteIngredient(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId,
                                                 @ApiParam(value = "Ingredient id.", required = true) @PathVariable("ingredient-id") String ingredientId) throws NotFoundException {
        ingredientsBusiness.deleteIngredient(recipeId, ingredientId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private String saveIngredient(IngredientNew ingredientNew, String recipeId) throws ApiException {
        IngredientNewBO ingredientNewBO = ingredientsNewMapper.toIngredientNewBO(ingredientNew, recipeId);
        ingredientsBusiness.addIngredient(ingredientNewBO);
        return ingredientNewBO.getId();
    }

    private void throwErrorIfRecipeNotFound(String recipeId) throws NotFoundException {
        if (!recipesBusiness.existsRecipe(recipeId)) {
            throw new NotFoundException(messages.get("error.notfound.recipe.code"), messages.get("error.notfound.recipe.desc"));
        }
    }

    private URI generateLocationHeader(String id) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }

}
