package com.ojodev.cookinghero.recipes.data;

import com.ojodev.cookinghero.recipes.bean.Media;
import com.ojodev.cookinghero.recipes.enume.MediaType;
import com.ojodev.cookinghero.recipes.po.MediaPO;

public class MediaExamples {

	public static final MediaType MEDIA_01_TYPE = MediaType.VIDEO;
	public static final String MEDIA_01_HREF = "media/videos/776603030340cc";
	public static final MediaPO MEDIA_01 = new MediaPO(MEDIA_01_TYPE, MEDIA_01_HREF);
	
	public static final MediaType MEDIA_02_TYPE = MediaType.PHOTO;
	public static final String MEDIA_02_HREF = "media/photos/5005933453485438ca";
	public static final MediaPO MEDIA_02 = new MediaPO(MEDIA_02_TYPE, MEDIA_02_HREF);
	
	public static final MediaType MEDIA_03_TYPE = MediaType.VIDEO;
	public static final String MEDIA_03_HREF = "media/videos/2343243253523ac";
	public static final MediaPO MEDIA_03 = new MediaPO(MEDIA_03_TYPE, MEDIA_03_HREF);
	
	public static final Media MEDIA_REQUEST_01 = new Media(Media.MediaTypeEnum.PHOTO, MEDIA_01_HREF);
	public static final Media MEDIA_REQUEST_02 = new Media(Media.MediaTypeEnum.VIDEO, MEDIA_02_HREF);
}
