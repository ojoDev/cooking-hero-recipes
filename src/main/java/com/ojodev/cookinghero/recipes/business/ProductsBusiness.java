package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.domain.exception.ApiBadRequestException;
import com.ojodev.cookinghero.recipes.domain.exception.ApiException;
import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.ProductBO;
import com.ojodev.cookinghero.recipes.domain.model.ProductMultiLanguageBO;
import com.ojodev.cookinghero.recipes.domain.model.ProductStatusEnumBO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ProductsBusiness {

    List<ProductBO> getProducts(LanguageEnumBO language, int offset, int limit);

    List<ProductBO> getProducts(String name, ProductStatusEnumBO status, LanguageEnumBO language, int offset, int limit);

    Long countProducts(String name, ProductStatusEnumBO status, LanguageEnumBO language);

    Optional<ProductBO> getProduct(String productId, LanguageEnumBO language);

    void addProduct(ProductMultiLanguageBO newProduct) throws ApiBadRequestException;

    void addOrReplaceProduct(ProductBO productBO) throws ApiException;

    void deleteProduct(String productId) throws NotFoundException;
}
