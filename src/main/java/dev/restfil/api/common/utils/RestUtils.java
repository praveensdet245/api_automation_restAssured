package dev.restfil.api.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Map;

public class RestUtils {
    private static final Logger log = LoggerFactory.getLogger(RestUtils.class);

    @Getter
    @Setter
    private String uri;

    @Getter
    @Setter
    private String baseUrl;

    @Getter
    @Setter
    public String endPoint;

    @Getter
    @Setter
    private Response response;

    @Getter
    @Setter
    private String serviceName;

    /**
     * this method has to get called before execute any api
     * @param service
     */


    public void initiateService(Services service,String endPointKey) {
        try {
            this.setServiceName(ServiceBaseUri.getBaseEndpoint(service));
            System.out.println(GlobalMap.get("ENV"));
            String baseService = ServiceBaseUri.getBaseEndpoint(service);
            this.setEndPoint(endPointKey);
            this.setBaseUrl(baseService);
            log.info("BASEURL -> " + this.getBaseUrl());
            log.info("ENDPOINT -> " + this.getEndPoint());
        }catch (Exception e) {
            log.error("Unable to initiate service --> "+service,e);
        }
    }

    /**
     * to execute different simple api calls
     * @param methodType
     * @param headers
     * @param requestBody
     * @return
     */
    public Response executeAPI(RestMethods methodType, Map<String,Object> headers,Object requestBody) {
        try {
            RestAssured.baseURI = this.getBaseUrl();
            String wholeUrl = this.getBaseUrl()+this.getEndPoint();
            switch (methodType) {
                case GET:
                    response = RestAssured.given().contentType(ContentType.JSON).headers(headers).when().get(this.getEndPoint());
                    log.info("[GET] : "+wholeUrl+" status code : "+response.getStatusCode());
                    this.setResponse(response);
                    break;
                case POST:
                    ObjectMapper objectMapper = CustomObjectMapper.getObjectMapper();
                    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                    String request = objectMapper.writeValueAsString(requestBody);
                    log.info("Request body : "+request);
                    if(headers != null) response = RestAssured.given().contentType(ContentType.JSON).headers(headers).body(request).when().post(this.getEndPoint());
                    else response = RestAssured.given().contentType(ContentType.JSON).body(request).when().post(this.getEndPoint());
                    log.info("[POST] : "+wholeUrl+" status code : "+response.getStatusCode());
                    if(response.getStatusCode() != 200) log.info("response body --> ",response.prettyPrint());
                    this.setResponse(response);
                    break;
                case DELETE:
                    response = RestAssured.given().contentType(ContentType.JSON).headers(headers).when().delete(this.getEndPoint());
                    log.info("[DELETE] : "+wholeUrl+" status code : "+response.getStatusCode());
                    this.setResponse(response);
                    break;

                default:
                    log.error("invalid api method type...");
                    break;
            }
        }catch (Exception e) {
            log.error("unable to execute api call with method type :"+methodType,e);
        }
        this.setResponse(response);
        return this.getResponse();
    }

    /**
     * this method is used to execute post api call with file as request body
     * @param headers
     * @param filePath
     * @return response if execute successfully
     */
    public Response executePostWithFile(Map<String,Object> headers, String filePath) {
        try {
            RestAssured.baseURI = this.getBaseUrl();
            String wholeUrl = this.getBaseUrl()+this.getEndPoint();
            log.info("ENDPOINT -> " + this.getEndPoint());
            response = RestAssured.given().headers(headers).body(new File(filePath)).when().post(this.getEndPoint());
            log.info(wholeUrl+" status code : "+response.getStatusCode());
        }catch (Exception e) {
            log.error("unable to execute post call-->",e);
        }
        this.setResponse(response);
        return this.getResponse();
    }

    /**
     * this method is used to execute post api call with file with variables and user can pass replaceable values map
     * @param headers
     * @param filePath
     * @return response if execute successfully
     */
    public Response executePostWithFileWithReplaceableValues(Map<String,? extends Object> headers, Map<String,? extends Object> replaceableValues,String filePath) {
        try {
            RestAssured.baseURI = this.getBaseUrl();
            String wholeUrl = this.getBaseUrl()+this.getEndPoint();
            log.info("ENDPOINT -> " + this.getEndPoint());
            String jsonDataFromFile = JsonParser.parseReader(new FileReader(Constants.RESOURCES_FOLDER_PATH+filePath)).toString();
            for(Map.Entry<String,? extends Object> entry:replaceableValues.entrySet()) {
                jsonDataFromFile = jsonDataFromFile.replace(entry.getKey(),String.valueOf(entry.getValue()));
            }
            ObjectMapper objectMapper = CustomObjectMapper.getObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            String request = objectMapper.writeValueAsString(jsonDataFromFile);
            response = RestAssured.given().headers(headers).body(request).when().post(this.getEndPoint());
            log.info(wholeUrl+" status code : "+response.getStatusCode());
        }catch (Exception e) {
            log.error("unable to execute post call-->",e);
        }
        this.setResponse(response);
        return this.getResponse();
    }


    /**
     * this is to execute delete call and end point needs to be set before this call
     * @param headers
     * @return response
     */
    public Response executeDelete(Map<String,Object> headers) {
        try {
            RestAssured.baseURI = this.getBaseUrl();
            String wholeUrl = this.getBaseUrl()+this.getEndPoint();
            log.info("ENDPOINT -> " + this.getEndPoint());
            response = RestAssured.given().headers(headers).when().delete(this.getEndPoint());
            log.info(wholeUrl+" status code : "+response.getStatusCode());
        }catch (Exception e) {
            log.error("unable to execute delete call-->",e);
        }
        this.setResponse(response);
        return this.getResponse();
    }

    /**
     * this method is used to execute post api call with map as request body
     * @param headers
     * @param requestBody
     * @return response if execute successfully
     */
    public Response executePostWithMapAsRequestBody(Map<String,Object> headers, Map<String,Object> requestBody) {
        try {
            RestAssured.baseURI = this.getBaseUrl();
            String wholeUrl = this.getBaseUrl()+this.getEndPoint();
            log.info("ENDPOINT -> " + this.getEndPoint());
            ObjectMapper objectMapper = CustomObjectMapper.getObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            String request = objectMapper.writeValueAsString(requestBody);
            response = RestAssured.given().headers(headers).body(request).when().post(this.getEndPoint());
            log.info(wholeUrl+" status code : "+response.getStatusCode());
        }catch (Exception e) {
            log.error("unable to execute post call-->",e);
        }
        this.setResponse(response);
        return this.getResponse();
    }

    /**
     * this function is to validate response code from given response
     * @param expectedResponseCode ex: 200, 401
     * @return true if expected matches
     */
    public boolean isResponseCodeExpected(int expectedResponseCode) {
        log.info("actual response code is {"+this.getResponse().getStatusCode()+"} and expected response code is {"+expectedResponseCode+"}");
        return (this.getResponse().getStatusCode() == expectedResponseCode);
    }


    /**
     * this method converts response to deserializable class
     * @param deserializableClass class which needs to be deserializable
     * @return class object
     */
    public <T> T assignResponseToClass(Response response, Class<T> deserializableClass) {
        try {
            return response.getBody().as(deserializableClass);
        } catch (Exception e) {
            log.error("unable to assign response to class ::: "+deserializableClass.getClass().getName(),e.getStackTrace().toString());
            return null;
        }
    }

    public <T> List<T> getArrayFromResponse(String keyPathInResponse) {
        List<T> list = this.getResponse().jsonPath().getList(keyPathInResponse);
        log.info("extracted list size from response is :"+list.size());
        return  list;
    }

    public <T> T getKeyValueFromResponse(String keyPath,Class<T> returnType) {
        T value = this.getResponse().jsonPath().getObject(keyPath,returnType);
        log.info("extracted value from response for key :"+keyPath+" is { "+value+" }");
        return value;
    }
}
