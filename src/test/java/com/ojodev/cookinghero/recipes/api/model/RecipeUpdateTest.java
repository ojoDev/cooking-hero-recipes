package com.ojodev.cookinghero.recipes.api.model;

import com.ojodev.cookinghero.recipes.utils.AbstractJavaBeanTest;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeUpdateTest extends AbstractJavaBeanTest<RecipeUpdate> {

    @Override
    protected RecipeUpdate getBeanInstance() {
        return new RecipeUpdate();
    }

}
