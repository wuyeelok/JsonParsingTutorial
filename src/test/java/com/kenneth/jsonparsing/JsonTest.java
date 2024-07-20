package com.kenneth.jsonparsing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.kenneth.jsonparsing.pojo.SimpleTestCaseJsonPOJO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonTest {

    private String simpleTestCase;
    private SimpleTestCaseJsonPOJO stpojo;

    @BeforeEach
    void setUp() {
        simpleTestCase = null;
        stpojo = null;
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

    @Test
    void fromJson() throws JsonProcessingException {
        simpleTestCase = "{\"title\": \"BYE!\"}";
        JsonNode node = Json.parse(simpleTestCase);
        SimpleTestCaseJsonPOJO pojo = Json.fromJson(node, SimpleTestCaseJsonPOJO.class);

        assertEquals("BYE!", pojo.getTitle());
    }

    @Test
    void convertStringToObj() throws JsonProcessingException {
        simpleTestCase = "{\"title\": \"Harry Potter\"}";

        SimpleTestCaseJsonPOJO pojo = Json.convertStringToObj(simpleTestCase, SimpleTestCaseJsonPOJO.class);

        assertEquals("Harry Potter", pojo.getTitle());
    }

    @Test
    void fromJsonNullProperties() throws JsonProcessingException {
        simpleTestCase = "{\"title\": null}";

        SimpleTestCaseJsonPOJO pojo = Json.convertStringToObj(simpleTestCase, SimpleTestCaseJsonPOJO.class);

        assertNull(pojo.getTitle());
    }

    @Test
    void fromJsonUnknownProperties() throws JsonProcessingException {
        simpleTestCase = "{\"title\": \"BYE!\", \"author\": \"Tom\"}";
        JsonNode node = Json.parse(simpleTestCase);
        SimpleTestCaseJsonPOJO pojo = Json.fromJson(node, SimpleTestCaseJsonPOJO.class);

        assertEquals("BYE!", pojo.getTitle());
    }


    @Test
    void convertObjToString() throws JsonProcessingException {
        stpojo = new SimpleTestCaseJsonPOJO();
        stpojo.setTitle("Orange");

        String jsonStr = Json.convertObjToString(stpojo);
        String expectedJsonStr = "{\"title\":\"Orange\"}";

        assertEquals(expectedJsonStr, jsonStr);
    }

    @Test
    void convertObjToStringNullCase() throws JsonProcessingException {
        stpojo = new SimpleTestCaseJsonPOJO();

        String jsonStr = Json.convertObjToString(stpojo);
        String expectedJsonStr = "{\"title\":null}";

        assertEquals(expectedJsonStr, jsonStr);
    }

    @Test
    void convertObjToPrettyString() throws JsonProcessingException {
        stpojo = new SimpleTestCaseJsonPOJO();
        stpojo.setTitle("Apple");

        String jsonStr = Json.convertObjToPrettyString(stpojo);
        String expectedJsonStr = """
                {
                  "title" : "Apple"
                }""";
        expectedJsonStr = expectedJsonStr.replaceAll("\n", "\r\n");

        assertEquals(expectedJsonStr, jsonStr);
    }
}