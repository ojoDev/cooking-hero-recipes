package com.ojodev.cookinghero.recipes.domain.model;

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
public class CuisineTypeBOTest extends AbstractJavaBeanTest<CuisineTypeBO> {

    @Override
    protected CuisineTypeBO getBeanInstance() {
        return new CuisineTypeBO();
    }

    @Test
    public void constructorAllFields() {
        CuisineTypeBO cuisineType = new CuisineTypeBO(CuisineTypesExamples.CUISINE_TYPE_01_ID, CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, LanguageEnumBO.EN);

        assertNotNull(cuisineType);
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_ID, cuisineType.getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, cuisineType.getName());
        assertEquals(LanguageEnumBO.EN, cuisineType.getLanguage());
    }
}
