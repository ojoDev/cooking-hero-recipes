package com.ojodev.cookinghero.recipes.infrastructure.repository;

import com.ojodev.cookinghero.recipes.RecipesApplication;
import com.ojodev.cookinghero.recipes.infrastructure.po.CuisineTypePO;
import com.ojodev.cookinghero.recipes.infrastructure.po.RecipeCuisineTypeRelationship;
import com.ojodev.cookinghero.recipes.infrastructure.po.RecipePO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class })
@SpringBootTest(classes = RecipesApplication.class)
@Slf4j
public class RecipeCuisineTypeRelationshipRepositoryTest {

    @Autowired
    private RecipeCuisineTypeRelationshipRepository recipeCuisineTypeRelationshipRepository;

    @Autowired
    private RecipesRepository recipesRepository;

    @Autowired
    private CuisineTypesRepository cuisineTypesRepository;

    @Test
    @Ignore
    public void createRelationship() {

        List<RecipePO> recipes = recipesRepository.findByObjectId("0123456789ab0123456789abxyz");

        List<CuisineTypePO> cuisineTypes = cuisineTypesRepository.findByName("veggie3", "en");
        if (recipes != null && !recipes.isEmpty() && cuisineTypes !=null && !cuisineTypes.isEmpty()) {
            RecipeCuisineTypeRelationship recipeCuisineTypeRelationship= new RecipeCuisineTypeRelationship(recipes.get(0), cuisineTypes.get(0));
            recipeCuisineTypeRelationshipRepository.save(recipeCuisineTypeRelationship);

            List<RecipePO> recipesWithRelationship = recipesRepository.findByObjectId("0123456789ab0123456789abxyz");
            assertNotNull(recipesWithRelationship.get(0).getCuisineTypes());

        }}

        @Test
        @Ignore
        public void createRelationshipBasic() {

            List<RecipePO> recipes = recipesRepository.findByObjectId("0123456789ab0123456789ab");

            List<CuisineTypePO> cuisineTypes = cuisineTypesRepository.findByName("veggie2", "en");
            if (recipes != null && !recipes.isEmpty() && cuisineTypes !=null && !cuisineTypes.isEmpty()) {
                RecipePO recipe = recipes.get(0);

                List<CuisineTypePO> recipeCuisineTypes = recipe.getCuisineTypes()== null ? new ArrayList<>() : recipe.getCuisineTypes();
                recipeCuisineTypes.add(cuisineTypes.get(0));
                recipe.setCuisineTypes(recipeCuisineTypes);
                recipesRepository.save(recipe, 1);

                List<RecipePO> recipesWithRelationship = recipesRepository.findByObjectId("0123456789ab0123456789ab");
                assertNotNull(recipesWithRelationship.get(0).getCuisineTypes());

            }


    }

}
