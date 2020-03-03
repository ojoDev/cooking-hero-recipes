package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.MeasureUpdate;
import com.ojodev.cookinghero.recipes.domain.constants.RecipesConstants;
import com.ojodev.cookinghero.recipes.domain.model.IngredientBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.MeasureBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.DescriptiveNamePO;
import com.ojodev.cookinghero.recipes.infrastructure.po.IngredientPO;
import com.ojodev.cookinghero.recipes.infrastructure.po.MeasurePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class IngredientsMapperDecorator implements IngredientsMapper {

    @Autowired
    @Qualifier("delegate")
    private IngredientsMapper delegate;

    @Autowired
    private ProductsMapper productsMapper;

    @Autowired
    private MeasuresMapper measuresMapper;

    @Override
    public IngredientBO toIngredientBO(IngredientPO ingredient, LanguageEnumBO language) {
        IngredientBO ingredientBO = new IngredientBO();
        ingredientBO.setId(ingredient.getObjectId());
        ingredientBO.setProduct(productsMapper.toProductBO(ingredient.getProduct(), language));
        ingredientBO.setQuantity(ingredient.getQuantity());
        ingredientBO.setMeasure(measuresMapper.toMeasureBO(ingredient.getMeasure(), language));
        return ingredientBO;
    }

    @Override
    public ArrayList<IngredientBO> toIngredientBOList(List<IngredientPO> ingredients, LanguageEnumBO language){
        return ingredients.stream().map(i -> toIngredientBO(i, language)).collect(Collectors.toCollection(ArrayList::new));
    }

}
