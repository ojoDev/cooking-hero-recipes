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
public class CuisineTypeTest extends AbstractJavaBeanTest<CuisineType> {

    @Override
    protected CuisineType getBeanInstance() {
        return new CuisineType();
    }

    @Test
    public void constructorAllFields() {
        CuisineType cuisineType = new CuisineType(CuisineTypesExamples.CUISINE_TYPE_01_ID, CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH);

        assertNotNull(cuisineType);
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_ID, cuisineType.getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, cuisineType.getName());
    }
}
