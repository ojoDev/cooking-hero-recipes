package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.domain.exception.ApiException;
import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;
import com.ojodev.cookinghero.recipes.domain.model.IngredientBO;
import com.ojodev.cookinghero.recipes.domain.model.IngredientNewBO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface IngredientsBusiness {

    Optional<IngredientBO> getIngredient(String recipeId, String ingredientId) throws NotFoundException;

    List<IngredientBO> getIngredients(String recipeId) throws NotFoundException;

    void deleteIngredient(String recipeId, String ingredientId) throws NotFoundException;

    void addIngredient(IngredientNewBO ingredient) throws ApiException;

    void addOrReplaceIngredient(IngredientNewBO ingredient) throws ApiException;

}
