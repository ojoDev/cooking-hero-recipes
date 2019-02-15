package com.ojodev.cookinghero.recipes.converter.mapper;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.bson.types.Decimal128;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.ojodev.cookinghero.recipes.bean.PhotoRef;
import com.ojodev.cookinghero.recipes.config.ServerConfig;
import com.ojodev.cookinghero.recipes.constants.RecipeConstants;

@Component
public class Formatter {
	
	
	
	@Autowired
	private ServerConfig serverConfig;
	
	@Autowired
	private Environment environment;
	
	private static final String PORT_SEPARATOR = ":";
	private static final String RESOURCE_SEPARATOR = "/";

	public Decimal128 map(BigDecimal value) {
		return value == null ? null : new Decimal128(value);
	}

	public BigDecimal map(Decimal128 value) {
		return value == null ? null : value.bigDecimalValue();
	}
	
	public ObjectId toObjectId(String id) {
		return new ObjectId(id);
	}
	
	public String toStringId(ObjectId id) {
		return id.toString();
	}
	
	//TODO DMS: Ver como centralizar la localización del literal del recurso media/photo. Spring Cloud Config?
	public PhotoRef toPhotoRef(String photoId) {
	    String host = "";
		try {
			host = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO DMS: Log
		}
	    String port= environment.getProperty("server.port");
		return photoId == null ? null : new PhotoRef(new StringBuilder().append(host).append(PORT_SEPARATOR).append(port).append(RESOURCE_SEPARATOR).append(RecipeConstants.MEDIA_PHOTO_URI).append(RESOURCE_SEPARATOR).append(photoId).toString());
		//return photoId == null ? null : new PhotoRef(new StringBuilder().append(serverConfig.getServerAddress()).append(PORT_SEPARATOR).append(RecipeConstants.MEDIA_PHOTO_URI).append(RESOURCE_SEPARATOR+photoId).toString());
	}
	
	public String toPhotoId(PhotoRef photoRef) {
		return photoRef == null || photoRef.getHref() == null ? null : photoRef.getHref().substring(photoRef.getHref().lastIndexOf(RESOURCE_SEPARATOR));
	}

}
