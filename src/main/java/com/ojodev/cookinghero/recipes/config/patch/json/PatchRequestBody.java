package com.ojodev.cookinghero.recipes.config.patch.json;

import java.lang.annotation.*;

/**
 *
 * Created by socrates on 7/19/16.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PatchRequestBody {
}
