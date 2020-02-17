package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.LanguageEnum;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LanguageEnumMapper {

    LanguageEnumBO toLanguageEnumBO(LanguageEnum languageEnum);

    LanguageEnum toLanguageEnum(LanguageEnumBO languageEnumBO);

}
