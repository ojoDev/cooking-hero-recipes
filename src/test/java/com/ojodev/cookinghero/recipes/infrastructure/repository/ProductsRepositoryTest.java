package com.ojodev.cookinghero.recipes.infrastructure.repository;

import com.ojodev.cookinghero.recipes.RecipesApplication;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.ProductPO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class })
@SpringBootTest(classes = RecipesApplication.class)
@Slf4j
public class ProductsRepositoryTest {

    @Autowired
    ProductsRepository productsRepository;

    @Test
    public void findByName() {
        List<ProductPO> productList = productsRepository.findByName("po", "en", 3 , 0);
        assertNotNull(productList);
    }

    @Test
    public void countByName() {
       Long productNum = productsRepository.countByName("po", "en");
        assertEquals(Long.valueOf(1), productNum);
    }




    @Test
    public void findNoName() {
        List<ProductPO> productList = productsRepository.findByName("", "en",3 , 0);
        assertNotNull(productList);
    }
}
