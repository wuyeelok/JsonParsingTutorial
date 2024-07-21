package com.kenneth.jsonparsing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.kenneth.jsonparsing.pojo.AuthorPojo;
import com.kenneth.jsonparsing.pojo.EventPojo;
import com.kenneth.jsonparsing.pojo.SimpleTestCaseJsonPOJO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class JsonTest {

    private String simpleTestCase;
    private SimpleTestCaseJsonPOJO stpojo;

    private String complexTestCase;

    @BeforeEach
    void setUp() {
        simpleTestCase = null;
        stpojo = null;
        complexTestCase = null;
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

        String jsonStr = Json.convertObjToJsonString(stpojo, false);
        String expectedJsonStr = "{\"title\":\"Orange\"}";

        assertEquals(expectedJsonStr, jsonStr);
    }

    @Test
    void convertObjToStringNullCase() throws JsonProcessingException {
        stpojo = new SimpleTestCaseJsonPOJO();

        String jsonStr = Json.convertObjToJsonString(stpojo, false);
        String expectedJsonStr = "{\"title\":null}";

        assertEquals(expectedJsonStr, jsonStr);
    }

    @Test
    void convertObjToPrettyString() throws JsonProcessingException {
        stpojo = new SimpleTestCaseJsonPOJO();
        stpojo.setTitle("Apple");

        String jsonStr = Json.convertObjToJsonString(stpojo, true);
        String expectedJsonStr = """
                {
                  "title" : "Apple"
                }""";
        expectedJsonStr = expectedJsonStr.replaceAll("\n", "\r\n");

        assertEquals(expectedJsonStr, jsonStr);
    }

//    @Test
//    void convertComplexStringToObjDate() throws JsonProcessingException, ParseException {
//        complexTestCase = """
//                {
//                  "date": "2024-02-01",
//                  "name": "Reborn"
//                }""";
//
//        EventPojo pojo = Json.convertStringToObj(complexTestCase, EventPojo.class);
//
//        assertEquals("Reborn", pojo.getName());
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date expectedDate = sdf.parse("2024-02-01");
//
//        assertEquals(expectedDate, pojo.getDate());
//    }

    @Test
    void convertComplexStringToObjLocalDate() throws JsonProcessingException, ParseException {
        complexTestCase = """
                {
                  "dateNew": "2024-02-01",
                  "name": "Reborn"
                }""";

        EventPojo pojo = Json.convertStringToObj(complexTestCase, EventPojo.class);
        assertEquals("Reborn", pojo.getName());
        assertEquals(LocalDate.of(2024, 2, 1), pojo.getDateNew());
    }

    @Test
    void convertComplexStringToObjMoreThan1Depth() throws JsonProcessingException, ParseException {
        complexTestCase = """
                {
                  "authorName": "John",
                  "books": [
                    {
                        "title": "title1",
                        "inPrint": true,
                        "publishDate": "2024-05-19"
                    },
                    {
                        "title": "title1",
                        "inPrint": false,
                        "publishDate": "2020-12-25"
                    }
                  ]
                }""";

        AuthorPojo pojo = Json.convertStringToObj(complexTestCase, AuthorPojo.class);
        assertEquals("John", pojo.getAuthorName());
        assertTrue(pojo.getBooks().get(0).isInPrint());
        assertEquals(LocalDate.of(2020, 12, 25), pojo.getBooks().get(1).getPublishDate());
    }
}