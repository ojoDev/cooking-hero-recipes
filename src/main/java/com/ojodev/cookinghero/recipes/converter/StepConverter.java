package com.ojodev.cookinghero.recipes.converter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import com.ojodev.cookinghero.recipes.bean.Step;
import com.ojodev.cookinghero.recipes.po.StepPO;

@Component
public class StepConverter {

	public Step toStep(StepPO stepPO) {
		Step step = null;
		if (stepPO != null) {
			step = new Step();
			step.setDescription(stepPO.getDescription());
			step.setTime(stepPO.getTime() == null ? null : new BigDecimal(stepPO.getTime()));
			//TODO DMS Media converter
			//step.setMedia(step.getMedia());
		}
		return step;
	}
	
	public StepPO toStepPO(Step step) {
		StepPO stepPO = null;
		if (step != null) {
			stepPO = new StepPO();
			stepPO.setDescription(step.getDescription());
			stepPO.setTime(step.getTime() == null ? null : step.getTime().intValue());
			//TODO DMS Media converter
			//stepPO.setMedia(step.getMedia());
		}
		return stepPO;
	}

	public List<Step> toSteps(List<StepPO> stepsPO) {
		return stepsPO == null ? null : stepsPO.stream().map(e -> toStep(e)).collect(Collectors.toList());
	}

	public List<StepPO> toStepsPO(@Valid List<Step> steps) {
		return steps == null ? null : steps.stream().map(e -> toStepPO(e)).collect(Collectors.toList());
	}
}
