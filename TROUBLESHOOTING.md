# REST Assured SDET - Troubleshooting Guide

## Common Issues and Solutions

### 1. Maven Build Issues

#### Problem: "mvn command not found"
**Solution:**
- Ensure Maven is installed: `java -version` and `mvn -version`
- Add Maven to PATH if not installed
- Download from: https://maven.apache.org/download.cgi

#### Problem: "Could not find or load main class"
**Solution:**
```bash
# Clean and rebuild
mvn clean install

# Verify pom.xml is correct
# Check Java version: java -version (should be 11+)
```

#### Problem: "Dependency resolution failed"
**Solution:**
```bash
# Clear Maven cache
rm -rf ~/.m2/repository

# Rebuild
mvn clean install -U
```

---

### 2. Test Execution Issues

#### Problem: Tests not found
**Solution:**
```bash
# Ensure tests are in src/test/java
# Ensure test class ends with "Test"
# Ensure @Test annotation is used

# Run tests with correct naming
mvn test -Dtest=BasicGetTest
```

#### Problem: "NetworkException" or connection timeout
**Solution:**
- Check internet connection
- Verify API endpoint is accessible: `curl https://jsonplaceholder.typicode.com/posts/1`
- Check firewall settings
- Increase timeout in pom.xml

#### Problem: Tests fail with "404 Not Found"
**Solution:**
- Verify base URI is correct
- Check endpoint path spelling
- Ensure API server is running
- Check API documentation for correct endpoint

---

### 3. Assertion Failures

#### Problem: Status code doesn't match
**Solution:**
```java
// Use .log().all() to see actual vs expected
.then()
    .log().all()
    .statusCode(200);
```

#### Problem: Body assertion fails
**Solution:**
```java
// Extract and inspect actual response
Response response = given()
    .get("/posts/1")
    .then()
    .extract()
    .response();

System.out.println("Actual Response: " + response.asString());
System.out.println("Status: " + response.getStatusCode());

// Then use correct path
.body("actualKey", equalTo(value))
```

#### Problem: JSONPath not working
**Solution:**
```java
// Print response to see structure
System.out.println(response.prettyPrint());

// Use correct JSONPath syntax
.body("user.profile.email", equalTo("test@example.com"))
.body("items[0].name", equalTo("Item1"))
.body("items.size()", equalTo(5))
```

---

### 4. Content Type Issues

#### Problem: "Invalid content type" error
**Solution:**
```java
// Ensure contentType matches request format
given()
    .contentType(ContentType.JSON)        // For JSON
    .contentType(ContentType.XML)         // For XML
    .contentType(ContentType.FORM)        // For form data
    .body(requestBody)
    .post("/endpoint")
    .then()
    .statusCode(201);
```

#### Problem: Response content type mismatch
**Solution:**
```java
// Verify content type header
.then()
    .log().headers()
    .contentType("application/json; charset=utf-8")
    .statusCode(200);
```

---

### 5. Request Body Issues

#### Problem: "Invalid JSON" error
**Solution:**
```java
// Validate JSON syntax
String validJson = "{\"key\": \"value\"}";  // Valid

// Not valid
String invalidJson = "{'key': 'value'}";    // Single quotes
String invalidJson2 = "{key: value}";       // No quotes

// Use proper escaping
String json = "{\"name\": \"John\", \"email\": \"john@test.com\"}";
```

#### Problem: Large request body issues
**Solution:**
```java
// Use file for large payloads
given()
    .contentType(ContentType.JSON)
    .body(new File("path/to/large-payload.json"))
    .post("/endpoint")
    .then()
    .statusCode(201);
```

---

### 6. Authentication Issues

#### Problem: "401 Unauthorized" response
**Solution:**
```java
// Verify token is valid and not expired
given()
    .header("Authorization", "Bearer " + validToken)
    .get("/protected")
    .then()
    .statusCode(200);

// Or use basic auth
given()
    .auth().basic("username", "password")
    .get("/protected")
    .then()
    .statusCode(200);
```

#### Problem: "403 Forbidden" response
**Solution:**
- User doesn't have required permissions
- Check if endpoint requires specific roles
- Verify user is authenticated

---

### 7. Response Extraction Issues

#### Problem: "Path cannot be null" error
**Solution:**
```java
// Ensure path exists in response
Response response = given()
    .get("/posts/1")
    .then()
    .extract()
    .response();

// Print response to verify structure
System.out.println(response.prettyPrint());

// Use correct path
String title = response.jsonPath().get("title");
```

#### Problem: Null Pointer Exception during extraction
**Solution:**
```java
// Check if value exists before extracting
Object value = response.jsonPath().get("optional.path");
if (value != null) {
    System.out.println(value);
}

// Or use default
String value = response.jsonPath().getString("optional.path", "default");
```

---

### 8. IDE/Editor Issues

#### Problem: Cannot resolve symbols/imports in VS Code
**Solution:**
```bash
# Install Extension Pack for Java
# Or run Maven explicitly
mvn clean install

# Reload window: Ctrl+Shift+P -> Developer: Reload Window
```

#### Problem: Red squiggly lines for imports
**Solution:**
- Ensure pom.xml is saved
- Run `mvn clean install`
- Reload IDE/editor
- Check Maven is configured in IDE

---

### 9. Logging and Debugging

#### Problem: Can't see request/response details
**Solution:**
```java
// Add logging to test
.then()
    .log().all()           // Log everything
    .statusCode(200);

// Or use request logging
given()
    .log().all()
    .post("/posts")
    .then()
    .statusCode(201);
```

#### Problem: Too much logging output
**Solution:**
```java
// Log only specific parts
.then()
    .log().body()           // Only response body
    .log().headers()        // Only response headers
    .log().status()         // Only status
    .statusCode(200);

// Log only on failure
.then()
    .log().ifValidationFails()
    .statusCode(200);
```

---

### 10. API Endpoint Issues

#### Problem: "Connection refused" error
**Solution:**
- Verify API server is running
- Check if port is correct
- Check firewall rules
- Verify base URI is correct

#### Problem: "Timeout" error
**Solution:**
```java
// Increase timeout in pom.xml or request
given()
    .connectionTimeout(60)  // seconds
    .socketTimeout(60)      // seconds
    .get("/slow-endpoint")
    .then()
    .statusCode(200);
```

---

## Quick Debugging Checklist

- [ ] Is Java 11+ installed? `java -version`
- [ ] Is Maven installed? `mvn -version`
- [ ] Are dependencies downloaded? `mvn clean install`
- [ ] Is code syntactically correct?
- [ ] Is API server running?
- [ ] Is network connectivity available?
- [ ] Check actual vs expected in `.log().all()`
- [ ] Verify JSON/XML syntax with pretty print
- [ ] Check JSONPath is correct
- [ ] Verify authentication tokens are valid

---

## Getting Help

1. **Check REST Assured Docs:** https://rest-assured.io/
2. **View Response Details:** Use `.log().all()` and `.prettyPrint()`
3. **Test API Manually:** Use Postman or curl
4. **Check Error Messages:** They often indicate the exact problem
5. **Search Stack Overflow:** Include error message in search

---

## Example of Complete Debugging

```java
@Test
public void debugFailingTest() {
    // Step 1: Setup
    RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    
    // Step 2: Make request with full logging
    Response response = given()
        .log().all()                    // Log request details
        .get("/posts/1")
        .then()
        .log().all()                    // Log response details
        .extract()
        .response();
    
    // Step 3: Inspect response
    System.out.println("Status: " + response.getStatusCode());
    System.out.println("Pretty Print:\n" + response.prettyPrint());
    
    // Step 4: Extract and verify
    String title = response.jsonPath().get("title");
    System.out.println("Title: " + title);
    
    // Step 5: Assert
    assert response.getStatusCode() == 200;
    assert title != null;
}
```

---

**Remember:** Use `.log().all()` liberally during development. It's your best friend for debugging!
