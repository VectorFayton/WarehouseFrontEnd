package com.example.warehousefrontend;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Json_ObjectAdapter implements JsonAdapter{
    private final ObjectMapper objectMapper;
    public Json_ObjectAdapter(){
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
