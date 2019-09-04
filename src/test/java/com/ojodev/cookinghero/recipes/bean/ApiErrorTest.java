package com.ojodev.cookinghero.recipes.bean;


import net.codebox.javabeantester.JavaBeanTester;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.beans.IntrospectionException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiErrorTest {

    @Test
    public void testBean() {
        try {
            JavaBeanTester.test(ApiError.class);
        } catch (IntrospectionException e) {
            Assert.fail("Exception: " + e);
        }
    }
}
