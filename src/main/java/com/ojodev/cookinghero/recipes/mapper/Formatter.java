package com.ojodev.cookinghero.recipes.mapper;

import java.math.BigDecimal;

import org.bson.types.Decimal128;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ojodev.cookinghero.recipes.api.model.PhotoRef;
import com.ojodev.cookinghero.recipes.config.ServerInfo;
import com.ojodev.cookinghero.recipes.domain.constants.RecipeConstants;

@Component
public class Formatter {

    private static final String RESOURCE_SEPARATOR = "/";

    @Autowired
    ServerInfo serverInfo;

    public Decimal128 map(BigDecimal value) {
        return value == null ? null : new Decimal128(value);
    }

	public BigDecimal mapToBigDecimal(Decimal128 value) {
		return value == null ? null : value.bigDecimalValue();
	}

    public Decimal128 map(Double value) {
        return value == null ? null : new Decimal128(value.longValue());
    }

	public Double mapToDouble(Decimal128 value) {
		return value == null ? null :  value.bigDecimalValue().doubleValue();
	}

    public ObjectId toObjectId(String id) {
        return new ObjectId(id);
    }

    public String toStringId(ObjectId id) {
    	return id.toString();
    }

    public PhotoRef toPhotoRef(String photoId) {
        return photoId == null ? null : new PhotoRef(serverInfo.getServerResourceURI(new StringBuffer(RecipeConstants.MEDIA_PHOTO_URI).append(RESOURCE_SEPARATOR).append(photoId).toString()));
    }

    public String toPhotoId(PhotoRef photoRef) {
        return photoRef == null || photoRef.getHref() == null ? null : photoRef.getHref().substring(photoRef.getHref().lastIndexOf(RESOURCE_SEPARATOR));
    }

}
