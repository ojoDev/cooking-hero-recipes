package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.api.model.LanguageEnum;
import com.ojodev.cookinghero.recipes.domain.model.CuisineTypeBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageNameBO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public interface CuisineTypesBusiness {

    List<CuisineTypeBO> getCuisineTypes(LanguageEnum language);

    List<CuisineTypeBO> getCuisineTypes(String name, LanguageEnum language);

    Optional<CuisineTypeBO> getCuisineType(String id, LanguageEnum language);

    void addCuisineType(List<LanguageNameBO> contentTypeNames);

    void addOrReplaceCuisineType(CuisineTypeBO cuisineType, LanguageEnum language);
}
