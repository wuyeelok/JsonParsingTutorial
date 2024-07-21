package com.kenneth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kenneth.jsonparsing.Json;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

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

        Date currentDate = new Date();
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        String currentDateStr = "";
        try {
            currentDateStr = om.writeValueAsString(currentDate);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("It’s important to note that Jackson will serialize the Date to a timestamp format by default (number of milliseconds since January 1st, 1970, UTC).\n" +
                "Jackson parse java util date will become: " + currentDateStr);

        System.out.println("\n");

        LocalDate ld = LocalDate.now();
        String currentLocalDateStr = "";
        try {
            currentLocalDateStr = om.writeValueAsString(ld);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Jackson parse java time localDate will become: " + currentLocalDateStr);

    }
}
