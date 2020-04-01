package com.ojodev.cookinghero.recipes.domain.model;

import com.ojodev.cookinghero.recipes.data.MediaExamples;
import com.ojodev.cookinghero.recipes.utils.AbstractJavaBeanTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MediaRefBOTest extends AbstractJavaBeanTest<MediaRefBO> {

    @Override
    protected MediaRefBO getBeanInstance() {
        return new MediaRefBO();
    }


    @Test
    public void constructorAllFields() {
        MediaRefBO mediaRefBO = new MediaRefBO(MediaExamples.MEDIA_01_TYPE_BO, MediaExamples.MEDIA_01_ID);

        assertNotNull(mediaRefBO);
        assertEquals(MediaExamples.MEDIA_01_ID, mediaRefBO.getId());
        assertEquals(MediaExamples.MEDIA_01_TYPE_BO, mediaRefBO.getMediaType());
    }
}

