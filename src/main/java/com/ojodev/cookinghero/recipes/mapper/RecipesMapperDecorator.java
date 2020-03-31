package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.domain.model.*;
import com.ojodev.cookinghero.recipes.infrastructure.po.RecipePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.stream.Collectors;

public abstract class RecipesMapperDecorator implements RecipesMapper {

    @Autowired
    private CuisineTypesMapper cuisineTypesMapper;

    @Autowired
    private IngredientsMapper ingredientsMapper;

    @Autowired
    private StepsMapper stepsMapper;

    @Autowired
    @Qualifier("delegate")
    private RecipesMapper delegate;

    @Override
    public RecipeBO toRecipeBO(RecipePO recipePO) {
        RecipeBO recipeBO = toRecipeBOBasic(recipePO);
        recipeBO.setCuisineTypes(getCuisineTypesBO(recipePO));
        recipeBO.setSteps(getStepsBO(recipePO));
        recipeBO.setIngredients(getIngredientsBO(recipePO));
        return recipeBO;
    }

    private List<StepBO> getStepsBO(RecipePO recipePO) {
        return recipePO.getSteps() == null ? null : recipePO.getSteps().stream().map(s -> stepsMapper.toStepBO(s)).collect(Collectors.toList());
    }

    private List<CuisineTypeBO> getCuisineTypesBO(RecipePO recipePO) {
        return recipePO.getCuisineTypes() == null ? null : recipePO.getCuisineTypes().stream().map(c -> cuisineTypesMapper.toCuisineTypeBO(c, LanguageEnumBO.fromValue(recipePO.getLanguage()))).collect(Collectors.toList());
    }

    private List<IngredientBO> getIngredientsBO(RecipePO recipePO) {
        return recipePO.getIngredients() == null ? null : ingredientsMapper.toIngredientBOList(recipePO.getIngredients(), LanguageEnumBO.fromValue(recipePO.getLanguage()));
    }


}
