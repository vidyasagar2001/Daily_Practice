package com.restassured.sdet.config;

/**
 * API Configuration class - Centralized configuration for API endpoints
 * This helps in managing different environment URLs
 */
public class ApiConfig {

    // Base URIs for different environments
    public static final String BASE_URI_DEV = "https://jsonplaceholder.typicode.com";
    public static final String BASE_URI_STAGING = "https://jsonplaceholder.typicode.com";
    public static final String BASE_URI_PROD = "https://jsonplaceholder.typicode.com";

    // API Endpoints
    public static final String POSTS_ENDPOINT = "/posts";
    public static final String COMMENTS_ENDPOINT = "/comments";
    public static final String USERS_ENDPOINT = "/users";
    public static final String TODOS_ENDPOINT = "/todos";

    // Default timeout values (in seconds)
    public static final int SOCKET_TIMEOUT = 30;
    public static final int CONNECTION_TIMEOUT = 30;

    /**
     * Get base URI based on environment
     * @param environment "dev", "staging", or "prod"
     * @return Base URI string
     */
    public static String getBaseUri(String environment) {
        switch (environment.toLowerCase()) {
            case "dev":
                return BASE_URI_DEV;
            case "staging":
                return BASE_URI_STAGING;
            case "prod":
                return BASE_URI_PROD;
            default:
                return BASE_URI_DEV;
        }
    }
}
