package com.ojodev.cookinghero.recipes.business;

import com.google.common.net.HttpHeaders;
import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.domain.constants.RecipesConstants;
import com.ojodev.cookinghero.recipes.domain.exception.*;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.ProductBO;
import com.ojodev.cookinghero.recipes.domain.model.ProductMultiLanguageBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.DescriptiveNamePO;
import com.ojodev.cookinghero.recipes.infrastructure.po.ProductPO;
import com.ojodev.cookinghero.recipes.infrastructure.repository.ProductsRepository;
import com.ojodev.cookinghero.recipes.mapper.DescriptiveNameMapper;
import com.ojodev.cookinghero.recipes.mapper.ProductsMapper;
import com.ojodev.cookinghero.recipes.mapper.ProductsMultipleLanguageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class ProductsBusinessImpl implements ProductsBusiness {

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private ProductsMapper productsMapper;

    @Autowired
    private ProductsMultipleLanguageMapper productsMultipleLanguageMapper;

    @Autowired
    private DescriptiveNameMapper descriptiveNameMapper;

    @Autowired
    private Messages messages;

    @Override
    public Optional<ProductBO> getProduct(String productId, LanguageEnumBO language) {
        List<ProductPO> productPOList = productsRepository.findByObjectId(productId);
        return productPOList == null || productPOList.isEmpty() ? Optional.empty() : Optional.ofNullable(productsMapper.toProductBO(productPOList.get(0), language));
    }

    @Override
    public void addProduct(ProductMultiLanguageBO newProduct) throws ApiBadRequestException {
        List<ProductPO> existentProducts = productsRepository.findByObjectId(newProduct.getId());
        if (existentProducts != null && !existentProducts.isEmpty()) {
            throw new ApiBadRequestException(messages.get("error.badrequest.duplicatedentityname.code"), messages.get("error.badrequest.duplicatedentityname.desc", "product"));
        }
        productsRepository.save(productsMultipleLanguageMapper.toProductPO(newProduct));
    }

    @Override
    public void addOrReplaceProduct(ProductBO productBO) throws ApiException {
        List<ProductPO> existentProducts = productsRepository.findByObjectId(productBO.getId());

        throwErrorIfNotExists(existentProducts);

        ProductPO productPO = existentProducts.get(0);

        if (existLanguageName(productPO, productBO)) {
            updateLanguageName(productPO, productBO);
        } else {
            productPO.getNames().add(descriptiveNameMapper.toDescriptiveNamePO(productBO.getName()));
        }
        productPO.setStatus(productBO.getStatus().toString());
        productsRepository.save(productPO);
    }

    @Override
    public void deleteProduct(String productId) throws NotFoundException {
        throwErrorIfNotExists(productsRepository.findByObjectId(productId));
        //TODO DMS: Faltaría evaluar si el producto está en uso, ne cuyo caso no debería borrarse
        productsRepository.deleteById(productId);
    }

    private LanguageEnumBO setDefaultLanguageIfNull(LanguageEnumBO language) {
        return language == null ? RecipesConstants.DEFAULT_LANGUAGE : language;
    }

    private void throwErrorIfNotExists(List<ProductPO> products) throws NotFoundException {
        if (products == null || products.isEmpty()) {
            throw new NotFoundException(messages.get("error.notfound.code"), messages.get("error.notfound.desc"));
        }
    }

    private boolean existLanguageName(ProductPO productPO, ProductBO productBO) {
        return productPO.getNames().stream().filter(n -> n.getLanguage().equals(productBO.getName().getLanguage().toString())).findAny().isPresent();
    }

    private void updateLanguageName(ProductPO productPO, ProductBO productBO) {
        List<DescriptiveNamePO> newNames = new ArrayList<>();
        for (DescriptiveNamePO name : productPO.getNames()) {
            if (name.getLanguage().equals(productBO.getName().getLanguage().toString())) {
                newNames.add(descriptiveNameMapper.toDescriptiveNamePO(productBO.getName()));
            } else {
                newNames.add(name);
            }
        }
        productPO.setNames(newNames);
    }
}
