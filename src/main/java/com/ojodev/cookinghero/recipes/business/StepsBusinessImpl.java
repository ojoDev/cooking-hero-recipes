package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;
import com.ojodev.cookinghero.recipes.domain.model.StepBO;
import com.ojodev.cookinghero.recipes.domain.model.StepNewBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.RecipePO;
import com.ojodev.cookinghero.recipes.infrastructure.po.StepPO;
import com.ojodev.cookinghero.recipes.infrastructure.repository.RecipesRepository;
import com.ojodev.cookinghero.recipes.infrastructure.repository.StepsRepository;
import com.ojodev.cookinghero.recipes.mapper.StepsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

@Component
public class StepsBusinessImpl implements StepsBusiness {

    @Autowired
    private RecipesRepository recipesRepository;

    @Autowired
    private StepsRepository stepsRepository;

    @Autowired
    private StepsMapper stepsMapper;

    @Autowired
    private Messages messages;

    @Override
    public StepBO getStep(String recipeId, String stepId) {
        List<StepPO> stepResults = stepsRepository.findStepInRecipe(recipeId, stepId);
        return null;
    }

    @Override
    public void addStep(@NotNull StepNewBO stepNew) throws NotFoundException {
        RecipePO recipePO = getRecipeOrThrowErrorIfNotExists(stepNew.getRecipeId());
        StepPO stepPO = stepsMapper.toStepPO(stepNew);
        stepPO.setRecipe(recipePO);
        stepsRepository.save(stepPO);
    }

    private RecipePO getRecipeOrThrowErrorIfNotExists(@NotNull String recipeId) throws NotFoundException {
        List<RecipePO> recipePOList = recipesRepository.findByObjectId(recipeId);
        if (recipePOList == null || recipePOList.isEmpty()) {
            throw new NotFoundException(messages.get("error.notfound.recipe.code"), messages.get("error.notfound.recipe.desc"));
        }
        return recipePOList.get(0);
    }
}
