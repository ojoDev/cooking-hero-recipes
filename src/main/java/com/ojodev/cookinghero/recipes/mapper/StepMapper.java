package com.ojodev.cookinghero.recipes.mapper;

import org.mapstruct.Mapper;

import com.ojodev.cookinghero.recipes.api.model.Step;
import com.ojodev.cookinghero.recipes.infrastructure.po.StepPO;

@Mapper(componentModel = "spring")
public interface StepMapper {
	
	Step toStep(StepPO stepPO);

	StepPO toStepPO(StepPO step);
}
