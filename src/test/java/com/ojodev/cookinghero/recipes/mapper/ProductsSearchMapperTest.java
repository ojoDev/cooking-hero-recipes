package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.ProductStatusEnum;
import com.ojodev.cookinghero.recipes.api.model.ProductsSearch;
import com.ojodev.cookinghero.recipes.data.ProductsExamples;
import com.ojodev.cookinghero.recipes.domain.model.DescriptiveNameBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.ProductBO;
import com.ojodev.cookinghero.recipes.domain.model.ProductStatusEnumBO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductsSearchMapperTest {

    @Autowired
    private ProductsSearchMapper productsSearchMapper;


    @Test
    public void convertProductBOListToProductsSearch() {

        ProductBO originProduct01 = new ProductBO(ProductsExamples.PRODUCT_01_ID, new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN), ProductStatusEnumBO.APPROVED_BY_ADMIN);
        ProductBO originProduct02 = new ProductBO(ProductsExamples.PRODUCT_02_ID, new DescriptiveNameBO(ProductsExamples.PRODUCT_02_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_02_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN), ProductStatusEnumBO.CREATED_BY_USER);
        Integer offset = 20;
        Integer limit = 10;
        Integer totalProducts = 77;
        String sUrl = "http://www.mycompany.com/api/v1/recipes/products";
        URL url = null;
        try {
            url = new URL(sUrl);
        } catch (MalformedURLException e) {
            fail("Bad test definition: URL is not valid");
        }
        ProductsSearch productsSearch = productsSearchMapper.toProductsSearch(Arrays.asList(originProduct01,originProduct02), offset, limit, totalProducts, url);
        assertNotNull(productsSearch);
        assertNotNull(productsSearch.getContent());
        assertEquals(2, productsSearch.getContent().size());
        assertEquals(ProductsExamples.PRODUCT_01_ID, productsSearch.getContent().get(0).getId());
        assertNotNull(productsSearch.getContent().get(1).getName());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, productsSearch.getContent().get(0).getName().getSingular());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, productsSearch.getContent().get(0).getName().getPlural());
        assertEquals(ProductStatusEnum.APPROVED_BY_ADMIN, productsSearch.getContent().get(0).getStatus());
        assertEquals(ProductsExamples.PRODUCT_02_ID, productsSearch.getContent().get(1).getId());
        assertNotNull(productsSearch.getContent().get(1).getName());
        assertEquals(ProductsExamples.PRODUCT_02_NAME_ENGLISH_SINGULAR, productsSearch.getContent().get(1).getName().getSingular());
        assertEquals(ProductsExamples.PRODUCT_02_NAME_ENGLISH_PLURAL, productsSearch.getContent().get(1).getName().getPlural());
        assertEquals(ProductStatusEnum.CREATED_BY_USER, productsSearch.getContent().get(1).getStatus());
        assertNotNull(productsSearch.getPagination());
        assertEquals(offset, productsSearch.getPagination().getOffset());
        assertEquals(limit, productsSearch.getPagination().getLimit());
        assertEquals(Integer.valueOf(3), productsSearch.getPagination().getPage());
        assertEquals(Integer.valueOf(8), productsSearch.getPagination().getTotalPages());
        assertEquals(totalProducts, productsSearch.getPagination().getTotalElements());
        assertNotNull(productsSearch.getPagination().getLinks());
        assertEquals(sUrl + "?limit=10", productsSearch.getPagination().getLinks().getFirst());
        assertEquals(sUrl +"?offset=10&limit=10", productsSearch.getPagination().getLinks().getPrev());
        assertEquals(sUrl +"?offset=20&limit=10", productsSearch.getPagination().getLinks().getSelf());
        assertEquals(sUrl +"?offset=30&limit=10", productsSearch.getPagination().getLinks().getNext());
        assertEquals(sUrl +"?offset=70&limit=10", productsSearch.getPagination().getLinks().getLast());

    }
}
