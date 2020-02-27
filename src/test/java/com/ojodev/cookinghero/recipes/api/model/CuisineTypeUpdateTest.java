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
public class CuisineTypeUpdateTest extends AbstractJavaBeanTest<CuisineTypeUpdate> {

    @Override
    protected CuisineTypeUpdate getBeanInstance() {
        return new CuisineTypeUpdate();
    }

    @Test
    public void constructorAllFields() {
        CuisineTypeUpdate cuisineTypeUpdate = new CuisineTypeUpdate(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH);

        assertNotNull(cuisineTypeUpdate);
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, cuisineTypeUpdate.getName());
    }
}
