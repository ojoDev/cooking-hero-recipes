package com.ojodev.cookinghero.recipes.api.model;

import com.ojodev.cookinghero.recipes.data.CuisineTypesExamples;
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
public class CuisineTypeNewTest extends AbstractJavaBeanTest<CuisineTypeNew> {

    @Override
    protected CuisineTypeNew getBeanInstance() {
        return new CuisineTypeNew();
    }

    @Test
    public void constructorAllFields() {
        CuisineTypeNew cuisineTypeNew = new CuisineTypeNew(Arrays.asList(
                new CuisineTypeNewName(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, LanguageEnum.EN),
                new CuisineTypeNewName(CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH, LanguageEnum.ES)));

        assertNotNull(cuisineTypeNew);
        assertNotNull(cuisineTypeNew.getNames());
        assertEquals(2, cuisineTypeNew.getNames().size());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, cuisineTypeNew.getNames().get(0).getName());
        assertEquals(LanguageEnum.EN, cuisineTypeNew.getNames().get(0).getLanguage());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH, cuisineTypeNew.getNames().get(1).getName());
        assertEquals(LanguageEnum.ES, cuisineTypeNew.getNames().get(1).getLanguage());
    }
}
