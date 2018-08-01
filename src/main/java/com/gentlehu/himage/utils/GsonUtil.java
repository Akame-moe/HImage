package com.gentlehu.himage.utils;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by gentle-hu on 2018/7/29 3:59.
 * Email:me@gentlehu.com
 */
public class GsonUtil {

    private static Gson gson = null;
    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String toJson(Object obj){
        if(gson == null){
            gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,new LocalDateTimeAdapter()).disableHtmlEscaping().create();
        }
        return gson.toJson(obj);
    }
    private static class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime>,JsonDeserializer {

        @Override
        public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
            if(jsonElement == null) return null;
            String str = jsonElement.getAsString();
            return LocalDateTime.parse(str,DateTimeFormatter.ofPattern(DATE_PATTERN));
        }

        @Override
        public JsonElement serialize(LocalDateTime localDateTime, Type type, JsonSerializationContext context) {
            String str = localDateTime.format(DateTimeFormatter.ofPattern(DATE_PATTERN));
            return new JsonPrimitive(str);
        }
    }
}
