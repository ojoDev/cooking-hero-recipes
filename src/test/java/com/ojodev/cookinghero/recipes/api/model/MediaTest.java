package com.ojodev.cookinghero.recipes.api.model;

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
public class MediaTest extends AbstractJavaBeanTest<MediaRef> {

    @Override
    protected MediaRef getBeanInstance() {
        return new MediaRef();
    }

    @Test
    public void constructorAllFields() {

        MediaRef media = new MediaRef(MediaExamples.MEDIA_01_TYPE, MediaExamples.MEDIA_01_ID);

        assertNotNull(media);
        assertEquals(MediaExamples.MEDIA_01_ID, media.getId());
        assertEquals(MediaExamples.MEDIA_01_TYPE, media.getMediaType());

    }
}
