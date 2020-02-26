package com.ojodev.cookinghero.recipes.api.model;

import com.ojodev.cookinghero.recipes.utils.AbstractJavaBeanTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiErrorTest extends AbstractJavaBeanTest<ApiError> {

    @Override
    protected ApiError getBeanInstance() {
        return new ApiError();
    }

    @Test
    public void constructorAllFields() {
        String errorCode = "BAD_REQUEST";
        String errorDesc = "Bad request.";

        ApiError apiError = new ApiError(errorCode, errorDesc);

        assertNotNull(apiError);
        assertEquals(errorCode, apiError.getCode());
        assertEquals(errorDesc, apiError.getDescription());
    }

}
