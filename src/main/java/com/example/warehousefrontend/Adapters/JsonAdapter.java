package com.example.warehousefrontend.Adapters;

import com.fasterxml.jackson.databind.JsonNode;

public interface JsonAdapter {
    String objectToJson(Object object);
    <T> T jsonToObject(JsonNode json, Class<T> type);
}
