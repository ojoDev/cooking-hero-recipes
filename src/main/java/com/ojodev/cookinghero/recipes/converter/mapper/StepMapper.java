package com.ojodev.cookinghero.recipes.converter.mapper;

import org.mapstruct.Mapper;

import com.ojodev.cookinghero.recipes.bean.Step;
import com.ojodev.cookinghero.recipes.po.StepPO;

@Mapper(componentModel = "spring")
public interface StepMapper {
	
	Step toStep(StepPO stepPO);

	StepPO toStepPO(StepPO step);
}
