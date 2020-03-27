package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.IngredientUpdate;
import com.ojodev.cookinghero.recipes.domain.model.IngredientBO;
import com.ojodev.cookinghero.recipes.domain.model.IngredientNewBO;

public interface IngredientsPatchMapper {

    IngredientNewBO patch(IngredientBO origin, IngredientUpdate patch);
}
