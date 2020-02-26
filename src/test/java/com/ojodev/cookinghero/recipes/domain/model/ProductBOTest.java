package com.ojodev.cookinghero.recipes.domain.model;

import com.ojodev.cookinghero.recipes.api.model.Product;
import com.ojodev.cookinghero.recipes.data.DescriptiveNamesExamples;
import com.ojodev.cookinghero.recipes.data.ProductsExamples;
import com.ojodev.cookinghero.recipes.utils.AbstractJavaBeanTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductBOTest extends AbstractJavaBeanTest<Product> {

    @Override
    protected Product getBeanInstance() {
        return new Product();
    }

    @Test
    public void constructorAllFields() {

        DescriptiveNameBO descriptiveName = new DescriptiveNameBO(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_SINGULAR, DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_PLURAL, LanguageEnumBO.EN);
        ProductBO product = new ProductBO(ProductsExamples.PRODUCT_01_ID, descriptiveName, ProductStatusEnumBO.APPROVED_BY_ADMIN);

        assertNotNull(product);
        assertEquals(ProductsExamples.PRODUCT_01_ID, product.getId());
        assertNotNull(product.getName());
        assertEquals(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_SINGULAR, product.getName().getSingular());
        assertEquals(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_PLURAL, product.getName().getPlural());
        assertEquals(ProductStatusEnumBO.APPROVED_BY_ADMIN, product.getStatus());
    }
}
