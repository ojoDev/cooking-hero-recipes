package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;
import com.ojodev.cookinghero.recipes.domain.model.IngredientBO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface IngredientsBusiness {

    Optional<IngredientBO> getIngredient(String recipeId, String ingredientId) throws NotFoundException;

    List<IngredientBO> getIngredients(String recipeId) throws NotFoundException;



}
