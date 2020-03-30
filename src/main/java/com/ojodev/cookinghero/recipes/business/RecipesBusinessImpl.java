package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.domain.model.RecipeNewBO;
import com.ojodev.cookinghero.recipes.infrastructure.repository.RecipesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecipesBusinessImpl implements RecipesBusiness {

    @Autowired
    private RecipesRepository recipesRepository;


    @Override
    public String addRecipe(RecipeNewBO recipe) {
        throwsExceptionIfUserNotExists(recipe.getUserId());
        return null;
    }

    private void throwsExceptionIfUserNotExists(String userId) {
        // TODO DMS: Falta llamar a servicio externo
    }
}
