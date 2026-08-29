package com.restassured.sdet.basics;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.restassured.sdet.spec.RequestSpecs;
import com.restassured.sdet.spec.ResponseSpecs;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * Basic GET Request Tests with REST Assured
 * This test demonstrates fundamental REST Assured concepts with Request/Response Specifications
 */
public class BasicGetTest {

    private RequestSpecification requestSpec;
    private ResponseSpecification responseSpec;

    @BeforeClass
    public void setup() {
        // Initialize request and response specifications
        requestSpec = RequestSpecs.getGetRequestSpec();
        responseSpec = ResponseSpecs.getSuccessfulGetResponseSpec();
        
        // Set base URI for all requests (optional, already in spec)
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    /**
     * Test 1: Simple GET request with status code validation using spec
     */
    @Test
    public void testGetWithStatusCodeValidation() {
        given(requestSpec)
                .get("/posts/1")
                .then()
                .spec(responseSpec)
                .log().all();
    }

    /**
     * Test 2: GET request with response body validation using spec
     */
    @Test
    public void testGetWithBodyValidation() {
        given(requestSpec)
                .get("/posts/1")
                .then()
                .spec(responseSpec)
                .body("userId", equalTo(1))
                .body("id", equalTo(1))
                .body("title", not(emptyString()))
                .log().body();
    }

    /**
     * Test 3: GET request and extract response using spec
     */
    @Test
    public void testGetAndExtractResponse() {
        Response response = given(requestSpec)
                .get("/posts/1")
                .then()
                .spec(responseSpec)
                .extract()
                .response();

        System.out.println("Response Status: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
        System.out.println("User ID: " + response.jsonPath().get("userId"));
    }

    /**
     * Test 4: GET request with query parameters using spec
     */
    @Test
    public void testGetWithQueryParameters() {
        given(requestSpec)
                .queryParam("userId", 1)
                .get("/posts")
                .then()
                .spec(responseSpec)
                .body("userId", everyItem(equalTo(1)))
                .log().body();
    }

    /**
     * Test 5: GET request with path parameters using spec
     */
    @Test
    public void testGetWithPathParameters() {
        given(requestSpec)
                .pathParam("postId", 5)
                .get("/posts/{postId}")
                .then()
                .spec(responseSpec)
                .body("id", equalTo(5))
                .log().body();
    }

    /**
     * Test 6: GET request with multiple validations using spec
     */
    @Test
    public void testGetWithMultipleValidations() {
        given(requestSpec)
                .get("/posts/1")
                .then()
                .spec(responseSpec)
                .header("Content-Type", containsString("application/json"))
                .body("userId", notNullValue())
                .body("title", notNullValue())
                .body("body", notNullValue())
                .log().all();
    }
}
