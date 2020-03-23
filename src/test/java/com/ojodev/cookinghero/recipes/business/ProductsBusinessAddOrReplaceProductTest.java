package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.ProductsExamples;
import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;
import com.ojodev.cookinghero.recipes.domain.model.DescriptiveNameBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.ProductBO;
import com.ojodev.cookinghero.recipes.domain.model.ProductStatusEnumBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.DescriptiveNamePO;
import com.ojodev.cookinghero.recipes.infrastructure.po.ProductPO;
import com.ojodev.cookinghero.recipes.infrastructure.repository.ProductsRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductsBusinessAddOrReplaceProductTest {


    @Autowired
    private ProductsBusiness productsBusiness;

    @Autowired
    private Messages messages;

    @MockBean
    private ProductsRepository productsRepository;


    @Test()
    public void addOrReplaceProductReplace() {

        ProductBO productBOEs = new ProductBO(ProductsExamples.PRODUCT_01_ID, new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_SPANISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_SPANISH_PLURAL, LanguageEnumBO.ES));
        ProductPO productPO = new ProductPO(ProductsExamples.PRODUCT_01_ID, Arrays.asList(
                new DescriptiveNamePO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, ProductsExamples.LANGUAGE_EN),
                new DescriptiveNamePO(ProductsExamples.PRODUCT_01_NAME_SPANISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_SPANISH_PLURAL, ProductsExamples.LANGUAGE_ES)),
                ProductStatusEnumBO.APPROVED_BY_ADMIN.toString());

        when(this.productsRepository.findByObjectId(productPO.getObjectId())).thenReturn(Arrays.asList(productPO));

        Assertions.assertDoesNotThrow(() -> productsBusiness.addOrReplaceProduct(productBOEs));

        verify(productsRepository).save(any(ProductPO.class));
    }

    @Test()
    public void addOrReplaceProductAddLanguage() {

        ProductBO productBOEs = new ProductBO(ProductsExamples.PRODUCT_01_ID, new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_SPANISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_SPANISH_PLURAL, LanguageEnumBO.ES));
        ProductPO productPOOnlyEnglish = initProductOnlyEnglish();

        when(this.productsRepository.findByObjectId(productPOOnlyEnglish.getObjectId())).thenReturn(Arrays.asList(productPOOnlyEnglish));

        Assertions.assertDoesNotThrow(() -> productsBusiness.addOrReplaceProduct(productBOEs));

        verify(productsRepository).save(any(ProductPO.class));
    }

    @Test
    public void addOrReplaceProductNoExists() {
        ProductBO productBO = new ProductBO(ProductsExamples.PRODUCT_01_ID, new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN));

        when(this.productsRepository.findByObjectId(ProductsExamples.PRODUCT_01_ID)).thenReturn(null);

        NotFoundException exception = Assertions.assertThrows(NotFoundException.class, () -> productsBusiness.addOrReplaceProduct(productBO));
        assertEquals(messages.get("error.notfound.product.code"), exception.getCode());
        assertEquals(messages.get("error.notfound.product.desc"), exception.getDescription());

        verify(productsRepository, never()).save(any(ProductPO.class));
    }


    private static ProductPO initProductOnlyEnglish() {
        ProductPO ProductPO = new ProductPO();
        ProductPO.setObjectId(ProductsExamples.PRODUCT_01_ID);
        ArrayList<DescriptiveNamePO> names = new ArrayList<>();
        names.add(new DescriptiveNamePO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, ProductsExamples.LANGUAGE_EN));
        ProductPO.setNames(names);
        return ProductPO;
    }

}
