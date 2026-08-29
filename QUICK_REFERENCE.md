# REST Assured Quick Reference Guide

## 1. Basic Syntax (Given-When-Then)

```java
given()                    // Prerequisites (headers, params, body, etc.)
    .contentType("application/json")
    .body(payload)
.when()                    // HTTP method (GET, POST, PUT, DELETE, PATCH)
    .post("/endpoint")
.then()                    // Assertions and validations
    .statusCode(200)
    .body("key", equalTo("value"));
```

## 2. HTTP Methods with REST Assured

### GET Request
```java
given()
    .get("/posts/1")
    .then()
    .statusCode(200);
```

### POST Request
```java
given()
    .contentType(ContentType.JSON)
    .body("{\"title\": \"Test\"}")
    .post("/posts")
    .then()
    .statusCode(201);
```

### PUT Request
```java
given()
    .contentType(ContentType.JSON)
    .body("{\"id\": 1, \"title\": \"Updated\"}")
    .put("/posts/1")
    .then()
    .statusCode(200);
```

### DELETE Request
```java
given()
    .delete("/posts/1")
    .then()
    .statusCode(200);
```

### PATCH Request
```java
given()
    .contentType(ContentType.JSON)
    .body("{\"title\": \"Partially Updated\"}")
    .patch("/posts/1")
    .then()
    .statusCode(200);
```

## 3. Request Parameters

### Query Parameters
```java
given()
    .queryParam("userId", 1)
    .queryParam("page", 2)
    .get("/posts")
    .then()
    .statusCode(200);
```

### Path Parameters
```java
given()
    .pathParam("id", 1)
    .get("/posts/{id}")
    .then()
    .statusCode(200);
```

### Multiple Path Parameters
```java
given()
    .pathParam("userId", 1)
    .pathParam("postId", 5)
    .get("/users/{userId}/posts/{postId}")
    .then()
    .statusCode(200);
```

## 4. Headers

### Add Single Header
```java
given()
    .header("Authorization", "Bearer token123")
    .get("/protected")
    .then()
    .statusCode(200);
```

### Add Multiple Headers
```java
given()
    .headers("Content-Type", "application/json",
             "Accept", "application/json")
    .get("/posts")
    .then()
    .statusCode(200);
```

### Using Map for Headers
```java
Map<String, String> headers = new HashMap<>();
headers.put("Authorization", "Bearer token");
headers.put("Accept", "application/json");

given()
    .headers(headers)
    .get("/posts")
    .then()
    .statusCode(200);
```

## 5. Request Body

### String Body
```java
String jsonBody = "{\"title\": \"Test Post\"}";
given()
    .contentType(ContentType.JSON)
    .body(jsonBody)
    .post("/posts")
    .then()
    .statusCode(201);
```

### JSON String
```java
given()
    .contentType("application/json")
    .body("{\n  \"title\": \"Test\"\n}")
    .post("/posts")
    .then()
    .statusCode(201);
```

### File Body
```java
given()
    .contentType(ContentType.JSON)
    .body(new File("request.json"))
    .post("/posts")
    .then()
    .statusCode(201);
```

## 6. Response Assertions

### Status Code
```java
.statusCode(200)
.statusCode(anyOf(200, 201))
```

### Response Body - Exact Match
```java
.body("userId", equalTo(1))
.body("id", is(1))
```

### Response Body - String Matching
```java
.body("title", containsString("REST"))
.body("title", startsWith("REST"))
.body("title", endsWith("API"))
```

### Response Body - Null Checks
```java
.body("email", notNullValue())
.body("phone", nullValue())
```

### Response Body - Collections
```java
.body("items", hasSize(5))
.body("items", everyItem(notNullValue()))
.body("items", hasItem("item1"))
```

### Response Body - Nested JSON
```java
.body("user.name", equalTo("John"))
.body("user.profile.email", containsString("@"))
```

### Response Body - Array Operations
```java
.body("posts.title", hasItems("Post1", "Post2"))
.body("posts.size()", equalTo(10))
```

### Response Body - Conditional Assertions
```java
.body("users.findAll{it.active}.size()", equalTo(5))
.body("data.findAll{it.age > 18}.name", hasItems("John", "Jane"))
```

## 7. Headers Assertions

```java
.header("Content-Type", containsString("application/json"))
.header("Server", notNullValue())
.headers("Content-Type", "application/json; charset=utf-8")
```

## 8. Response Extraction

### Extract Entire Response
```java
Response response = given()
    .get("/posts/1")
    .then()
    .statusCode(200)
    .extract()
    .response();

System.out.println(response.getBody().asString());
```

### Extract Specific Value (JSONPath)
```java
String title = given()
    .get("/posts/1")
    .then()
    .extract()
    .path("title");
```

### Extract Using JSONPath from Response
```java
Response response = given()
    .get("/posts/1")
    .then()
    .extract()
    .response();

String title = response.jsonPath().get("title");
int userId = response.jsonPath().get("userId");
```

### Extract XML Path
```java
String value = given()
    .get("/data.xml")
    .then()
    .extract()
    .path("root.element.text()");
```

## 9. Logging

### Log Everything
```java
.then()
    .log().all()
    .statusCode(200);
```

### Log Specific Parts
```java
.then()
    .log().body()      // Only body
    .log().headers()   // Only headers
    .log().status()    // Only status
    .statusCode(200);
```

### Log on Condition
```java
.then()
    .log().ifValidationFails()
    .statusCode(200);
```

### Log Request
```java
given()
    .log().all()
    .get("/posts")
    .then()
    .statusCode(200);
```

## 10. Base URI Setup

### Set Globally
```java
RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

// Now use relative paths
given()
    .get("/posts/1")
    .then()
    .statusCode(200);
```

### Set per Request
```java
given()
    .baseUri("https://jsonplaceholder.typicode.com")
    .basePath("/api/v1")
    .get("/posts/1")
    .then()
    .statusCode(200);
```

## 11. Response Specifications

```java
ResponseSpecification respSpec = new ResponseSpecBuilder()
    .expectStatusCode(200)
    .expectContentType(ContentType.JSON)
    .expectBody("title", notNullValue())
    .build();

given()
    .get("/posts/1")
    .then()
    .spec(respSpec);
```

## 12. Request Specifications

```java
RequestSpecification reqSpec = new RequestSpecBuilder()
    .setBaseUri("https://jsonplaceholder.typicode.com")
    .addHeader("Accept", "application/json")
    .setContentType(ContentType.JSON)
    .build();

given()
    .spec(reqSpec)
    .get("/posts/1")
    .then()
    .statusCode(200);
```

## 13. Common Matchers from Hamcrest

```java
equalTo()              // Exact match
notNullValue()        // Not null
nullValue()           // Is null
containsString()      // Contains substring
startsWith()          // Starts with string
endsWith()            // Ends with string
hasSize()             // Collection size
hasItem()             // Contains item
hasItems()            // Contains multiple items
everyItem()           // All items match
greaterThan()         // Greater than
lessThan()            // Less than
anyOf()               // One of many options
```

## 14. Authentication Methods

### Basic Authentication
```java
given()
    .auth().basic("username", "password")
    .get("/protected")
    .then()
    .statusCode(200);
```

### OAuth2 Token
```java
given()
    .auth().oauth2("access_token")
    .get("/protected")
    .then()
    .statusCode(200);
```

### Bearer Token (Header)
```java
given()
    .header("Authorization", "Bearer " + token)
    .get("/protected")
    .then()
    .statusCode(200);
```

### API Key (Header)
```java
given()
    .header("X-API-Key", "your-api-key")
    .get("/protected")
    .then()
    .statusCode(200);
```

## 15. Common Test Patterns

### Test for Multiple Fields
```java
given()
    .get("/posts/1")
    .then()
    .statusCode(200)
    .body("id", notNullValue())
    .body("userId", greaterThan(0))
    .body("title", not(emptyString()))
    .body("body", not(emptyString()));
```

### Test List Response
```java
given()
    .get("/posts?userId=1")
    .then()
    .statusCode(200)
    .body("", hasSize(greaterThan(0)))
    .body("userId", everyItem(equalTo(1)))
    .body("title", everyItem(notNullValue()));
```

### Conditional Validation
```java
given()
    .get("/users")
    .then()
    .statusCode(200)
    .body("findAll{it.active}.size()", greaterThan(0));
```

## Quick Tips

1. Always use `.log().all()` during development to see request/response
2. Use `.log().ifValidationFails()` in production for error debugging
3. Extract responses into variables for complex assertions
4. Use `RequestSpecification` and `ResponseSpecification` for reusable configs
5. Keep base URLs in configuration files (don't hardcode)
6. Use descriptive test method names (what, given what, expect what)
7. Handle timeouts appropriately for slow APIs
8. Use matchers for flexible assertions

## Useful Links

- REST Assured Docs: https://rest-assured.io/
- Hamcrest Matchers: http://hamcrest.org/JavaHamcrest/
- JSONPath: https://github.com/jayway/JsonPath
- XMLPath: https://rest-assured.io/docs/xml/
