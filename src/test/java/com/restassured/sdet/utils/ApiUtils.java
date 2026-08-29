package com.restassured.sdet.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

/**
 * API Utilities class - Common methods for API testing
 */
public class ApiUtils {

    /**
     * Initialize RestAssured with base URI
     */
    public static void setupRestAssured(String baseUri) {
        RestAssured.baseURI = baseUri;
    }

    /**
     * Get API response with headers
     */
    public static Response getResponse(String endpoint) {
        return given()
                .when()
                .get(endpoint);
    }

    /**
     * Post with JSON body
     */
    public static Response postRequest(String endpoint, String body) {
        return given()
                .contentType("application/json")
                .body(body)
                .when()
                .post(endpoint);
    }

    /**
     * Put request with JSON body
     */
    public static Response putRequest(String endpoint, String body) {
        return given()
                .contentType("application/json")
                .body(body)
                .when()
                .put(endpoint);
    }

    /**
     * Delete request
     */
    public static Response deleteRequest(String endpoint) {
        return given()
                .when()
                .delete(endpoint);
    }

    /**
     * Get request with query parameters
     */
    public static Response getWithQueryParams(String endpoint, String paramKey, String paramValue) {
        return given()
                .queryParam(paramKey, paramValue)
                .when()
                .get(endpoint);
    }

    /**
     * Get request with path parameters
     */
    public static Response getWithPathParams(String endpoint, String paramKey, String paramValue) {
        return given()
                .pathParam(paramKey, paramValue)
                .when()
                .get(endpoint);
    }

    /**
     * Get request with authentication header
     */
    public static Response getWithAuth(String endpoint, String token) {
        return given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get(endpoint);
    }

    /**
     * Print response details
     */
    public static void printResponse(Response response) {
        System.out.println("\n=== Response Details ===");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Status Line: " + response.getStatusLine());
        System.out.println("Content Type: " + response.getContentType());
        System.out.println("Body: " + response.getBody().asString());
    }

    /**
     * Verify status code
     */
    public static boolean verifyStatusCode(Response response, int expectedCode) {
        return response.getStatusCode() == expectedCode;
    }

    /**
     * Extract JSON path value from response
     */
    public static Object extractJsonPath(Response response, String jsonPath) {
        return response.jsonPath().get(jsonPath);
    }

    /**
     * Extract XML path value from response
     */
    public static Object extractXmlPath(Response response, String xmlPath) {
        return response.xmlPath().get(xmlPath);
    }
}
