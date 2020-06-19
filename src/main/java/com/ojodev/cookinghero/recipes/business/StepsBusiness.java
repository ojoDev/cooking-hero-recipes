package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;
import com.ojodev.cookinghero.recipes.domain.model.StepBO;
import com.ojodev.cookinghero.recipes.domain.model.StepNewBO;

public interface StepsBusiness {

    StepBO getStep(String recipeId, String stepId);

    void addStep(StepNewBO stepNew) throws NotFoundException;

}
