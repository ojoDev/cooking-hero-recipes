package com.ojodev.cookinghero.recipes.exception;

import com.ojodev.cookinghero.recipes.bean.ApiException;

public class NotFoundException extends ApiException {
   
    public NotFoundException (String code, String description) {
        super(code, description);
    }
    
}
