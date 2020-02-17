package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.domain.model.DescriptiveNameBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.DescriptiveNamePO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DescriptiveNameMapper {

    //TODO TEST
    DescriptiveNameBO toDescriptiveNameBO(DescriptiveNamePO descriptiveNamePO);
}
