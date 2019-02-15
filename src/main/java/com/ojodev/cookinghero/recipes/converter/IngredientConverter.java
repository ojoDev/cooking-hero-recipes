package com.ojodev.cookinghero.recipes.converter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ojodev.cookinghero.recipes.bean.Ingredient;

import com.ojodev.cookinghero.recipes.po.IngredientPO;

@Component
public class IngredientConverter {

	public Ingredient toIngredient(IngredientPO ingredientPO) {
		Ingredient ingredient = null;
		if (ingredientPO != null) {
			ingredient = new Ingredient();
			ingredient.setProduct(ingredientPO.getProduct());
			ingredient.setQuantity(
					ingredientPO.getQuantity() == null ? null : new BigDecimal(ingredientPO.getQuantity()));
			ingredient.setMeasure(ingredient.getMeasure());
		}
		return ingredient;
	}

	public List<Ingredient> toIngredients(List<IngredientPO> ingredientsPO) {
		return ingredientsPO == null ? null
				: ingredientsPO.stream().map(e -> toIngredient(e)).collect(Collectors.toList());
	}

	public IngredientPO toIngredientPO(Ingredient ingredient) {
		IngredientPO ingredientPO = null;
		if (ingredient != null) {
			ingredientPO = new IngredientPO();
			ingredientPO.setProduct(ingredient.getProduct());
			ingredientPO.setQuantity(ingredient.getQuantity() == null ? null : ingredient.getQuantity().intValue());
			ingredientPO.setMeasure(ingredient.getMeasure());
		}
		return ingredientPO;
	}

	public List<IngredientPO> toIngredientsPO(List<Ingredient> ingredients) {
		return ingredients == null ? null
				: ingredients.stream().map(e -> toIngredientPO(e)).collect(Collectors.toList());
	}
	
	
}
