package com.ojodev.cookinghero.recipes.infrastructure.po;

import com.ojodev.cookinghero.recipes.data.MediaExamples;
import com.ojodev.cookinghero.recipes.utils.AbstractJavaBeanTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.meanbean.test.BeanTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MediaPOTest {

    @Test
    public void constructorAllFields() {

        MediaPO mediaPO = new MediaPO(MediaExamples.MEDIA_01_ID, MediaExamples.MEDIA_01_TYPE_BO.toString());

        assertNotNull(mediaPO);
        assertEquals(MediaExamples.MEDIA_01_ID, mediaPO.getObjectId());
        assertEquals(MediaExamples.MEDIA_01_TYPE_BO.toString(), mediaPO.getType());

    }

    @Test
    public void getterAndSetterCorrectness() {
        final BeanTester beanTester = new BeanTester();
        beanTester.testBean(MediaPO.class);
    }

}
