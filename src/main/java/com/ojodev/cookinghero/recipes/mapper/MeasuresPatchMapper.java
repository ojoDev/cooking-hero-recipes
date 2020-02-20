package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.MeasureUpdate;
import com.ojodev.cookinghero.recipes.domain.model.MeasureBO;

public interface MeasuresPatchMapper {

	MeasureBO patch(MeasureBO origin, MeasureUpdate patch);
}
