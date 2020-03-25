package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.IngredientNew;
import com.ojodev.cookinghero.recipes.api.model.IngredientUpdate;
import com.ojodev.cookinghero.recipes.domain.model.IngredientNewBO;


public interface IngredientsNewMapper {

    IngredientNewBO toIngredientNewBO(IngredientNew ingredient, String recipeId);

}
