package com.ojodev.cookinghero.recipes.infrastructure.po;

import com.ojodev.cookinghero.recipes.data.MediaExamples;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.meanbean.test.BeanTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MediaRefPOTest {

    @Test
    public void constructorAllFields() {

        MediaRefPO mediaRefPO = new MediaRefPO(MediaExamples.MEDIA_01_ID, MediaExamples.MEDIA_01_TYPE_BO.toString());

        assertNotNull(mediaRefPO);
        assertEquals(MediaExamples.MEDIA_01_ID, mediaRefPO.getObjectId());
        assertEquals(MediaExamples.MEDIA_01_TYPE_BO.toString(), mediaRefPO.getMediaType());

    }

    @Test
    public void getterAndSetterCorrectness() {
        final BeanTester beanTester = new BeanTester();
        beanTester.testBean(MediaRefPO.class);
    }

}
