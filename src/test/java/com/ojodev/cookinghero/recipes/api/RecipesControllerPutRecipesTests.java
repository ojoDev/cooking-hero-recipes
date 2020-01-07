package com.ojodev.cookinghero.recipes.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ojodev.cookinghero.recipes.infrastructure.po.RecipePO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.mongodb.client.result.UpdateResult;
import com.ojodev.cookinghero.recipes.data.RecipesExamples;
import com.ojodev.cookinghero.recipes.utils.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RecipesControllerPutRecipesTests {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private MongoTemplate mongoTemplate;
	
	@Test
	public void putRecipe() throws Exception {
		initRecipeUpdated();
		this.mvc.perform(put("/recipes/{recipe-id}", RecipesExamples.RECIPE_ID_01).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(TestUtils.asJsonString(RecipesExamples.RECIPE))).andExpect(status().isCreated());
	}
		
	private void initRecipeUpdated() {
		UpdateResult result = mock(UpdateResult.class);
		when(this.mongoTemplate.upsert(any(), any(),eq(RecipePO.class))).thenReturn(result);
	}

	private void initOutOfMemoryException() {
		 Mockito.doThrow(new OutOfMemoryError()).when(mongoTemplate).insert(RecipePO.class);
	}
}
