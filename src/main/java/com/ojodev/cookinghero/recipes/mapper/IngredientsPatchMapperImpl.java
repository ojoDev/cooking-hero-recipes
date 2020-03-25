package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.IngredientUpdate;
import com.ojodev.cookinghero.recipes.api.model.MeasureRef;
import com.ojodev.cookinghero.recipes.domain.model.IngredientBO;
import com.ojodev.cookinghero.recipes.domain.model.IngredientNewBO;
import com.ojodev.cookinghero.recipes.domain.model.MeasureBO;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Optional;

@Component
public class IngredientsPatchMapperImpl implements IngredientsPatchMapper {


    @Override
    public IngredientNewBO patch(@NotNull IngredientBO origin, @NotNull IngredientUpdate patch) {
        IngredientNewBO resultIngredient = new IngredientNewBO(origin.getId());
        resultIngredient.setProductName(getNullOrValue(patch.getProductNameOpt(), origin.getProduct() == null ? null : origin.getProduct().getName().getSingular()));
        resultIngredient.setQuantity(getNullOrValue(patch.getQuantityOpt(), origin.getQuantity()));
        resultIngredient.setMeasureId(getNullOrValue(patch.getMeasureOpt(), origin.getMeasure()));
        if (patch.getMeasure() != null) {
            resultIngredient.setMeasureId(patch.getMeasureOpt().isPresent() ? patch.getMeasureOpt().get().getId()
                    : origin.getMeasure() == null ? null : origin.getMeasure().getId());
        }
        return resultIngredient;
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

    private String getNullOrValue(Optional<MeasureRef> patchValue, MeasureBO originalMeasure) {
        if (patchValue == null) {
            return null;
        } else {
            return patchValue.isPresent() ? patchValue.get().getId()
                    : originalMeasure == null ? null : originalMeasure.getId();
        }
    }

    private BigDecimal getNullOrValue(Optional<BigDecimal> optionalValue, BigDecimal value) {
        if (optionalValue == null) {
            return null;
        } else {
            return optionalValue.isPresent() ? optionalValue.get() : value;
        }
    }
}

