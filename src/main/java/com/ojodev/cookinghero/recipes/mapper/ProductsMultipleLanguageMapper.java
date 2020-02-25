package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.ProductNew;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.ProductMultiLanguageBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.ProductPO;

public interface ProductsMultipleLanguageMapper {

    ProductMultiLanguageBO toProductMultiLanguageBO(ProductNew productNew, LanguageEnumBO defaultLanguage);

    ProductPO toProductPO(ProductMultiLanguageBO newProduct);
}
