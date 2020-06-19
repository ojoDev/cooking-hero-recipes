package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.MediaRef;
import com.ojodev.cookinghero.recipes.data.MediaExamples;
import com.ojodev.cookinghero.recipes.domain.model.MediaRefBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.MediaRefPO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MediaRefMapperTest {

    @Autowired
    private MediaRefMapper mediaRefMapper;

    @Test
    public void convertMediaRefPOToMediaRefBO() {

        MediaRefPO mediaRefPO = new MediaRefPO(MediaExamples.MEDIA_01_ID, MediaExamples.MEDIA_01_TYPE_BO.toString());

        MediaRefBO mediaRefBO = mediaRefMapper.toMediaRefBO(mediaRefPO);

        assertNotNull(mediaRefBO);
        assertEquals(MediaExamples.MEDIA_01_ID, mediaRefBO.getId());
        assertEquals(MediaExamples.MEDIA_01_TYPE_BO, mediaRefBO.getMediaType());
    }

    @Test
    public void convertMediaRefToMediaRefBO() {

        MediaRef mediaRef = new MediaRef(MediaExamples.MEDIA_01_ID, MediaExamples.MEDIA_01_TYPE);

        MediaRefBO mediaRefBO = mediaRefMapper.toMediaRefBO(mediaRef);

        assertNotNull(mediaRefBO);
        assertEquals(MediaExamples.MEDIA_01_ID, mediaRefBO.getId());
        assertEquals(MediaExamples.MEDIA_01_TYPE_BO, mediaRefBO.getMediaType());
    }
}
