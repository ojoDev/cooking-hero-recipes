package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.CuisineTypeNew;
import com.ojodev.cookinghero.recipes.domain.model.CuisineTypeMultiLanguageBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.CuisineTypePO;

public interface CuisineTypesMultipleLanguageMapper {

    CuisineTypeMultiLanguageBO toCuisineTypeMultiLanguageBO(CuisineTypeNew cuisineTypeNew, LanguageEnumBO defaultLanguage);

    CuisineTypePO toCuisineTypePO(CuisineTypeMultiLanguageBO newCuisineType);
}
