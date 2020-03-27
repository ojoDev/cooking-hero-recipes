package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.Ingredient;
import com.ojodev.cookinghero.recipes.domain.model.IngredientBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.IngredientPO;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductsMapper.class, MeasuresMapper.class, DescriptiveNameMapper.class})
@DecoratedWith(IngredientsMapperDecorator.class)
public interface IngredientsMapper {

    Ingredient toIngredient(IngredientBO ingredientBO);

    List<Ingredient> toIngredientList(List<IngredientBO> ingredientBO);

    IngredientBO toIngredientBO(IngredientPO ingredient, LanguageEnumBO language);

    ArrayList<IngredientBO> toIngredientBOList(List<IngredientPO> ingredients, LanguageEnumBO language);

}
