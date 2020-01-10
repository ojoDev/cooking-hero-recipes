package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.api.model.LanguageEnum;
import com.ojodev.cookinghero.recipes.domain.constants.RecipeConstants;
import com.ojodev.cookinghero.recipes.domain.model.CuisineTypeBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.CuisineTypePO;
import com.ojodev.cookinghero.recipes.infrastructure.repository.CuisineTypesRepository;
import com.ojodev.cookinghero.recipes.mapper.CuisineTypesMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CuisineTypesBusinessImpl implements CuisineTypesBusiness{

    @Autowired
    private CuisineTypesRepository cuisineTypesRepository;

    @Autowired
    private CuisineTypesMapper cuisineTypesMapper;

    public List<CuisineTypeBO> getCuisineTypes(LanguageEnum language) {
        List<CuisineTypePO> cuisineTypePOList = cuisineTypesRepository.findAll();
        return cuisineTypePOList.stream().map(cuisineType -> cuisineTypesMapper.toCuisineTypeBO(cuisineType, setDefaultLanguageIfNull(language))).collect(Collectors.toCollection(ArrayList::new));
    }

    public List<CuisineTypeBO> getCuisineTypes(String name, LanguageEnum language) {
        List<CuisineTypePO> cuisineTypesPO;
        if (StringUtils.isEmpty(name)) {
            return getCuisineTypes(language);
        } else {
            switch (setDefaultLanguageIfNull(language)) {
                case EN: cuisineTypesPO = cuisineTypesRepository.findByNameENContaining(name); break;
                case ES: cuisineTypesPO = cuisineTypesRepository.findByNameESContaining(name); break;
                default: cuisineTypesPO = cuisineTypesRepository.findByNameENContaining(name); break;
            }
        }
        return cuisineTypesPO.stream().map(cuisineType -> cuisineTypesMapper.toCuisineTypeBO(cuisineType, language)).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Optional<CuisineTypeBO> getCuisineType(String id, LanguageEnum language) {
        //TODO DMS Not implemented
        return null;
    }

    private LanguageEnum setDefaultLanguageIfNull(LanguageEnum language) {
        return language == null ? RecipeConstants.DEFAULT_LANGUAGE : language;
    }

}
