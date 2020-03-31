package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.RecipeBO;
import com.ojodev.cookinghero.recipes.domain.model.RecipeNewBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.RecipePO;
import com.ojodev.cookinghero.recipes.infrastructure.repository.RecipesRepository;
import com.ojodev.cookinghero.recipes.mapper.RecipesMapper;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Component
public class RecipesBusinessImpl implements RecipesBusiness {

    @Autowired
    private RecipesRepository recipesRepository;

    @Autowired
    private RecipesMapper recipesMapper;

    @Override
    public void addRecipe(RecipeNewBO recipeNew) {
        throwsExceptionIfUserNotExists(recipeNew.getUserId());
        //TODO DMS: Debe recuperarse el lenguaje del usuario y dar de alta el recipe con él. Hacer nuevos test TDD. De momento solo inglés.
        LanguageEnumBO language = LanguageEnumBO.EN;
        recipesRepository.save(recipesMapper.toRecipePO(recipeNew, new DateTime(), language));
    }

    @Override
    public Optional<RecipeBO> getRecipe(@NotNull String recipeId) {
        List<RecipePO> recipePOList = recipesRepository.findByObjectId(recipeId);
        return recipePOList == null || recipePOList.isEmpty() ? Optional.empty() : Optional.of(recipesMapper.toRecipeBO(recipePOList.get(0)));
    }

    private void throwsExceptionIfUserNotExists(String userId) {
        // TODO DMS: Falta llamar a servicio externo que compruebe si el usuario existe y es válido. Posiblemente se compruebe con permisos con token Oauth.
    }
}
