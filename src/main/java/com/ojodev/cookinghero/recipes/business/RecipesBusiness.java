package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.domain.model.RecipeNewBO;

public interface RecipesBusiness {

    /**
     * Recipe
     *
     * @param recipe recipe to add.
     * @return String id recipe generated.
     */
    String addRecipe(RecipeNewBO recipe);

}
