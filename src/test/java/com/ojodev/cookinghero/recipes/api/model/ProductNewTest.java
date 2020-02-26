package com.ojodev.cookinghero.recipes.api.model;

import com.ojodev.cookinghero.recipes.data.DescriptiveNamesExamples;
import com.ojodev.cookinghero.recipes.utils.AbstractJavaBeanTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductNewTest extends AbstractJavaBeanTest<ProductNew> {

    @Override
    protected ProductNew getBeanInstance() {
        return new ProductNew();
    }

    @Test
    public void constructorAllFields() {

        DescriptiveName descriptiveName = new DescriptiveName(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_SINGULAR, DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_PLURAL);
        ProductNewName productNewName01 = new ProductNewName(descriptiveName, LanguageEnum.EN);
        DescriptiveName descriptiveName02 = new DescriptiveName(DescriptiveNamesExamples.DESCRIPTIVE_NAME_02_SINGULAR, DescriptiveNamesExamples.DESCRIPTIVE_NAME_02_PLURAL);
        ProductNewName productNewName02 = new ProductNewName(descriptiveName02, LanguageEnum.ES);

        ProductNew productNew = new ProductNew(Arrays.asList(productNewName01, productNewName02), ProductStatusEnum.APPROVED_BY_ADMIN);

        assertNotNull(productNew);
        assertNotNull(productNew.getNames());
        assertEquals(2, productNew.getNames().size());
        assertEquals(LanguageEnum.EN, productNew.getNames().get(0).getLanguage());
        assertNotNull(productNew.getNames().get(0).getName());
        assertEquals(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_SINGULAR, productNew.getNames().get(0).getName().getSingular());
        assertEquals(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_PLURAL, productNew.getNames().get(0).getName().getPlural());
        assertEquals(LanguageEnum.ES, productNew.getNames().get(1).getLanguage());
        assertNotNull(productNew.getNames().get(1).getName());
        assertEquals(DescriptiveNamesExamples.DESCRIPTIVE_NAME_02_SINGULAR, productNew.getNames().get(1).getName().getSingular());
        assertEquals(DescriptiveNamesExamples.DESCRIPTIVE_NAME_02_PLURAL, productNew.getNames().get(1).getName().getPlural());
        assertEquals(ProductStatusEnum.APPROVED_BY_ADMIN, productNew.getStatus());
    }
}
