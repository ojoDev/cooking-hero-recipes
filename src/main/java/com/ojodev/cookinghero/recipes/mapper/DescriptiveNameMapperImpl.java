package com.ojodev.cookinghero.recipes.mapper;

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
}