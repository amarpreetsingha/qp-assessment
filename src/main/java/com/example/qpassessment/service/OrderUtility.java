package com.example.qpassessment.service;

import com.example.qpassessment.model.Item;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Component;

import java.util.List;

public class OrderUtility {

    static ObjectMapper objectMapper = new ObjectMapper();
    static TypeReference<List<Item>> listType = new TypeReference<>() {};
    public static String convertToJson(Object meta) {
        try {
            return objectMapper.writeValueAsString(meta);
        } catch ( JsonProcessingException ex) {
            return null;
        }
    }

    public static List<Item> convertToObject(String json) {
        json = json.substring(1, json.length()-1);
        json = StringEscapeUtils.unescapeJson(json);
        try {
            return objectMapper.readValue(json, listType);
        } catch (JsonProcessingException ex) {
            return null;
        }
    }
}
