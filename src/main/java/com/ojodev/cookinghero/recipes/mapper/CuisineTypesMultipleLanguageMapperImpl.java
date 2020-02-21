package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.CuisineTypeNew;
import com.ojodev.cookinghero.recipes.api.model.CuisineTypeNewName;
import com.ojodev.cookinghero.recipes.domain.constants.RecipesConstants;
import com.ojodev.cookinghero.recipes.domain.model.CuisineTypeMultiLanguageBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageNameBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.CuisineTypePO;
import com.ojodev.cookinghero.recipes.infrastructure.po.LanguageNamePO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CuisineTypesMultipleLanguageMapperImpl implements CuisineTypesMultipleLanguageMapper {

    @Override
    public CuisineTypeMultiLanguageBO toCuisineTypeMultiLanguageBO(CuisineTypeNew cuisineTypeNew, LanguageEnumBO defaultLanguage){
        List<LanguageNameBO> languageNames = cuisineTypeNew.getNames().stream().map(n -> convertToLanguageNameBO(n)).collect(Collectors.toList());
        return new CuisineTypeMultiLanguageBO.Builder(languageNames, defaultLanguage).build();
    }


    private LanguageNameBO convertToLanguageNameBO(CuisineTypeNewName cuisineTypeNewName) {
        return new LanguageNameBO(cuisineTypeNewName.getName(), setDefaultLanguageIfNull(LanguageEnumBO.fromValue(cuisineTypeNewName.getLanguage().toString())));
    }

    private LanguageEnumBO setDefaultLanguageIfNull(LanguageEnumBO language) {
        return language == null ? RecipesConstants.DEFAULT_LANGUAGE : language;
    }

    @Override
    public CuisineTypePO toCuisineTypePO(CuisineTypeMultiLanguageBO cuisineTypeNew) {
        List<LanguageNamePO> languageNames = cuisineTypeNew.getLanguageNames().stream().map(n -> convertToLanguageNamePO(n)).collect(Collectors.toList());
       return new CuisineTypePO(cuisineTypeNew.getId(), languageNames);
    }

    private LanguageNamePO convertToLanguageNamePO(LanguageNameBO languageNameBO) {
        return new LanguageNamePO(languageNameBO.getLanguage().toString(),languageNameBO.getName());
    }

}
