package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.LanguageEnum;
import com.ojodev.cookinghero.recipes.domain.constants.RecipeConstants;
import com.ojodev.cookinghero.recipes.domain.model.CuisineTypeBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.CuisineTypePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public abstract class CuisineTypesMapperDecorator implements CuisineTypesMapper {

    @Autowired
    @Qualifier("delegate")
    private CuisineTypesMapper delegate;

    @Override
    public CuisineTypeBO toCuisineTypeBO(CuisineTypePO cuisineTypePO, LanguageEnum language){
        CuisineTypeBO cuisineTypeBO = new CuisineTypeBO();
        cuisineTypeBO.setId(cuisineTypePO.getId());
        switch(setDefaultLanguageIfNull(language)) {
            case EN: cuisineTypeBO.setName(cuisineTypePO.getNameEN()); break;
            case ES: cuisineTypeBO.setName(cuisineTypePO.getNameES()); break;
            default: cuisineTypeBO.setName(cuisineTypePO.getNameEN()); break;
        }
        return cuisineTypeBO;
    }

    private LanguageEnum setDefaultLanguageIfNull(LanguageEnum language) {
        return language == null ? RecipeConstants.DEFAULT_LANGUAGE : language;
    }

}
