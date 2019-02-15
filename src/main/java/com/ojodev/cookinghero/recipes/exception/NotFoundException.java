package com.ojodev.cookinghero.recipes.exception;

import org.springframework.beans.factory.annotation.Autowired;

import com.ojodev.cookinghero.recipes.bean.ApiException;
import com.ojodev.cookinghero.recipes.config.Messages;


public class NotFoundException extends ApiException {
   
	@Autowired
	private Messages messages;
	
	 public NotFoundException () {
		 this.code = messages.get("error.notfound.code");
		 this.description = messages.get("error.notfound.desc");
	    }
	    
    public NotFoundException (String code, String description) {
        super(code, description);
    }
    
}
