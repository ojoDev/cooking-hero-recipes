package com.ojodev.cookinghero.recipes.data;

import com.ojodev.cookinghero.recipes.api.model.LanguageEnum;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.DescriptiveNamePO;

public class DescriptiveNamesExamples {

    public static final String DESCRIPTIVE_NAME_01_SINGULAR= "tablespoon";
    public static final String DESCRIPTIVE_NAME_01_PLURAL = "tablespoons";
    public static final String LANGUAGE_ENGLISH = "en";
    public static final DescriptiveNamePO DESCRIPTIVE_NAME_PO = new DescriptiveNamePO(DESCRIPTIVE_NAME_01_SINGULAR, DESCRIPTIVE_NAME_01_PLURAL, LANGUAGE_ENGLISH);

    public static final LanguageEnumBO LANGUAGE_ENGLISH_ENUM = LanguageEnumBO.EN;
}
