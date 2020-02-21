package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.DescriptiveNameUpdate;
import com.ojodev.cookinghero.recipes.api.model.ProductStatusEnum;
import com.ojodev.cookinghero.recipes.api.model.ProductUpdate;
import com.ojodev.cookinghero.recipes.data.ProductsExamples;
import com.ojodev.cookinghero.recipes.domain.exception.ApiException;
import com.ojodev.cookinghero.recipes.domain.model.DescriptiveNameBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.ProductBO;
import com.ojodev.cookinghero.recipes.utils.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductsPatchMapperTest {

    @Autowired
    private ProductsPatchMapper productsPatchMapper;

    @Autowired
    private FileUtils fileUtils;


    @Test
    public void convertPatchBodyComplete() throws ApiException, FileNotFoundException {
        ProductBO originProduct = new ProductBO(ProductsExamples.PRODUCT_01_ID,  new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN));
        ProductUpdate patchContent = new ProductUpdate(new DescriptiveNameUpdate(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR_CHANGED, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL_CHANGED),
                ProductStatusEnum.APPROVED_BY_ADMIN);

        ProductBO result = productsPatchMapper.patch(originProduct, patchContent);

        assertNotNull(result);
        assertNotNull(result.getName());
        assertEquals("Language not change", LanguageEnumBO.EN, result.getName().getLanguage());
        assertEquals("Patch change singular field", ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR_CHANGED, result.getName().getSingular());
        assertEquals("Patch change plural field", ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL_CHANGED, result.getName().getPlural());
    }

    @Test
    public void convertPatchBodyPartial() throws ApiException {

        ProductBO originProduct = new ProductBO(ProductsExamples.PRODUCT_01_ID,  new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN));
        ProductUpdate patchContent = initPartialProductUpdate();

        ProductBO result = productsPatchMapper.patch(originProduct, patchContent);

        assertNotNull(result);
        assertNotNull(result.getName());
        assertEquals("Language not change", LanguageEnumBO.EN, result.getName().getLanguage());
        assertEquals("Patch change singular field", ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR_CHANGED, result.getName().getSingular());
        assertEquals("Patch not change plural field", ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, result.getName().getPlural());
    }

    @Test
    public void convertPatchBodyPartialAndNull() throws ApiException {

        ProductBO originProduct = new ProductBO(ProductsExamples.PRODUCT_01_ID,  new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN));
        ProductUpdate patchContent = initPartialAndNullProductUpdate();

        ProductBO result = productsPatchMapper.patch(originProduct, patchContent);

        assertNotNull(result);
        assertNotNull(result.getName());
        assertEquals("Language not change", LanguageEnumBO.EN, result.getName().getLanguage());
        assertEquals("Patch change singular field", ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR_CHANGED, result.getName().getSingular());
        assertNull("Patch set plural field to null", result.getName().getPlural());
    }


    private static ProductUpdate initPartialProductUpdate() {
        DescriptiveNameUpdate descriptiveNameUpdate = new DescriptiveNameUpdate();
        descriptiveNameUpdate.setSingular(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR_CHANGED);
        return new ProductUpdate(descriptiveNameUpdate,  ProductStatusEnum.APPROVED_BY_ADMIN););
    }

    private static ProductUpdate initPartialAndNullProductUpdate() {
        DescriptiveNameUpdate descriptiveNameUpdate = new DescriptiveNameUpdate();
        descriptiveNameUpdate.setSingular(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR_CHANGED);
        descriptiveNameUpdate.setPlural(null);
        return new ProductUpdate(descriptiveNameUpdate, ProductStatusEnum.APPROVED_BY_ADMIN););
    }
}
