package com.ojodev.cookinghero.recipes.api.model;

import com.ojodev.cookinghero.recipes.data.MeasuresExamples;
import com.ojodev.cookinghero.recipes.utils.AbstractJavaBeanTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MeasureRefTest extends AbstractJavaBeanTest<MeasureRef> {

    @Override
    protected MeasureRef getBeanInstance() {
        return new MeasureRef();
    }

    @Test
    public void constructorAllFields() {
        MeasureRef measureRef = new MeasureRef(MeasuresExamples.MEASURE_01_ID);

        assertNotNull(measureRef);
        assertEquals(MeasuresExamples.MEASURE_01_ID, measureRef.getId());
    }
}
