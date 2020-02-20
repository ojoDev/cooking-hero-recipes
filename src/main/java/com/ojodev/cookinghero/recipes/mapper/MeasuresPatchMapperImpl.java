package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.MeasureUpdate;
import com.ojodev.cookinghero.recipes.domain.model.DescriptiveNameBO;
import com.ojodev.cookinghero.recipes.domain.model.MeasureBO;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MeasuresPatchMapperImpl implements MeasuresPatchMapper {


    //TODO DMS: Hacer un mapper genérico para PATCH, con reflections
    @Override
    public MeasureBO patch(MeasureBO origin, MeasureUpdate patch) {
        MeasureBO result = new MeasureBO();
        result.setId(origin.getId());

        DescriptiveNameBO nameBO = new DescriptiveNameBO();
        if (patch.getName() == null) {
            nameBO.setSingular(origin.getName().getSingular());
            nameBO.setPlural(origin.getName().getPlural());
        } else {
            nameBO.setSingular(getNullOrValue(patch.getName().getSingularOpt(), origin.getName().getSingular()));
            nameBO.setPlural(getNullOrValue(patch.getName().getPluralOpt(), origin.getName().getPlural()));
            nameBO.setLanguage(origin.getName().getLanguage());
            result.setName(nameBO);
        }
        return result;
    }

    /**
     * Path a Optional variable.
     * If Optional variable is null, return null.
     * If is empty, return value.
     * If is not empty, return optionalValue value.
     *
     * @param optionalValue Optional String
     * @return null or value
     */
    private String getNullOrValue(Optional<String> optionalValue, String value) {
        if (optionalValue == null) {
            return null;
        } else {
            return optionalValue.isPresent() ? optionalValue.get() : value;
        }
    }


}
