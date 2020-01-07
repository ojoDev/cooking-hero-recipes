package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.api.model.LanguageEnum;
import com.ojodev.cookinghero.recipes.domain.model.CuisineTypeBO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CuisineTypesBusiness {


    public List<CuisineTypeBO> getCuisineTypes(Optional<String> name, Optional<LanguageEnum> language) {
        List<CuisineTypeBO> cuisineTypesList = new ArrayList<>();
        //TODO DMS: Implement
        return cuisineTypesList;
    }

}
