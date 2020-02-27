package com.ojodev.cookinghero.recipes.mapper;

import org.mapstruct.Mapper;

import com.ojodev.cookinghero.recipes.api.model.Ingredient;
import com.ojodev.cookinghero.recipes.infrastructure.po.IngredientPO;

@Mapper(componentModel = "spring")
public interface IngredientMapper {
	
	//Ingredient toIngredient(IngredientPO ingredientPO);

	//IngredientPO toIngredientPO(Ingredient ingredient);
}
