package com.ojodev.cookinghero.recipes.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.FileNameEnum;
import com.ojodev.cookinghero.recipes.domain.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

@Component
public class FileUtils<T> {

    @Autowired
    private Messages messages;

    public String fileAsJsonString(FileNameEnum fileName) throws ApiException {
        if (fileName == null) {
            return "";
        }
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName.toString()).getFile());
        try {
            return new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
           throw new ApiException(messages.get("error.file.notfound.code"), messages.get("error.file.notfound.desc" , fileName.toString()));
        }
    }

    public Map<String, Object> fileAsMap(FileNameEnum fileName) throws ApiException {
        String content = fileAsJsonString(fileName);
        return new Gson().fromJson(content, new TypeToken<HashMap<String, Object>>() {}.getType());
    }

    public T fileAsBean(Class<T> t, FileNameEnum fileName) throws ApiException{
        String content = fileAsJsonString(fileName);
        return new Gson().fromJson(content, t);
    }

}
