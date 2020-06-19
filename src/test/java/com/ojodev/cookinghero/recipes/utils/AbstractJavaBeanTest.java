package com.ojodev.cookinghero.recipes.utils;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;
import org.meanbean.lang.Factory;
import org.meanbean.test.BeanTester;

import java.time.LocalDateTime;

public abstract class AbstractJavaBeanTest<T> {

    protected String[] propertiesToBeIgnored;


    @Test
    public void equalsAndHashCodeContract() {
        EqualsVerifier.forClass(getBeanInstance().getClass()).suppress(Warning.STRICT_INHERITANCE, Warning.NONFINAL_FIELDS).verify();
    }


    protected abstract T getBeanInstance();

    /**
     * Concrete Factory that creates a LocalDateTime.
     */
    class LocalDateTimeFactory implements Factory {

        @Override
        public LocalDateTime create() {
            return LocalDateTime.now();
        }

    }

}