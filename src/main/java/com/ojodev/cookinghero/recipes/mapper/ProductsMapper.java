package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.Product;
import com.ojodev.cookinghero.recipes.api.model.ProductUpdate;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.ProductBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.ProductPO;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
@DecoratedWith(ProductsMapperDecorator.class)
public interface ProductsMapper {

    ProductsMapper INSTANCE = Mappers.getMapper(ProductsMapper.class);

    Product toProduct(ProductBO productBO);

    List<Product> toProductsList(List<ProductBO> productBOList);

    ProductBO toProductBO(ProductPO productPO, LanguageEnumBO language);

    ProductBO toProductBO(ProductUpdate productUpdate, String productId, LanguageEnumBO language);

}
