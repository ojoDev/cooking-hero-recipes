package com.ojodev.cookinghero.recipes.api.model;

import com.ojodev.cookinghero.recipes.data.CuisineTypesExamples;
import com.ojodev.cookinghero.recipes.utils.AbstractJavaBeanTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CuisineTypeNewNameTest extends AbstractJavaBeanTest<CuisineTypeNewName> {

    @Override
    protected CuisineTypeNewName getBeanInstance() {
        return new CuisineTypeNewName();
    }

    @Test
    public void constructorAllFields() {
        CuisineTypeNewName cuisineTypeNewName = new CuisineTypeNewName(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, LanguageEnum.EN);

        assertNotNull(cuisineTypeNewName);
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, cuisineTypeNewName.getName());
        assertEquals(LanguageEnum.EN, cuisineTypeNewName.getLanguage());
    }
}
