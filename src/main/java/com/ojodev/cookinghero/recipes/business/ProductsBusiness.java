package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.domain.exception.ApiBadRequestException;
import com.ojodev.cookinghero.recipes.domain.exception.ApiException;
import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;
import com.ojodev.cookinghero.recipes.domain.model.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ProductsBusiness {

    List<ProductBO> getProducts(LanguageEnumBO language, int limit, int offset);

    List<ProductBO> getProducts(String name,  LanguageEnumBO language, int limit, int offset);

    Long countProducts(String name,  LanguageEnumBO language, int limit, int offset);

    Optional<ProductBO> getProduct(String productId, LanguageEnumBO language);

    void addProduct(ProductMultiLanguageBO newProduct) throws ApiBadRequestException;

    void addOrReplaceProduct(ProductBO productBO) throws ApiException;

    void deleteProduct(String productId) throws NotFoundException;
}
