package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.ProductNew;
import com.ojodev.cookinghero.recipes.api.model.ProductStatusEnum;
import com.ojodev.cookinghero.recipes.domain.model.DescriptiveNameBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.ProductMultiLanguageBO;
import com.ojodev.cookinghero.recipes.domain.model.ProductStatusEnumBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.DescriptiveNamePO;
import com.ojodev.cookinghero.recipes.infrastructure.po.ProductPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductsMultipleLanguageMapperImpl implements ProductsMultipleLanguageMapper {

    @Autowired
    private DescriptiveNameMapper descriptiveNameMapper;

    @Override
    public ProductMultiLanguageBO toProductMultiLanguageBO(ProductNew newProduct, LanguageEnumBO defaultLanguage) {
        List<DescriptiveNameBO> names = newProduct.getNames().stream().map(n -> descriptiveNameMapper.toDescriptiveNameBO(n.getName(), n.getLanguage())).collect(Collectors.toList());
        ProductStatusEnumBO productStatusEnumBO = ProductStatusEnumBO.valueOf(newProduct.getStatus().toString());
        return new ProductMultiLanguageBO.Builder(names, defaultLanguage, productStatusEnumBO).build();
    }

    @Override
    public ProductPO toProductPO(ProductMultiLanguageBO newProduct) {
        List<DescriptiveNamePO> names = newProduct.getNames().stream().map(n -> descriptiveNameMapper.toDescriptiveNamePO(n)).collect(Collectors.toList());
        return new ProductPO(newProduct.getId(), names, newProduct.getStatus().toString());
    }
}
