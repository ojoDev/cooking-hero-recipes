package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.DescriptiveName;
import com.ojodev.cookinghero.recipes.api.model.LanguageEnum;
import com.ojodev.cookinghero.recipes.domain.model.DescriptiveNameBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.DescriptiveNamePO;

public interface DescriptiveNameMapper {

    DescriptiveNameBO toDescriptiveNameBO(DescriptiveNamePO descriptiveNamePO);

    DescriptiveNameBO toDescriptiveNameBO(DescriptiveName descriptiveName, LanguageEnum languageEnum);

    DescriptiveNamePO toDescriptiveNamePO(DescriptiveNameBO descriptiveNameBO);
}
