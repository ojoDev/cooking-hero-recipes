package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.StepNew;
import com.ojodev.cookinghero.recipes.domain.model.StepNewBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.StepPO;

public interface StepsNewMapper {

    StepNewBO toStepNewBO(StepNew stepNew, String recipeId);

    StepPO toStepNewPO(StepNewBO stepNewBO);

}
