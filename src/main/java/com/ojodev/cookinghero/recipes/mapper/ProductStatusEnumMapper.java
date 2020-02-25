package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.ProductStatusEnum;
import com.ojodev.cookinghero.recipes.domain.model.ProductStatusEnumBO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductStatusEnumMapper {

    ProductStatusEnum toProductStatusEnum(ProductStatusEnumBO productStatusEnumBO);

    ProductStatusEnumBO toProductStatusEnumBO(ProductStatusEnum productStatusEnum);
}
