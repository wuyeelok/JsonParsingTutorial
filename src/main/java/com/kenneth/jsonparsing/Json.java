package com.kenneth.jsonparsing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Json {

    private static final ObjectMapper objectMapper = getDefaultObjectMapper();

    private static ObjectMapper getDefaultObjectMapper() {
        ObjectMapper defaultObjectMapper = new ObjectMapper();

        defaultObjectMapper.registerModule(new JavaTimeModule());

        defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return defaultObjectMapper;
    }

    public static JsonNode parse(String src) throws JsonProcessingException {
        return objectMapper.readTree(src);
    }

    public static <T> T fromJson(JsonNode node, Class<T> clazz) throws JsonProcessingException {
        return objectMapper.treeToValue(node, clazz);
    }

    public static <T> T convertStringToObj(String jsonStr, Class<T> clazz) throws JsonProcessingException {
        // objectMapper.setTimeZone(TimeZone.getTimeZone("Hongkong"));
        return objectMapper.readValue(jsonStr, clazz);
    }

    private static String convertObjToString(Object o) throws JsonProcessingException {
        return objectMapper.writeValueAsString(o);
    }

    private static String convertObjToPrettyString(Object o) throws JsonProcessingException {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
    }

    public static String convertObjToJsonString(Object o, boolean enablePretty) throws JsonProcessingException {
        if (enablePretty) {
            return convertObjToPrettyString(o);
        } else {
            return convertObjToString(o);
        }
    }
}
