package com.ojodev.cookinghero.recipes.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.regex.Pattern;


public class IdentifiableBO {

    private String id;

    private static final Pattern ACCEPTED_CHARS =  Pattern.compile("^[a-zA-Z0-9\\-\\s]+$");
    private static final String ID_WORD_SEPARATOR =  "-";

    public IdentifiableBO(String id) {
        this.id = normalizeId(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = normalizeId(id);
    }

    private String normalizeId(String id) {
        StringBuilder normalizedId = new StringBuilder();
        for (char s : id.toCharArray()) {
            if (ACCEPTED_CHARS.matcher(String.valueOf(s)).matches()) {
                normalizedId.append(s);
            }
        }
        return normalizedId.toString().trim().replace(" ", ID_WORD_SEPARATOR);
    }
}
