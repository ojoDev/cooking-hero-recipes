package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.MeasureUpdate;
import com.ojodev.cookinghero.recipes.domain.constants.RecipesConstants;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.MeasureBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.DescriptiveNamePO;
import com.ojodev.cookinghero.recipes.infrastructure.po.MeasurePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.stream.Collectors;

public abstract class MeasuresMapperDecorator implements MeasuresMapper {

    @Autowired
    @Qualifier("delegate")
    private MeasuresMapper delegate;

    @Autowired
    private DescriptiveNameMapper descriptiveNameMapper;

    @Override
    public MeasureBO toMeasureBO(MeasurePO measurePO, LanguageEnumBO language){
        if (measurePO == null) {
            return null;
        }
        MeasureBO measureBO = new MeasureBO();
        measureBO.setId(measurePO.getObjectId());
        DescriptiveNamePO descriptiveNamePO = selectNameByLanguage(measurePO.getNames(), setDefaultLanguageIfNull(language).toString());
        measureBO.setName(descriptiveNameMapper.toDescriptiveNameBO(descriptiveNamePO));
        return measureBO;
    }



    private DescriptiveNamePO selectNameByLanguage(List<DescriptiveNamePO> names, String language) {
        List<DescriptiveNamePO> filteredNames = names.stream().filter(n -> language.equals(n.getLanguage())).collect(Collectors.toList());
        if (filteredNames.isEmpty()) {
           return selectNameByLanguage(names, RecipesConstants.DEFAULT_LANGUAGE.toString());
       } else {
            return filteredNames.get(0);
        }
   }

    private LanguageEnumBO setDefaultLanguageIfNull(LanguageEnumBO language) {
        return language == null ? RecipesConstants.DEFAULT_LANGUAGE : language;
    }

    public MeasureBO toMeasureBO(MeasureUpdate measureUpdate, String id, LanguageEnumBO language) {
        if (measureUpdate == null) {
            return null;
        }
        MeasureBO measureBO = new MeasureBO();
        measureBO.setName(descriptiveNameMapper.toDescriptiveNameBO(measureUpdate.getName(), language));
        measureBO.setId(id);
        return measureBO;
    }


}
