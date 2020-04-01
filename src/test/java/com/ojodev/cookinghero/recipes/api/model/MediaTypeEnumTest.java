package com.ojodev.cookinghero.recipes.api.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MediaTypeEnumTest {

    private static final String IMAGE = "IMAGE";
    private static final String VIDEO = "VIDEO";
    private static final String INVALID = "TEAPOT";

    @Test
    public void initFromValue() {
        MediaTypeEnum mediaTypeEnumImage = MediaTypeEnum.fromValue(IMAGE);
        MediaTypeEnum mediaTypeEnumVideo = MediaTypeEnum.fromValue(VIDEO);

        assertEquals(MediaTypeEnum.IMAGE, mediaTypeEnumImage);
        assertEquals(MediaTypeEnum.VIDEO, mediaTypeEnumVideo);
    }

    @Test
    public void initFromInvalidValue() {
        MediaTypeEnum mediaTypeEnum = MediaTypeEnum.fromValue(INVALID);

        assertNull(mediaTypeEnum);
    }

}
