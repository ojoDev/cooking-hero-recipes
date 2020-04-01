package com.ojodev.cookinghero.recipes.infrastructure.po;

import com.ojodev.cookinghero.recipes.data.RecipesExamples;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.meanbean.test.BeanTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipePOTest {

    @Test
    public void constructorTwoFields() {
        RecipePO recipePO = new RecipePO(RecipesExamples.RECIPE_01_ID, RecipesExamples.RECIPE_01_NAME);

        assertNotNull(recipePO);
        assertEquals(RecipesExamples.RECIPE_01_ID, recipePO.getObjectId());
        assertEquals(RecipesExamples.RECIPE_01_NAME, recipePO.getName());
    }

    @Test
    public void constructorThreeFields() {
        RecipePO recipePO = new RecipePO(RecipesExamples.RECIPE_01_ID, RecipesExamples.RECIPE_01_NAME, RecipesExamples.RECIPE_01_DESCRIPTION, RecipesExamples.RECIPE_01_LANGUAGE);

        assertNotNull(recipePO);
        assertEquals(RecipesExamples.RECIPE_01_ID, recipePO.getObjectId());
        assertEquals(RecipesExamples.RECIPE_01_NAME, recipePO.getName());
        assertEquals(RecipesExamples.RECIPE_01_DESCRIPTION, recipePO.getDescription());
        assertEquals(RecipesExamples.RECIPE_01_LANGUAGE.toString(), recipePO.getLanguage());
    }


    @Test
    public void getterAndSetterCorrectness() {
        final BeanTester beanTester = new BeanTester();
        beanTester.testBean(RecipePO.class);
    }

}
