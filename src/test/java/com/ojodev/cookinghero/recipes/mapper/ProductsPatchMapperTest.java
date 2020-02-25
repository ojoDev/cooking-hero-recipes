package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.DescriptiveNameUpdate;
import com.ojodev.cookinghero.recipes.api.model.ProductStatusEnum;
import com.ojodev.cookinghero.recipes.api.model.ProductUpdate;
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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductsPatchMapperTest {

    @Autowired
    private ProductsPatchMapper productsPatchMapper;

    @Autowired
    private ProductStatusEnumMapper productStatusEnumMapper;


    @Test
    public void convertPatchBodyComplete()  {
        ProductBO originProduct = new ProductBO(ProductsExamples.PRODUCT_01_ID,  new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN), ProductStatusEnumBO.CREATED_BY_USER);
        ProductUpdate patchContent = new ProductUpdate(new DescriptiveNameUpdate(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR_CHANGED, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL_CHANGED), ProductStatusEnum.APPROVED_BY_ADMIN);

        ProductBO result = productsPatchMapper.patch(originProduct, patchContent);

        assertNotNull(result);
        assertNotNull(result.getName());
        assertEquals("Language not change", LanguageEnumBO.EN, result.getName().getLanguage());
        assertEquals("Patch change singular field", ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR_CHANGED, result.getName().getSingular());
        assertEquals("Patch change plural field", ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL_CHANGED, result.getName().getPlural());
        assertEquals("Patch change status field", ProductStatusEnumBO.APPROVED_BY_ADMIN, result.getStatus());
    }

    @Test
    public void convertPatchBodyPartial() {

        ProductBO originProduct = new ProductBO(ProductsExamples.PRODUCT_01_ID,  new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN), ProductStatusEnumBO.CREATED_BY_USER);
        ProductUpdate patchContent = initPartialProductUpdate();

        ProductBO result = productsPatchMapper.patch(originProduct, patchContent);

        assertNotNull(result);
        assertNotNull(result.getName());
        assertEquals("Language not change", LanguageEnumBO.EN, result.getName().getLanguage());
        assertEquals("Patch change singular field", ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR_CHANGED, result.getName().getSingular());
        assertEquals("Patch not change plural field", ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, result.getName().getPlural());
        assertEquals("Patch change status field", ProductStatusEnumBO.CREATED_BY_USER, result.getStatus());
    }

    @Test
    public void convertPatchBodyPartialAndNull() {

        ProductBO originProduct = new ProductBO(ProductsExamples.PRODUCT_01_ID,  new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN), ProductStatusEnumBO.APPROVED_BY_ADMIN);
        ProductUpdate patchContent = initPartialAndNullProductUpdate();

        ProductBO result = productsPatchMapper.patch(originProduct, patchContent);

        assertNotNull(result);
        assertNotNull(result.getName());
        assertEquals("Language not change", LanguageEnumBO.EN, result.getName().getLanguage());
        assertEquals("Patch change singular field", ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR_CHANGED, result.getName().getSingular());
        assertNull("Patch set plural field to null", result.getName().getPlural());
        assertEquals("Patch don't set status to null, set to old type",  ProductStatusEnumBO.APPROVED_BY_ADMIN, result.getStatus());
    }

    @Test
    public void convertPatchBodyChangeOnlyStatus() {

        ProductBO originProduct = new ProductBO(ProductsExamples.PRODUCT_01_ID,  new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN), ProductStatusEnumBO.CREATED_BY_USER);
        ProductUpdate patchContent = new ProductUpdate();
        patchContent.setStatus(productStatusEnumMapper.toProductStatusEnum(ProductStatusEnumBO.APPROVED_BY_ADMIN));

        ProductBO result = productsPatchMapper.patch(originProduct, patchContent);

        assertNotNull(result);
        assertNotNull(result.getName());
        assertEquals("Language not change", LanguageEnumBO.EN, result.getName().getLanguage());
        assertEquals("Singular not change", ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, result.getName().getSingular());
        assertEquals("Plural not change", ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, result.getName().getPlural());
        assertEquals("Status should change", ProductStatusEnumBO.APPROVED_BY_ADMIN, result.getStatus());

    }

    private static ProductUpdate initPartialProductUpdate() {
        ProductUpdate productUpdate = new ProductUpdate();
        DescriptiveNameUpdate descriptiveNameUpdate = new DescriptiveNameUpdate();
        descriptiveNameUpdate.setSingular(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR_CHANGED);
        productUpdate.setName(descriptiveNameUpdate);
        return productUpdate;
    }

    private static ProductUpdate initPartialAndNullProductUpdate() {
        ProductUpdate productUpdate = new ProductUpdate();
        DescriptiveNameUpdate descriptiveNameUpdate = new DescriptiveNameUpdate();
        descriptiveNameUpdate.setSingular(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR_CHANGED);
        descriptiveNameUpdate.setPlural(null);
        productUpdate.setName(descriptiveNameUpdate);
        productUpdate.setStatus(null);
        return productUpdate;
    }
}
