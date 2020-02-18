package com.ojodev.cookinghero.recipes.infrastructure.repository;

import com.ojodev.cookinghero.recipes.RecipesApplication;
import com.ojodev.cookinghero.recipes.data.MeasuresExamples;
import com.ojodev.cookinghero.recipes.infrastructure.po.MeasurePO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class })
@SpringBootTest(classes = RecipesApplication.class)
@Slf4j
public class MeasuresRepositoryTest {

    @SpringBootApplication
    static class ExampleConfig {}

    @Autowired
    private MeasuresRepository measuresRepository;

    @Test
    @Disabled
    public void saveMeasure ()  {
        measuresRepository.save(MeasuresExamples.MEASURE_PO_01);
    }

    @Test
    @Disabled
    public void findAll()  {
       List<MeasurePO> measureList = measuresRepository.findAll();
        assertNotNull(measureList);
    }

    @Test
    @Disabled
    public void findById()  {
        List<MeasurePO> measureList = measuresRepository.findByObjectId(MeasuresExamples.MEASURE_01_ID);
        assertNotNull(measureList);
        assertEquals(1, measureList.size());
    }

    @Test
    @Disabled
    public void findByIdNotFound()  {
        List<MeasurePO> measureList = measuresRepository.findByObjectId("xxx");
        assertNotNull(measureList);
        assertEquals(0, measureList.size());
    }
}
