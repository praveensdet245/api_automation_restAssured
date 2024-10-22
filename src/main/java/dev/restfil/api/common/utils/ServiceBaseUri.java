package dev.restfil.api.common.utils;

import java.util.HashMap;
import java.util.Map;

public class ServiceBaseUri {

    private static Map<Services, String> serviceEndpoints = new HashMap<>();
    private static String TEST_SERVICE = "https://api.restful-api.environment/";

    static {
        serviceEndpoints.put(Services.TEST_SERVICE, TEST_SERVICE);
    }

    public static String getBaseEndpoint(Services service) {
        String endpoint = serviceEndpoints.get(service);
        return endpoint != null ? endpoint.replace("environment", GlobalMap.get("ENV").toString()) : null;
    }
}
