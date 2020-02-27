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
public class ApiFieldErrorTest extends AbstractJavaBeanTest<ApiFieldError> {

    @Override
    protected ApiFieldError getBeanInstance() {
        return new ApiFieldError();
    }

    @Test
    public void constructorAllFields() {
        String errorCode = "BAD_REQUEST";
        String field = "name";
        String errorDesc = "Name invalid.";

        ApiFieldError apiFiledError = new ApiFieldError(errorCode, field, errorDesc);

        assertNotNull(apiFiledError);
        assertEquals(errorCode, apiFiledError.getCode());
        assertEquals(field, apiFiledError.getField());
        assertEquals(errorDesc, apiFiledError.getDescription());
    }

}
