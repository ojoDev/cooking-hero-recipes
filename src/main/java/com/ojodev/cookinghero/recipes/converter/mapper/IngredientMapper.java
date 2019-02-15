package com.ojodev.cookinghero.recipes.converter.mapper;

import org.mapstruct.Mapper;

import com.ojodev.cookinghero.recipes.bean.Ingredient;
import com.ojodev.cookinghero.recipes.po.IngredientPO;

@Mapper(componentModel = "spring")
public interface IngredientMapper {
	
	Ingredient toIngredient(IngredientPO ingredientPO);

	IngredientPO toIngredientPO(Ingredient ingredient);
}
