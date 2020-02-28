package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.Ingredient;
import com.ojodev.cookinghero.recipes.domain.model.IngredientBO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IngredientsMapper {

    Ingredient toIngredient(IngredientBO ingredientBO);

    List<Ingredient> toIngredientList(List<IngredientBO> ingredientBO);
}
