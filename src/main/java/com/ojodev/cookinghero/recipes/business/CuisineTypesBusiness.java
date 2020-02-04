package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.domain.model.CuisineTypeBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageNameBO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface CuisineTypesBusiness {

    List<CuisineTypeBO> getCuisineTypes(LanguageEnumBO language);

    List<CuisineTypeBO> getCuisineTypes(String name, LanguageEnumBO language);

    Optional<CuisineTypeBO> getCuisineType(String id, LanguageEnumBO language);

    void addCuisineType(List<LanguageNameBO> contentTypeNames);

    void addOrReplaceCuisineType(CuisineTypeBO cuisineType, LanguageEnumBO language);
}
