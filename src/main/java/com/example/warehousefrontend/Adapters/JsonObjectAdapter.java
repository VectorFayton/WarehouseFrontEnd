package com.example.warehousefrontend.Adapters;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonObjectAdapter implements JsonAdapter{
    private final ObjectMapper objectMapper;
    public JsonObjectAdapter(){
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public String objectToJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception exception) {
            throw new RuntimeException("Error converting object to Json");
        }
    }

    @Override
    public <T> T jsonToObject(JsonNode json, Class<T> type) {
        try {
            return objectMapper.readValue(json.traverse(), type);
        } catch (Exception exception) {
            throw new RuntimeException("Error converting Json to object");
        }
    }
}
