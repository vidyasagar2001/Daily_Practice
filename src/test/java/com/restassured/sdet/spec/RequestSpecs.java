package com.restassured.sdet.spec;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import com.restassured.sdet.config.ApiConfig;

import static io.restassured.http.ContentType.JSON;

/**
 * Custom Request Specification Builder
 * Provides reusable request specifications for different scenarios
 */
public class RequestSpecs {

    /**
     * Get default request specification with base configuration
     * @return RequestSpecification
     */
    public static RequestSpecification getDefaultRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(ApiConfig.BASE_URI_DEV)
                .setContentType(JSON)
                .setAccept(JSON)
                .addHeader("User-Agent", "RestAssured-SDET")
                .build();
    }

    /**
     * Get request specification for GET requests
     * @return RequestSpecification
     */
    public static RequestSpecification getGetRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(ApiConfig.BASE_URI_DEV)
                .addHeader("User-Agent", "RestAssured-SDET")
                .build();
    }

    /**
     * Get request specification for POST requests
     * @return RequestSpecification
     */
    public static RequestSpecification getPostRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(ApiConfig.BASE_URI_DEV)
                .setContentType(JSON)
                .setAccept(JSON)
                .addHeader("User-Agent", "RestAssured-SDET")
                .build();
    }

    /**
     * Get request specification for PUT requests
     * @return RequestSpecification
     */
    public static RequestSpecification getPutRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(ApiConfig.BASE_URI_DEV)
                .setContentType(JSON)
                .setAccept(JSON)
                .addHeader("User-Agent", "RestAssured-SDET")
                .build();
    }

    /**
     * Get request specification for DELETE requests
     * @return RequestSpecification
     */
    public static RequestSpecification getDeleteRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(ApiConfig.BASE_URI_DEV)
                .addHeader("User-Agent", "RestAssured-SDET")
                .build();
    }

    /**
     * Get custom request specification with base URI
     * @param baseUri Base URI for the request
     * @return RequestSpecification
     */
    public static RequestSpecification getCustomRequestSpec(String baseUri) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setContentType(JSON)
                .setAccept(JSON)
                .addHeader("User-Agent", "RestAssured-SDET")
                .build();
    }
}
