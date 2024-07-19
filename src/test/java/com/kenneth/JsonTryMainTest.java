package com.kenneth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.kenneth.jsonparsing.Json;
import junit.framework.TestCase;

public class JsonTryMainTest extends TestCase {

    private String jsonSource;

    public void setUp() throws Exception {
        jsonSource = null;
    }

    public void testPhaseInt() {
        jsonSource = """
                {
                    "age": 60
                }""";
        try {
            JsonNode node = Json.parse(jsonSource);
            int age = node.get("age").asInt();

            assertEquals(60, age);
        } catch (JsonProcessingException e) {
            fail("testPhaseInt JsonProcessingException Thrown!!!");
        }
    }

    public void testPhaseString() {
        jsonSource = """
                {
                    "lastName": "WU"
                }""";
        try {
            JsonNode node = Json.parse(jsonSource);
            String lastName = node.get("lastName").asText();

            assertEquals("WU", lastName);
        } catch (JsonProcessingException e) {
            fail("testPhaseString JsonProcessingException Thrown!!!");
        }
    }
}