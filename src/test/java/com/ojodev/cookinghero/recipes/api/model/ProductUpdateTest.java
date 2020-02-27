package com.ojodev.cookinghero.recipes.api.model;

import com.ojodev.cookinghero.recipes.data.DescriptiveNamesExamples;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.meanbean.test.BeanTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductUpdateTest {

    @Test
    public void constructorAllFields() {

        DescriptiveNameUpdate descriptiveNameUpdate = new DescriptiveNameUpdate(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_SINGULAR, DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_PLURAL);
        ProductUpdate productUpdate = new ProductUpdate(descriptiveNameUpdate, ProductStatusEnum.APPROVED_BY_ADMIN);

        assertNotNull(productUpdate);
        assertNotNull(productUpdate.getName());
        assertEquals(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_SINGULAR, productUpdate.getName().getSingular());
        assertEquals(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_PLURAL, productUpdate.getName().getPlural());
        assertEquals(ProductStatusEnum.APPROVED_BY_ADMIN, productUpdate.getStatus());

    }

    @Test
    public void testNullOptionals() {
        ProductUpdate productUpdate = new ProductUpdate(null, null);

        assertNotNull(productUpdate);
        assertNull(productUpdate.getName());
        assertNull(productUpdate.getStatus());
        assertFalse(productUpdate.getNameOpt().isPresent());
        assertFalse(productUpdate.getStatusOpt().isPresent());
    }

    @Test
    public void getterAndSetterCorrectness() {
        final BeanTester beanTester = new BeanTester();
        beanTester.testBean(ProductUpdate.class);
    }
}
