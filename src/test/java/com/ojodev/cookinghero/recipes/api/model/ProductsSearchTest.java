package com.ojodev.cookinghero.recipes.api.model;

import com.ojodev.cookinghero.recipes.data.DescriptiveNamesExamples;
import com.ojodev.cookinghero.recipes.data.ProductsExamples;
import com.ojodev.cookinghero.recipes.utils.AbstractJavaBeanTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductsSearchTest extends AbstractJavaBeanTest<ProductsSearch> {

    @Override
    protected ProductsSearch getBeanInstance() {
        return new ProductsSearch();
    }

    @Test
    public void constructorAllFields() {
        Integer offset = 20;
        Integer limit = 10;
        Integer totalElements = 77;
        String sUrl = "http://www.mycompany.com/api/v1/recipes/products";
        URL url = null;
        try {
            url = new URL(sUrl);
        } catch (MalformedURLException e) {
            fail("Error in test url");
        }
        PaginationInfo paginationInfo = new PaginationInfo(offset, limit, totalElements, url);

        DescriptiveName descriptiveName = new DescriptiveName(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_SINGULAR, DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_PLURAL);
        Product product = new Product(ProductsExamples.PRODUCT_01_ID, descriptiveName, ProductStatusEnum.APPROVED_BY_ADMIN);

        ProductsSearch productsSearch = new ProductsSearch(Arrays.asList(product), paginationInfo);

        assertNotNull(productsSearch);
        assertNotNull(productsSearch.getContent());
        assertEquals(1, productsSearch.getContent().size());
        assertNotNull(productsSearch.getContent().get(0).getName());
        assertEquals(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_SINGULAR, productsSearch.getContent().get(0).getName().getSingular());
        assertEquals(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_PLURAL, productsSearch.getContent().get(0).getName().getPlural());
        assertEquals(ProductStatusEnum.APPROVED_BY_ADMIN, productsSearch.getContent().get(0).getStatus());
        assertNotNull(productsSearch.getPagination());
        assertEquals(offset, productsSearch.getPagination().getOffset());
        assertEquals(limit, productsSearch.getPagination().getLimit());
        assertEquals(Integer.valueOf(3), productsSearch.getPagination().getPage());
        assertEquals(Integer.valueOf(8), productsSearch.getPagination().getTotalPages());
        assertEquals(totalElements, productsSearch.getPagination().getTotalElements());
        assertNotNull(productsSearch.getPagination().getLinks());
    }
}
