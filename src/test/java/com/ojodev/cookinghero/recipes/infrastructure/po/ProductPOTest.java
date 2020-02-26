package com.ojodev.cookinghero.recipes.infrastructure.po;

import com.ojodev.cookinghero.recipes.api.model.Product;
import com.ojodev.cookinghero.recipes.data.DescriptiveNamesExamples;
import com.ojodev.cookinghero.recipes.data.ProductsExamples;
import com.ojodev.cookinghero.recipes.domain.model.ProductStatusEnumBO;
import com.ojodev.cookinghero.recipes.utils.AbstractJavaBeanTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.meanbean.test.BeanTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductPOTest extends AbstractJavaBeanTest<Product> {

    @Override
    protected Product getBeanInstance() {
        return new Product();
    }

    @Test
    public void constructorAllFields() {

        DescriptiveNamePO descriptiveName = new DescriptiveNamePO(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_SINGULAR, DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_PLURAL, DescriptiveNamesExamples.LANGUAGE_ENGLISH);
        ProductPO product = new ProductPO(ProductsExamples.PRODUCT_01_ID, Arrays.asList(descriptiveName), ProductStatusEnumBO.APPROVED_BY_ADMIN.toString());

        assertNotNull(product);
        assertEquals(ProductsExamples.PRODUCT_01_ID, product.getObjectId());
        assertNotNull(product.getNames());
        assertEquals(1, product.getNames().size());
        assertEquals(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_SINGULAR, product.getNames().get(0).getSingular());
        assertEquals(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_PLURAL, product.getNames().get(0).getPlural());
        assertEquals(ProductStatusEnumBO.APPROVED_BY_ADMIN.toString(), product.getStatus());
    }

    @Test
    public void getterAndSetterCorrectness() {
        final BeanTester beanTester = new BeanTester();
        beanTester.testBean(ProductPO.class);
    }
}
