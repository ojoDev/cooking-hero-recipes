package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.ProductUpdate;
import com.ojodev.cookinghero.recipes.domain.model.ProductBO;

public interface ProductsPatchMapper {

	ProductBO patch(ProductBO origin, ProductUpdate patch);
}
