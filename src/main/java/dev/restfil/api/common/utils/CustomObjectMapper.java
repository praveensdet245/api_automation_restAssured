package dev.restfil.api.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.annotation.JsonInclude;

public class CustomObjectMapper {

    public static ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);  // Exclude null, empty and default values
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false); // Optional: handle empty beans
        return objectMapper;
    }
}
