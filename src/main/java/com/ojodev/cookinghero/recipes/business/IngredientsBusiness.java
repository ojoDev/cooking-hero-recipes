package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;
import com.ojodev.cookinghero.recipes.domain.model.IngredientBO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IngredientsBusiness {

    /**
     * Return ingredients included in a recipe.
     * Names of measures and products are in recipe main language.
     *
     * @param recipeId Recipe Id
     * @return list of ingredients. Empty list if recipe exists but don't have ingredients yet.
     * @throws NotFoundException recipe not found.
     */
    List<IngredientBO> getIngredients(String recipeId) throws NotFoundException;

}
