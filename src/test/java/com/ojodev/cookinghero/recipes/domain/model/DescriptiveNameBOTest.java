package com.ojodev.cookinghero.recipes.domain.model;

import com.ojodev.cookinghero.recipes.data.DescriptiveNamesExamples;
import com.ojodev.cookinghero.recipes.utils.AbstractJavaBeanTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DescriptiveNameBOTest extends AbstractJavaBeanTest<DescriptiveNameBO> {

    @Override
    protected DescriptiveNameBO getBeanInstance() {
        return new DescriptiveNameBO();
    }

    @Test
    public void constructorAllFields() {
        DescriptiveNameBO descriptiveName = new DescriptiveNameBO(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_SINGULAR, DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_PLURAL, LanguageEnumBO.EN);

        assertNotNull(descriptiveName);
        assertEquals(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_SINGULAR, descriptiveName.getSingular());
        assertEquals(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_PLURAL, descriptiveName.getPlural());
        assertEquals(LanguageEnumBO.EN, descriptiveName.getLanguage());
    }
}