package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.IngredientNew;
import com.ojodev.cookinghero.recipes.domain.model.IngredientNewBO;
import org.springframework.stereotype.Component;

@Component
public class IngredientsNewMapperImpl implements IngredientsNewMapper {

    @Override
    public IngredientNewBO toIngredientNewBO(IngredientNew ingredientNew, String recipeId) {
        return new IngredientNewBO(recipeId, ingredientNew.getProductName(), ingredientNew.getQuantity(), ingredientNew.getMeasureId());
    }
}
