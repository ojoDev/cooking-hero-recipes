package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.LanguageEnum;
import com.ojodev.cookinghero.recipes.api.model.Recipe;
import com.ojodev.cookinghero.recipes.api.model.RecipeNew;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.RecipeBO;
import com.ojodev.cookinghero.recipes.domain.model.RecipeNewBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.RecipePO;
import org.joda.time.DateTime;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {LanguageEnumMapper.class, CuisineTypesMapper.class, IngredientsMapper.class,}, imports = UUID.class)
@DecoratedWith(RecipesMapperDecorator.class)
public interface RecipesMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "objectId", source = "recipeNew.id", defaultValue = "java(java.util.UUID.randomUUID().toString())")
    @Mapping(target = "status", constant = "DRAFT")
    @Mapping(target = "language", source = "language", qualifiedByName = "languageEnumBOToString")
    RecipePO toRecipePO(RecipeNewBO recipeNew, DateTime creationDate, LanguageEnumBO language);

    @Named("languageEnumBOToString")
    static String languageEnumBOToString(LanguageEnumBO language) {
        return language.toString();
    }

    default RecipeNewBO toRecipeNewBO(RecipeNew recipeNew) {
        RecipeNewBO recipeNewBO = new RecipeNewBO(recipeNew.getName(), recipeNew.getUserId());
        recipeNewBO.setDescription(recipeNew.getDescription());
        recipeNewBO.setPreparationTime(recipeNew.getPreparationTime());
        recipeNewBO.setDifficulty(recipeNew.getDifficulty());
        recipeNewBO.setUserId(recipeNew.getUserId());
        return recipeNewBO;
    }

    @Mapping(target = "id", source = "objectId")
    @Mapping(target = "language", source = "language", qualifiedByName = "stringToLanguageEnum")
    RecipeBO toRecipeBOBasic(RecipePO recipePO);

    RecipeBO toRecipeBO(RecipePO recipePO);

    @Named("stringToLanguageEnum")
    static LanguageEnumBO languageEnumBOToString(String language) {
        return language == null ? null : LanguageEnumBO.fromValue(language.toString());
    }

    Recipe toRecipe(RecipeBO recipeBO);

}
