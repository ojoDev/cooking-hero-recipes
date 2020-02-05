package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.domain.constants.RecipeConstants;
import com.ojodev.cookinghero.recipes.domain.model.CuisineTypeBO;
import com.ojodev.cookinghero.recipes.domain.model.CuisineTypeMultiLanguageBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageNameBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.CuisineTypePO;
import com.ojodev.cookinghero.recipes.infrastructure.repository.CuisineTypesRepository;
import com.ojodev.cookinghero.recipes.mapper.CuisineTypesMapper;
import com.ojodev.cookinghero.recipes.mapper.CuisineTypesMultipleLanguageMapper;
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

    @Autowired
    private CuisineTypesMultipleLanguageMapper cuisineTypesMultipleLanguageMapper;

    public List<CuisineTypeBO> getCuisineTypes(LanguageEnumBO language) {
        List<CuisineTypePO> cuisineTypePOList = cuisineTypesRepository.findAll();
        return cuisineTypePOList.stream().map(cuisineType -> cuisineTypesMapper.toCuisineTypeBO(cuisineType, setDefaultLanguageIfNull(language))).collect(Collectors.toCollection(ArrayList::new));
    }

    public List<CuisineTypeBO> getCuisineTypes(String name, LanguageEnumBO language) {
        List<CuisineTypePO> cuisineTypesPO;
        if (StringUtils.isEmpty(name)) {
            return getCuisineTypes(language);
        } else {
            cuisineTypesPO = cuisineTypesRepository.findByName(name, language.toString());
        }
        return cuisineTypesPO.stream().map(cuisineType -> cuisineTypesMapper.toCuisineTypeBO(cuisineType, language)).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Optional<CuisineTypeBO> getCuisineType(String id, LanguageEnumBO language) {
        CuisineTypePO cuisineTypePO = cuisineTypesRepository.findById(id);
        return Optional.ofNullable(cuisineTypesMapper.toCuisineTypeBO(cuisineTypePO, language));
    }

    @Override
    public void addCuisineType(CuisineTypeMultiLanguageBO newCuisineType) {
        cuisineTypesRepository.save(cuisineTypesMultipleLanguageMapper.toCuisineTypePO(newCuisineType));
    }

    @Override
    public void addOrReplaceCuisineType(CuisineTypeBO cuisineType, LanguageEnumBO language) {
        //TODO DMS Not implemented
    }

    private LanguageEnumBO setDefaultLanguageIfNull(LanguageEnumBO language) {
        return language == null ? RecipeConstants.DEFAULT_LANGUAGE : language;
    }

}
