package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.domain.constants.RecipeConstants;
import com.ojodev.cookinghero.recipes.domain.model.CuisineTypeBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.CuisineTypePO;
import com.ojodev.cookinghero.recipes.infrastructure.po.LanguageNamePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.stream.Collectors;

public abstract class CuisineTypesMapperDecorator implements CuisineTypesMapper {

    @Autowired
    @Qualifier("delegate")
    private CuisineTypesMapper delegate;

    @Override
    public CuisineTypeBO toCuisineTypeBO(CuisineTypePO cuisineTypePO, LanguageEnumBO language){
        if (cuisineTypePO == null) {
            return null;
        }
        CuisineTypeBO cuisineTypeBO = new CuisineTypeBO();
        cuisineTypeBO.setId(cuisineTypePO.getId());
        cuisineTypeBO.setName(selectNameByLanguage(cuisineTypePO.getNames(), setDefaultLanguageIfNull(language).toString()));
        cuisineTypeBO.setLanguage(language);

        return cuisineTypeBO;
    }

   private String selectNameByLanguage(List<LanguageNamePO> names, String language) {
       return names.stream().filter(n -> language.equals(n.getLanguage())).collect(Collectors.toList()).get(0).getName();
   }

    private LanguageEnumBO setDefaultLanguageIfNull(LanguageEnumBO language) {
        return language == null ? RecipeConstants.DEFAULT_LANGUAGE : language;
    }


}
