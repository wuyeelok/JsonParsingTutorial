package com.kenneth.jsonparsing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Json {

    private static ObjectMapper objectMapper = getDefaultObjectMapper();

    private static ObjectMapper getDefaultObjectMapper() {
        ObjectMapper defaultObjectMapper = new ObjectMapper();
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
        return objectMapper.readValue(jsonStr, clazz);
    }
}
