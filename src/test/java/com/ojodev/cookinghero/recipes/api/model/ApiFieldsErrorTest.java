package com.ojodev.cookinghero.recipes.api.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.meanbean.test.BeanTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiFieldsErrorTest{

    @Test
    public void constructorAllFields() {
        String errorCode = "BAR_REQUEST";
        String errorDesc = "Bad request.";

        String errorCodeField01 = "INVALID_PARAMS_01";
        String fieldField01 = "name";
        String errorDescField01 = "Name invalid.";
        String errorCodeField02 = "INVALID_PARAMS_02";
        String fieldField02 = "surname";
        String errorDescField02 = "Surname invalid.";
        List<ApiFieldError> fieldErrorList = Arrays.asList(new ApiFieldError(errorCodeField01, fieldField01, errorDescField01), new ApiFieldError(errorCodeField02, fieldField02, errorDescField02));

        ApiFieldsError apiFieldsError = new ApiFieldsError(errorCode, errorDesc, fieldErrorList);

        assertNotNull(apiFieldsError);
        assertEquals(errorCode, apiFieldsError.getCode());
        assertEquals(errorDesc, apiFieldsError.getDescription());
        assertNotNull(apiFieldsError.getFields());
        assertEquals(2, apiFieldsError.getFields().size());
        assertEquals(errorCodeField01, apiFieldsError.getFields().get(0).getCode());
        assertEquals(fieldField01, apiFieldsError.getFields().get(0).getField());
        assertEquals(errorDescField01, apiFieldsError.getFields().get(0).getDescription());
        assertEquals(errorCodeField02, apiFieldsError.getFields().get(1).getCode());
        assertEquals(fieldField02, apiFieldsError.getFields().get(1).getField());
        assertEquals(errorDescField02, apiFieldsError.getFields().get(1).getDescription());
    }

    @Test
    public void getterAndSetterCorrectness() {
        final BeanTester beanTester = new BeanTester();
        beanTester.testBean(ApiFieldsError.class);
    }

}
