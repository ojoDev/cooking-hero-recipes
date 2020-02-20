package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.DescriptiveName;
import com.ojodev.cookinghero.recipes.api.model.DescriptiveNameUpdate;
import com.ojodev.cookinghero.recipes.api.model.LanguageEnum;
import com.ojodev.cookinghero.recipes.domain.model.DescriptiveNameBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.DescriptiveNamePO;
import org.springframework.stereotype.Component;

@Component
public class DescriptiveNameMapperImpl implements DescriptiveNameMapper {

    @Override
    public DescriptiveNameBO toDescriptiveNameBO(DescriptiveNamePO descriptiveNamePO) {
        if (descriptiveNamePO == null) {
            return null;
        }
        DescriptiveNameBO descriptiveNameBO = new DescriptiveNameBO();
        descriptiveNameBO.setSingular(descriptiveNamePO.getSingular());
        descriptiveNameBO.setPlural(descriptiveNamePO.getPlural());
        if (descriptiveNamePO.getLanguage() != null) {
            descriptiveNameBO.setLanguage(LanguageEnumBO.fromValue(descriptiveNamePO.getLanguage()));
        }
        return descriptiveNameBO;
    }

    @Override
    public DescriptiveNameBO toDescriptiveNameBO(DescriptiveName descriptiveName, LanguageEnum language) {
        if (descriptiveName == null) {
            return null;
        }
        DescriptiveNameBO descriptiveNameBO = new DescriptiveNameBO();
        descriptiveNameBO.setSingular(descriptiveName.getSingular());
        descriptiveNameBO.setPlural(descriptiveName.getPlural());
        if (language != null) {
            descriptiveNameBO.setLanguage(LanguageEnumBO.fromValue(language.toString()));
        }
        return descriptiveNameBO;
    }

    //TODO DMS: Se podría hacer con anotaciones mapstructs
    @Override
    public DescriptiveNameBO toDescriptiveNameBO(DescriptiveNameUpdate descriptiveNameUpdate, LanguageEnumBO languageEnum) {
        return new DescriptiveNameBO(descriptiveNameUpdate.getSingular(), descriptiveNameUpdate.getPlural(), languageEnum);
    }

    //TODO DMS: Se podría hacer con anotaciones mapstructs
    @Override
    public DescriptiveNamePO toDescriptiveNamePO(DescriptiveNameBO descriptiveNameBO) {
        return new DescriptiveNamePO(descriptiveNameBO.getSingular(), descriptiveNameBO.getPlural(), descriptiveNameBO.getLanguage().toString());
    }


}