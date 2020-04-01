package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.domain.model.RecipeBO;
import com.ojodev.cookinghero.recipes.domain.model.RecipeNewBO;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface RecipesBusiness {

    /**
     * Create a new recipe with basic attributes in DRAFT status.
     *
     * @param recipe recipe to add.
     * @return String id recipe generated.
     */
    void addRecipe(RecipeNewBO recipe);

    Optional<RecipeBO> getRecipe(@NotNull String recipeId);

    boolean existsRecipe(String recipeId);


}
