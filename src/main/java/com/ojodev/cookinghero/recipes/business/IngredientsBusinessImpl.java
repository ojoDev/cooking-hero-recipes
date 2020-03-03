package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;
import com.ojodev.cookinghero.recipes.domain.model.IngredientBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.IngredientPO;
import com.ojodev.cookinghero.recipes.infrastructure.po.RecipePO;
import com.ojodev.cookinghero.recipes.infrastructure.repository.IngredientsRepository;
import com.ojodev.cookinghero.recipes.infrastructure.repository.RecipesRepository;
import com.ojodev.cookinghero.recipes.mapper.IngredientsMapper;
import com.ojodev.cookinghero.recipes.mapper.LanguageEnumMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class IngredientsBusinessImpl implements IngredientsBusiness {

    @Autowired
    private IngredientsRepository ingredientsRepository;

    @Autowired
    private RecipesRepository recipesRepository;

    @Autowired
    private IngredientsMapper ingredientsMapper;

    @Autowired
    private LanguageEnumMapper languageEnumMapper;


    @Autowired
    private Messages messages;

    @Override
    public List<IngredientBO> getIngredients(String recipeId) throws NotFoundException {
        List<RecipePO> recipes = recipesRepository.findByObjectId(recipeId);
        throwErrorIfRecipeNotExists(recipes);
        return ingredientsMapper.toIngredientBOList(ingredientsRepository.findByRecipeId(recipeId), LanguageEnumBO.fromValue(recipes.get(0).getLanguage()));
    }

    @Override
    public Optional<IngredientBO> getIngredient(String recipeId, String ingredientId) throws NotFoundException {
        if (StringUtils.isEmpty(recipeId) || StringUtils.isEmpty(ingredientId)) {
            return Optional.empty();
        }
        List<RecipePO> recipes = recipesRepository.findByObjectId(recipeId);
        throwErrorIfRecipeNotExists(recipes);
        List<IngredientPO> ingredientPOList = recipes.get(0).getIngredients().stream().filter(i -> ingredientId.equals(i.getObjectId())).collect(Collectors.toList());
        if (ingredientPOList.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(ingredientsMapper.toIngredientBO(ingredientPOList.get(0), LanguageEnumBO.fromValue(recipes.get(0).getLanguage())));
    }


    private void throwErrorIfRecipeNotExists(List<RecipePO> recipes) throws NotFoundException {
        if (recipes == null || recipes.isEmpty()) {
            throw new NotFoundException(messages.get("error.notfound.recipe.code"), messages.get("error.notfound.recipe.desc"));
        }
    }

}
