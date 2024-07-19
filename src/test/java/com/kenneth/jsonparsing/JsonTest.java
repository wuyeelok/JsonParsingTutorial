package com.kenneth.jsonparsing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonTest {

    private String simpleTestCase;

    @BeforeEach
    void setUp() {
        simpleTestCase = null;
    }

    @Test
    void parse() {
        simpleTestCase = "{\"title\": \"Hello\"}";
        try {
            JsonNode node = Json.parse(simpleTestCase);
            String title = node.get("title").asText();

            assertEquals("Hello", title);
        } catch (JsonProcessingException e) {
            fail("Fail parse function!!!");
        }
    }

    @Test
    void testPhaseInt() {
        simpleTestCase = """
                {
                    "age": 60
                }""";
        try {
            JsonNode node = Json.parse(simpleTestCase);
            int age = node.get("age").asInt();

            assertEquals(60, age);
        } catch (JsonProcessingException e) {
            fail("testPhaseInt JsonProcessingException Thrown!!!");
        }
    }

    @Test
    void testPhaseString() {
        simpleTestCase = """
                {
                    "lastName": "WU"
                }""";
        try {
            JsonNode node = Json.parse(simpleTestCase);
            String lastName = node.get("lastName").asText();

            assertEquals("WU", lastName);
        } catch (JsonProcessingException e) {
            fail("testPhaseString JsonProcessingException Thrown!!!");
        }
    }
}