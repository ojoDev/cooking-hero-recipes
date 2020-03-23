package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.IngredientNew;
import com.ojodev.cookinghero.recipes.api.model.MeasureRef;
import com.ojodev.cookinghero.recipes.domain.model.IngredientNewBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class IngredientsNewMapperImpl implements IngredientsNewMapper {

    @Override
    public IngredientNewBO toIngredientNewBO(IngredientNew ingredientNew, String recipeId) {
        String measureId = ingredientNew.getMeasure() == null || StringUtils.isEmpty(ingredientNew.getMeasure().getId()) ? null : ingredientNew.getMeasure().getId();
        return new IngredientNewBO(recipeId, ingredientNew.getProductName(), ingredientNew.getQuantity(), measureId);
    }
}
