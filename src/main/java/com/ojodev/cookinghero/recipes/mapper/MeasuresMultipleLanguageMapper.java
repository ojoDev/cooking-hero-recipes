package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.MeasureNew;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.MeasureMultiLanguageBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.MeasurePO;

public interface MeasuresMultipleLanguageMapper {

    MeasureMultiLanguageBO toMeasureMultiLanguageBO(MeasureNew measureNew, LanguageEnumBO defaultLanguage);

    MeasurePO toMeasurePO(MeasureMultiLanguageBO newMeasure);
}
