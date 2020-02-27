package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.Product;
import com.ojodev.cookinghero.recipes.api.model.ProductStatusEnum;
import com.ojodev.cookinghero.recipes.data.ProductsExamples;
import com.ojodev.cookinghero.recipes.domain.model.DescriptiveNameBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.ProductBO;
import com.ojodev.cookinghero.recipes.domain.model.ProductStatusEnumBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.DescriptiveNamePO;
import com.ojodev.cookinghero.recipes.infrastructure.po.ProductPO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductsMapperTest {

    @Autowired
    private ProductsMapper productsMapper;


    @Test
    public void convertProductBOToProduct() {

        ProductBO originProduct = new ProductBO(ProductsExamples.PRODUCT_01_ID, new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN), ProductStatusEnumBO.APPROVED_BY_ADMIN);

        Product resultProduct = productsMapper.toProduct(originProduct);
        assertNotNull(resultProduct);
        assertEquals(ProductsExamples.PRODUCT_01_ID, resultProduct.getId());
        assertNotNull(resultProduct.getName());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, resultProduct.getName().getSingular());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, resultProduct.getName().getPlural());
        assertEquals(ProductStatusEnum.APPROVED_BY_ADMIN, resultProduct.getStatus());
    }


    @Test
    public void convertProductBOListToProductsList() {

        ProductBO originProduct01 = new ProductBO(ProductsExamples.PRODUCT_01_ID, new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN), ProductStatusEnumBO.APPROVED_BY_ADMIN);
        ProductBO originProduct02 = new ProductBO(ProductsExamples.PRODUCT_02_ID, new DescriptiveNameBO(ProductsExamples.PRODUCT_02_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_02_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN), ProductStatusEnumBO.CREATED_BY_USER);

        List<Product> resultProduct = productsMapper.toProductsList(Arrays.asList(originProduct01, originProduct02));
        assertNotNull(resultProduct);
        assertEquals(2, resultProduct.size());
        assertEquals(ProductsExamples.PRODUCT_01_ID, resultProduct.get(0).getId());
        assertNotNull(resultProduct.get(0).getName());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, resultProduct.get(0).getName().getSingular());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, resultProduct.get(0).getName().getPlural());
        assertEquals(ProductStatusEnum.APPROVED_BY_ADMIN, resultProduct.get(0).getStatus());
        assertEquals(ProductsExamples.PRODUCT_02_ID, resultProduct.get(1).getId());
        assertNotNull(resultProduct.get(1).getName());
        assertEquals(ProductsExamples.PRODUCT_02_NAME_ENGLISH_SINGULAR, resultProduct.get(1).getName().getSingular());
        assertEquals(ProductsExamples.PRODUCT_02_NAME_ENGLISH_PLURAL, resultProduct.get(1).getName().getPlural());
        assertEquals(ProductStatusEnum.CREATED_BY_USER, resultProduct.get(1).getStatus());
    }

    @Test
    public void convertProductPOToProductBO() {

        ProductPO originProductPO = new ProductPO(ProductsExamples.PRODUCT_01_ID, Arrays.asList(
                new DescriptiveNamePO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, ProductsExamples.LANGUAGE_EN),
                new DescriptiveNamePO(ProductsExamples.PRODUCT_01_NAME_SPANISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_SPANISH_PLURAL, ProductsExamples.LANGUAGE_ES)),
                ProductStatusEnumBO.APPROVED_BY_ADMIN.toString());

        ProductBO productEnBO = productsMapper.toProductBO(originProductPO, LanguageEnumBO.EN);
        assertNotNull(productEnBO);
        assertEquals(ProductsExamples.PRODUCT_01_ID, productEnBO.getId());
        assertNotNull(productEnBO.getName());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, productEnBO.getName().getSingular());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, productEnBO.getName().getPlural());
        assertEquals(ProductsExamples.LANGUAGE_EN, productEnBO.getName().getLanguage().toString());
        assertEquals(ProductStatusEnumBO.APPROVED_BY_ADMIN, productEnBO.getStatus());

        ProductBO productEsBO = productsMapper.toProductBO(originProductPO, LanguageEnumBO.ES);
        assertNotNull(productEsBO);
        assertEquals(ProductsExamples.PRODUCT_01_ID, productEsBO.getId());
        assertNotNull(productEsBO.getName());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_SPANISH_SINGULAR, productEsBO.getName().getSingular());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_SPANISH_PLURAL, productEsBO.getName().getPlural());
        assertEquals(ProductsExamples.LANGUAGE_ES, productEsBO.getName().getLanguage().toString());
        assertEquals(ProductStatusEnumBO.APPROVED_BY_ADMIN, productEsBO.getStatus());

    }


}
