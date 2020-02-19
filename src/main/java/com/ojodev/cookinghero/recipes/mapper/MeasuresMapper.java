package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.Measure;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.MeasureBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.MeasurePO;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
@DecoratedWith(MeasuresMapperDecorator.class)
public interface MeasuresMapper {

	MeasuresMapper INSTANCE = Mappers.getMapper(MeasuresMapper.class);

	Measure toMeasure(MeasureBO measureBO);

	List<Measure> toMeasuresList(List<MeasureBO> measureBOList);

	MeasureBO toMeasureBO(MeasurePO measure, LanguageEnumBO language);

    MeasureBO patch(MeasureBO origin, Map<String, Object> content);
}
