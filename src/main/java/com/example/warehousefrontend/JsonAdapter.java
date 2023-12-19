package com.example.warehousefrontend;

import com.fasterxml.jackson.databind.JsonNode;

public interface JsonAdapter {
    String objectToJson(Object object);
    <T> T jsonToObject(JsonNode json, Class<T> type);
}
