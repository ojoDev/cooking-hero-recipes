package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.LanguageEnum;
import com.ojodev.cookinghero.recipes.data.DescriptiveNamesExamples;
import com.ojodev.cookinghero.recipes.domain.model.DescriptiveNameBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.DescriptiveNamePO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DescriptiveNameMapperTest {

    @Autowired
    private DescriptiveNameMapper descriptiveNameMapper;

    @Test
    public void fromDescriptiveNamePOToDescriptiveNameBO() {
        DescriptiveNameBO descriptiveNameBO = descriptiveNameMapper.toDescriptiveNameBO(DescriptiveNamesExamples.DESCRIPTIVE_NAME_PO);

        assertNotNull(descriptiveNameBO);
        assertEquals(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_SINGULAR, descriptiveNameBO.getSingular());
        assertEquals(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_PLURAL, descriptiveNameBO.getPlural());
        assertEquals(DescriptiveNamesExamples.LANGUAGE_ENGLISH_ENUM, descriptiveNameBO.getLanguage());
    }

    @Test
    public void fromDescriptiveNameToDescriptiveNameBO() {
        DescriptiveNameBO descriptiveNameBO = descriptiveNameMapper.toDescriptiveNameBO(DescriptiveNamesExamples.DESCRIPTIVE_NAME, LanguageEnum.EN);

        assertNotNull(descriptiveNameBO);
        assertEquals(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_SINGULAR, descriptiveNameBO.getSingular());
        assertEquals(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_PLURAL, descriptiveNameBO.getPlural());
        assertEquals(DescriptiveNamesExamples.LANGUAGE_ENGLISH_ENUM, descriptiveNameBO.getLanguage());
    }

    @Test
    public void fromDescriptiveNameBOToDescriptiveNamePO() {
        DescriptiveNamePO descriptiveNamePO = descriptiveNameMapper.toDescriptiveNamePO(DescriptiveNamesExamples.DESCRIPTIVE_NAME_BO);

        assertNotNull(descriptiveNamePO);
        assertEquals(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_SINGULAR, descriptiveNamePO.getSingular());
        assertEquals(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_PLURAL, descriptiveNamePO.getPlural());
        assertEquals(DescriptiveNamesExamples.LANGUAGE_ENGLISH, descriptiveNamePO.getLanguage());
    }

    @Test
    public void fromDescriptiveNameUpdateToDescriptiveNameBO() {
        DescriptiveNameBO descriptiveNameBO = descriptiveNameMapper.toDescriptiveNameBO(DescriptiveNamesExamples.DESCRIPTIVE_NAME_UPDATE, DescriptiveNamesExamples.LANGUAGE_ENGLISH_ENUM);

        assertNotNull(descriptiveNameBO);
        assertEquals(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_SINGULAR, descriptiveNameBO.getSingular());
        assertEquals(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_PLURAL, descriptiveNameBO.getPlural());
        assertEquals(DescriptiveNamesExamples.LANGUAGE_ENGLISH_ENUM, descriptiveNameBO.getLanguage());
    }


}
