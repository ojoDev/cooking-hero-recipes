package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;
import com.ojodev.cookinghero.recipes.domain.model.IngredientBO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IngredientsBusinessImpl implements IngredientsBusiness {

    @Override
    public List<IngredientBO> getIngredients(String recipeId) throws NotFoundException {
       //TODO DMS Not implemented
        return null;
    }
}
