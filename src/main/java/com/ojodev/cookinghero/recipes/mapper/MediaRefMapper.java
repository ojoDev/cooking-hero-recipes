package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.Step;
import com.ojodev.cookinghero.recipes.domain.model.MediaRefBO;
import com.ojodev.cookinghero.recipes.domain.model.StepBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.MediaRefPO;
import com.ojodev.cookinghero.recipes.infrastructure.po.StepPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MediaRefMapper {

    @Mapping(target = "id", source = "objectId")
    MediaRefBO toMediaRefBO(MediaRefPO mediaRefPO);

}
