package com.restassured.sdet.spec;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.*;

/**
 * Custom Response Specification Builder
 * Provides reusable response specifications for different scenarios
 */
public class ResponseSpecs {

    /**
     * Get default response specification with standard validations
     * @return ResponseSpecification
     */
    public static ResponseSpecification getDefaultResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType("application/json; charset=utf-8")
                .expectResponseTime(lessThan(10000L)) // 10 seconds
                .build();
    }

    /**
     * Get response specification for successful GET requests
     * @return ResponseSpecification
     */
    public static ResponseSpecification getSuccessfulGetResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType("application/json; charset=utf-8")
                .expectHeader("Content-Type", containsString("application/json"))
                .expectResponseTime(lessThan(10000L))
                .build();
    }

    /**
     * Get response specification for successful POST requests
     * @return ResponseSpecification
     */
    public static ResponseSpecification getSuccessfulPostResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(201)
                .expectContentType("application/json; charset=utf-8")
                .expectHeader("Content-Type", containsString("application/json"))
                .expectResponseTime(lessThan(10000L))
                .build();
    }

    /**
     * Get response specification for successful PUT requests
     * @return ResponseSpecification
     */
    public static ResponseSpecification getSuccessfulPutResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType("application/json; charset=utf-8")
                .expectHeader("Content-Type", containsString("application/json"))
                .expectResponseTime(lessThan(10000L))
                .build();
    }

    /**
     * Get response specification for successful DELETE requests
     * @return ResponseSpecification
     */
    public static ResponseSpecification getSuccessfulDeleteResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectResponseTime(lessThan(10000L))
                .build();
    }

    /**
     * Get response specification for resource not found (404)
     * @return ResponseSpecification
     */
    public static ResponseSpecification getNotFoundResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(404)
                .expectResponseTime(lessThan(10000L))
                .build();
    }

    /**
     * Get response specification for bad request (400)
     * @return ResponseSpecification
     */
    public static ResponseSpecification getBadRequestResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(400)
                .expectResponseTime(lessThan(10000L))
                .build();
    }

    /**
     * Get response specification for unauthorized (401)
     * @return ResponseSpecification
     */
    public static ResponseSpecification getUnauthorizedResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(401)
                .expectResponseTime(lessThan(10000L))
                .build();
    }

    /**
     * Get response specification for forbidden (403)
     * @return ResponseSpecification
     */
    public static ResponseSpecification getForbiddenResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(403)
                .expectResponseTime(lessThan(10000L))
                .build();
    }

    /**
     * Get response specification for server error (500)
     * @return ResponseSpecification
     */
    public static ResponseSpecification getServerErrorResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(500)
                .expectResponseTime(lessThan(10000L))
                .build();
    }

    /**
     * Get custom response specification with specific status code
     * @param statusCode Expected status code
     * @return ResponseSpecification
     */
    public static ResponseSpecification getCustomResponseSpec(int statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .expectContentType("application/json; charset=utf-8")
                .expectResponseTime(lessThan(10000L))
                .build();
    }
}
