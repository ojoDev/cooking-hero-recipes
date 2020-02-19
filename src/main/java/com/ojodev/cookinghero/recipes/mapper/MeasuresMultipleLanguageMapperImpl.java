package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.MeasureNew;
import com.ojodev.cookinghero.recipes.domain.model.*;
import com.ojodev.cookinghero.recipes.infrastructure.po.CuisineTypePO;
import com.ojodev.cookinghero.recipes.infrastructure.po.DescriptiveNamePO;
import com.ojodev.cookinghero.recipes.infrastructure.po.LanguageNamePO;
import com.ojodev.cookinghero.recipes.infrastructure.po.MeasurePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MeasuresMultipleLanguageMapperImpl implements MeasuresMultipleLanguageMapper {

    @Autowired
    private DescriptiveNameMapper descriptiveNameMapper;

    @Override
    public MeasureMultiLanguageBO toMeasureMultiLanguageBO(MeasureNew measureNew, LanguageEnumBO defaultLanguage) {
        List<DescriptiveNameBO> names = measureNew.getNames().stream().map(n -> descriptiveNameMapper.toDescriptiveNameBO(n.getName(), n.getLanguage())).collect(Collectors.toList());
        return new MeasureMultiLanguageBO.Builder(names, defaultLanguage).build();
    }

    @Override
    public MeasurePO toMeasurePO(MeasureMultiLanguageBO newMeasure) {
        List<DescriptiveNamePO> names = newMeasure.getNames().stream().map(n -> descriptiveNameMapper.toDescriptiveNamePO(n)).collect(Collectors.toList());
        return new MeasurePO(newMeasure.getId(), names);
    }
}
