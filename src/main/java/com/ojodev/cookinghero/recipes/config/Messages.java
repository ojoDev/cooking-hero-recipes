package com.ojodev.cookinghero.recipes.config;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

@Component
public class Messages {

    private static final Locale LOCALE = Locale.ENGLISH;

    @Autowired
    private MessageSource messageSource;

    private MessageSourceAccessor accessor;

    @PostConstruct
    private void init() {
        accessor = new MessageSourceAccessor(messageSource, LOCALE);
    }

    public String get(String code, Object... arguments) {
        return accessor.getMessage(code, arguments, LOCALE);
    }

}
