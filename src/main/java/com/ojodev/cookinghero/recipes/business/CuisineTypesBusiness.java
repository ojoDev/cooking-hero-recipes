package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.domain.exception.ApiException;
import com.ojodev.cookinghero.recipes.domain.exception.ApiFieldsException;
import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;
import com.ojodev.cookinghero.recipes.domain.model.CuisineTypeBO;
import com.ojodev.cookinghero.recipes.domain.model.CuisineTypeMultiLanguageBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface CuisineTypesBusiness {

    List<CuisineTypeBO> getCuisineTypes(LanguageEnumBO language);

    List<CuisineTypeBO> getCuisineTypes(String name, LanguageEnumBO language);

    Optional<CuisineTypeBO> getCuisineType(String id, LanguageEnumBO language);

    void addCuisineType(CuisineTypeMultiLanguageBO newCuisineType) throws ApiException;

    void addOrReplaceCuisineType(CuisineTypeBO cuisineType) throws ApiException;

    void deleteCuisineType(String cuisineTypeId) throws NotFoundException;
}
