package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.StepNew;
import com.ojodev.cookinghero.recipes.domain.model.StepNewBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.StepPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StepsNewMapperImpl implements StepsNewMapper {

    @Autowired
    private MediaRefMapper mediaRefMapper;

    @Override
    public StepNewBO toStepNewBO(StepNew stepNew, String recipeId) {
        return new StepNewBO(recipeId, stepNew.getPosition(), stepNew.getDescription(), mediaRefMapper.toMediaRefBO(stepNew.getMedia()));
    }

    @Override
    public StepPO toStepNewPO(StepNewBO stepNewBO) {
        return null;
    }
}
