package com.ojodev.cookinghero.recipes.infrastructure.repository;

import com.ojodev.cookinghero.recipes.RecipesApplication;
import com.ojodev.cookinghero.recipes.domain.model.ProductStatusEnumBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.ProductPO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class })
@SpringBootTest(classes = RecipesApplication.class)
@Slf4j
public class ProductsRespositoryTest {

    @Autowired
    private ProductsRepository productsRepository;

    @Test
    @Ignore
    public void getProductsByNameAndLanguage () {
        String name = "po";
        String status = ProductStatusEnumBO.CREATED_BY_USER.toString();
        String language = "en";
        List<ProductPO> productList =  productsRepository.findProducts(name, status, language, 0, 10);
        assertNotNull(productList);
    }

    @Test
    @Ignore
    public void getProductsNoParams() {
        List<ProductPO> productList =  productsRepository.findProducts(null, null, null, 0, 10);
        assertNotNull(productList);
    }

    @Test
    @Ignore
    public void countProducts() {
        Long num=  productsRepository.countProducts("p", "CREATED_BY_USER", "en");
        log.info("[TEST] countProducts - num = " + num);
        assertTrue(num > 0);
    }

    @Test
    @Ignore
    public void countProductsNoParams() {
        Long num=  productsRepository.countProducts(null, null, null);
        log.info("[TEST] countProductsNoParams - num = " + num);
        assertTrue(num > 0);
    }


}
