package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.MeasureUpdate;
import com.ojodev.cookinghero.recipes.domain.constants.RecipesConstants;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.ProductBO;
import com.ojodev.cookinghero.recipes.domain.model.ProductStatusEnumBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.DescriptiveNamePO;
import com.ojodev.cookinghero.recipes.infrastructure.po.ProductPO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ProductsMapperDecorator implements ProductsMapper {

    @Autowired
    @Qualifier("delegate")
    private ProductsMapper delegate;

    @Autowired
    private DescriptiveNameMapper descriptiveNameMapper;


    @Override
    public ProductBO toProductBO(ProductPO productPO, LanguageEnumBO language) {
        if (productPO == null) {
            return null;
        }
        ProductBO productBO = new ProductBO();
        productBO.setId(productPO.getObjectId());
        DescriptiveNamePO descriptiveNamePO = selectNameByLanguage(productPO.getNames(), setDefaultLanguageIfNull(language).toString());
        productBO.setName(descriptiveNameMapper.toDescriptiveNameBO(descriptiveNamePO));
        productBO.setStatus(StringUtils.isEmpty(productPO.getStatus()) ? null : ProductStatusEnumBO.valueOf(productPO.getStatus()));
        return productBO;
    }

    private DescriptiveNamePO selectNameByLanguage(List<DescriptiveNamePO> names, String language) {
        List<DescriptiveNamePO> filteredNames = names.stream().filter(n -> language.equals(n.getLanguage())).collect(Collectors.toList());
        if (filteredNames.isEmpty()) {
            return selectNameByLanguage(names, RecipesConstants.DEFAULT_LANGUAGE.toString());
        } else {
            return filteredNames.get(0);
        }
    }

    private LanguageEnumBO setDefaultLanguageIfNull(LanguageEnumBO language) {
        return language == null ? RecipesConstants.DEFAULT_LANGUAGE : language;
    }

    public ProductBO toProductBO(MeasureUpdate measureUpdate, String id, LanguageEnumBO language) {
        if (measureUpdate == null) {
            return null;
        }
        ProductBO productBO = new ProductBO();
        productBO.setName(descriptiveNameMapper.toDescriptiveNameBO(measureUpdate.getName(), language));
        productBO.setId(id);
        return productBO;
    }


}
