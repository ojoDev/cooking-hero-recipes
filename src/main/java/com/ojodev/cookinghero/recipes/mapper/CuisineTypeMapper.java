package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.CuisineType;
import com.ojodev.cookinghero.recipes.api.model.Ingredient;
import com.ojodev.cookinghero.recipes.domain.model.CuisineTypeBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.IngredientPO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CuisineTypeMapper {

	CuisineType toCuisineType(CuisineTypeBO cuisineTypeBO);

	List<CuisineType> toCuisineType(List<CuisineTypeBO> cuisineTypeBO);

	CuisineTypeBO toCuisineType(CuisineType cuisineType);

}
