package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.CuisineType;
import com.ojodev.cookinghero.recipes.api.model.Ingredient;
import com.ojodev.cookinghero.recipes.api.model.LanguageEnum;
import com.ojodev.cookinghero.recipes.domain.model.CuisineTypeBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.CuisineTypePO;
import com.ojodev.cookinghero.recipes.infrastructure.po.IngredientPO;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
@DecoratedWith(CuisineTypesMapperDecorator.class)
public interface CuisineTypesMapper {

	CuisineTypesMapper INSTANCE = Mappers.getMapper(CuisineTypesMapper.class);

	CuisineType toCuisineType(CuisineTypeBO cuisineTypeBO);

	List<CuisineType> toCuisineTypeList(List<CuisineTypeBO> cuisineTypeBOList);

	CuisineTypeBO toCuisineTypeBO(CuisineTypePO cuisineTypePO, LanguageEnum language);

}
