package com.restassured.sdet.crud;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * CRUD Operations Tests with REST Assured
 * This test demonstrates POST, PUT, DELETE requests
 */
public class CrudOperationsTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    /**
     * Test 1: POST request to create a resource
     */
    @Test
    public void testPostRequest() {
        String requestBody = "{\n" +
                "  \"title\": \"Learning REST Assured\",\n" +
                "  \"body\": \"This is my first POST request\",\n" +
                "  \"userId\": 1\n" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("/posts")
                .then()
                .statusCode(201)
                .body("title", equalTo("Learning REST Assured"))
                .body("userId", equalTo(1))
                .log().all();
    }

    /**
     * Test 2: POST request using JSON object
     */
    @Test
    public void testPostWithJsonObject() {
        String jsonBody = "{\n" +
                "  \"title\": \"My Test Post\",\n" +
                "  \"body\": \"Testing POST with JSON\",\n" +
                "  \"userId\": 2\n" +
                "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .post("/posts")
                .then()
                .statusCode(201)
                .extract()
                .response();

        System.out.println("Created ID: " + response.jsonPath().get("id"));
    }

    /**
     * Test 3: PUT request to update a resource
     */
    @Test
    public void testPutRequest() {
        String updateBody = "{\n" +
                "  \"id\": 1,\n" +
                "  \"title\": \"Updated Title\",\n" +
                "  \"body\": \"Updated body content\",\n" +
                "  \"userId\": 1\n" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .body(updateBody)
                .put("/posts/1")
                .then()
                .statusCode(200)
                .body("title", equalTo("Updated Title"))
                .body("id", equalTo(1))
                .log().all();
    }

    /**
     * Test 4: PATCH request to partially update a resource
     */
    @Test
    public void testPatchRequest() {
        String patchBody = "{\n" +
                "  \"title\": \"Partially Updated Title\"\n" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .body(patchBody)
                .patch("/posts/1")
                .then()
                .statusCode(200)
                .body("title", equalTo("Partially Updated Title"))
                .log().all();
    }

    /**
     * Test 5: DELETE request to remove a resource
     */
    @Test
    public void testDeleteRequest() {
        given()
                .delete("/posts/1")
                .then()
                .statusCode(200)
                .log().all();
    }

    /**
     * Test 6: Complete CRUD workflow
     * Note: JSONPlaceholder is a mock API and doesn't actually persist data
     * This test demonstrates the workflow pattern, not actual persistence
     */
    @Test
    public void testCompleteCrudWorkflow() {
        // CREATE - JSONPlaceholder returns 201 with mock ID 101
        String createBody = "{\n" +
                "  \"title\": \"CRUD Test\",\n" +
                "  \"body\": \"Testing complete CRUD\",\n" +
                "  \"userId\": 3\n" +
                "}";

        Response createResponse = given()
                .contentType(ContentType.JSON)
                .body(createBody)
                .post("/posts")
                .then()
                .statusCode(201)
                .extract()
                .response();

        int createdId = createResponse.jsonPath().get("id");
        System.out.println("Created Post ID: " + createdId);

        // READ - Use existing post instead (JSONPlaceholder doesn't persist)
        given()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .body("title", notNullValue());

        // UPDATE - Update existing post
        String updateBody = "{\n" +
                "  \"id\": 1,\n" +
                "  \"title\": \"Updated CRUD Test\",\n" +
                "  \"body\": \"Updated testing complete CRUD\",\n" +
                "  \"userId\": 1\n" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .body(updateBody)
                .put("/posts/1")
                .then()
                .statusCode(200)
                .body("title", equalTo("Updated CRUD Test"));

        // DELETE - Delete existing post
        given()
                .delete("/posts/1")
                .then()
                .statusCode(200);

        System.out.println("CRUD workflow completed successfully!");
    }
}
