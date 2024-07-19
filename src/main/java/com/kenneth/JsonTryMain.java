package com.kenneth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.kenneth.jsonparsing.Json;

public class JsonTryMain {
    public static void main(String[] arg) {
        String jsonSource = """
                {
                    "name": "Kenneth"
                }""";

        try {
            JsonNode node = Json.parse(jsonSource);

            System.out.println(node.get("name").asText());

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
