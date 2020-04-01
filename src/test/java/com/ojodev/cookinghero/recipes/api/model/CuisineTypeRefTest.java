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
public class CuisineTypeRefTest extends AbstractJavaBeanTest<CuisineTypeRef> {

    @Override
    protected CuisineTypeRef getBeanInstance() {
        return new CuisineTypeRef();
    }

    @Test
    public void constructorAllFields() {
        CuisineTypeRef cuisineTypeRef = new CuisineTypeRef(CuisineTypesExamples.CUISINE_TYPE_01_ID);

        assertNotNull(cuisineTypeRef);
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_ID, cuisineTypeRef.getId());

    }
}
