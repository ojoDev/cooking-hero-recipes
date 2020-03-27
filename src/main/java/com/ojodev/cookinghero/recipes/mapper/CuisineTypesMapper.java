package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.CuisineType;
import com.ojodev.cookinghero.recipes.api.model.CuisineTypeNewName;
import com.ojodev.cookinghero.recipes.domain.model.CuisineTypeBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.CuisineTypePO;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
@DecoratedWith(CuisineTypesMapperDecorator.class)
public interface CuisineTypesMapper {

	CuisineTypesMapper INSTANCE = Mappers.getMapper(CuisineTypesMapper.class);

	CuisineType toCuisineType(CuisineTypeBO cuisineTypeBO);

	List<CuisineType> toCuisineTypeList(List<CuisineTypeBO> cuisineTypeBOList);

	CuisineTypeBO toCuisineTypeBO(CuisineTypePO cuisineTypePO, LanguageEnumBO language);



}
