# REST Assured SDET Learning Journey 🚀

Welcome to your REST Assured learning workspace! This project is designed to help you master API testing with REST Assured.

## 📋 Project Overview

This is a comprehensive REST Assured framework designed for SDET (Software Development Engineer in Test) professionals. It includes:

- ✅ Basic GET request testing
- ✅ CRUD operations (POST, PUT, DELETE, PATCH)
- ✅ Request/Response validation
- ✅ JSON & XML parsing
- ✅ API configuration management
- ✅ Reusable utilities

## 🏗️ Project Structure

```
Daily_Practice/
├── pom.xml                          # Maven configuration with dependencies
├── testng.xml                       # TestNG configuration
├── src/test/java/
│   └── com/restassured/sdet/
│       ├── basics/
│       │   └── BasicGetTest.java   # Basic GET request examples
│       ├── crud/
│       │   └── CrudOperationsTest.java  # POST, PUT, DELETE, PATCH examples
│       ├── config/
│       │   └── ApiConfig.java      # API configuration management
│       └── utils/
│           └── ApiUtils.java       # Common utility methods
└── README.md                        # This file
```

## 🛠️ Prerequisites

Before you start, ensure you have:

1. **Java 11 or higher** - [Download Java](https://www.oracle.com/java/technologies/downloads/)
2. **Maven 3.6+** - [Download Maven](https://maven.apache.org/download.cgi)
3. **IDE** - VS Code, IntelliJ IDEA, or Eclipse

Verify installations:
```bash
java -version
mvn -version
```

## 📦 Dependencies Included

The project uses the following key dependencies:

- **REST Assured 5.3.2** - Main REST API testing library
- **TestNG 7.8.1** - Test framework
- **Hamcrest 2.2** - Assertion matchers
- **AssertJ 3.24.1** - Advanced assertions
- **SLF4J 2.0.9** - Logging

## 🚀 Getting Started

### 1. **Clone and Setup**

```bash
cd /workspaces/Daily_Practice
```

### 2. **Download Dependencies**

```bash
mvn clean install
```

This will download all required dependencies and compile the project.

### 3. **Run All Tests**

```bash
mvn test
```

### 4. **Run Specific Test Class**

```bash
mvn test -Dtest=BasicGetTest
```

### 5. **Run Using TestNG**

```bash
mvn test -Dsuite=testng.xml
```

## 📚 Learning Path

### **Level 1: Basics** 📖

Start with `BasicGetTest.java` to learn:

- ✅ Simple GET requests
- ✅ Status code validation
- ✅ Response body validation
- ✅ Query and path parameters
- ✅ Header validation
- ✅ Response extraction

**Example:**
```java
given()
    .get("/posts/1")
    .then()
    .statusCode(200)
    .body("userId", equalTo(1));
```

### **Level 2: CRUD Operations** 🔄

Move to `CrudOperationsTest.java` to learn:

- ✅ POST requests (Create)
- ✅ PUT requests (Update)
- ✅ PATCH requests (Partial Update)
- ✅ DELETE requests (Delete)
- ✅ Request body construction
- ✅ Complete CRUD workflow

**Example:**
```java
given()
    .contentType(ContentType.JSON)
    .body(requestBody)
    .post("/posts")
    .then()
    .statusCode(201);
```

### **Level 3: Advanced Topics** 🚀

*Coming soon* - You'll learn:

- Authentication & Authorization
- Request/Response Logging
- Response Schema Validation
- Request Specifications & Response Specifications
- Serial/Deserialization with POJO
- Filters and Interceptors
- Performance Testing
- API Chaining

## 💡 Key REST Assured Concepts

### **Given-When-Then Pattern**

REST Assured uses the Given-When-Then pattern (BDD style):

```java
given()           // Set up request parameters, headers, body
    .when()       // Perform the HTTP method (GET, POST, etc.)
    .then()       // Assert and validate response
```

### **Common Matchers**

```java
// Equality
body("key", equalTo(value))

// Containment
body("name", containsString("John"))

// Null checks
body("email", notNullValue())

// Comparisons
body("age", greaterThan(18))

// Collections
body("items", hasSize(5))
body("items", everyItem(notNullValue()))
```

### **Response Extraction**

```java
Response response = given()
    .get("/posts/1")
    .then()
    .statusCode(200)
    .extract()
    .response();

int userId = response.jsonPath().get("userId");
```

## 🧪 Sample Test Execution

### Run Basic Tests:
```bash
mvn test -Dtest=BasicGetTest#testGetWithStatusCodeValidation
```

### Run All CRUD Tests:
```bash
mvn test -Dtest=CrudOperationsTest
```

## 📝 Understanding Test Structure

Each test class has:

1. **@BeforeClass** - Setup method (runs once before all tests)
2. **@Test** - Individual test methods
3. **Logging** - `.log().all()` to see request/response details
4. **Assertions** - Validations using matchers

## 🔗 API Used for Learning

This project uses **JSONPlaceholder** (free REST API):

- **Base URL:** `https://jsonplaceholder.typicode.com`
- **Endpoints:**
  - `/posts` - Blog posts (100 posts)
  - `/comments` - Comments on posts
  - `/users` - User data
  - `/todos` - To-do items

No authentication required - perfect for learning!

## 🎯 Practice Exercises

### Exercise 1: GET Request Variations
- Get a specific post
- Get posts by a specific user
- Get posts and validate multiple fields
- **Solution:** See `BasicGetTest.java`

### Exercise 2: POST Request
- Create a new post with title, body, and userId
- Validate response contains created ID
- **Solution:** See `CrudOperationsTest.java`

### Exercise 3: Update Operations
- Update an existing post (PUT)
- Partially update a post (PATCH)
- **Solution:** See `CrudOperationsTest.java`

### Exercise 4: Complete CRUD
- Create → Read → Update → Delete workflow
- **Solution:** `testCompleteCrudWorkflow()` in `CrudOperationsTest.java`

## 🛠️ Useful Maven Commands

```bash
# Clean build
mvn clean

# Compile code
mvn compile

# Run all tests
mvn test

# Run specific test
mvn test -Dtest=ClassName

# Skip tests during build
mvn install -DskipTests

# Generate reports
mvn surefire-report:report

# View test results
mvn surefire-report:report-only
```

## 📊 Common Assertion Examples

```java
// Status codes
.statusCode(200)
.statusCode(anyOf(equalTo(200), equalTo(201)))

// Headers
.header("Content-Type", equalTo("application/json"))
.header("Server", notNullValue())

// Response body
.body("users.name", hasItems("John", "Jane"))
.body("size()", equalTo(10))
.body("", instanceOf(List.class))

// Nested JSON
.body("user.profile.email", containsString("@gmail.com"))
.body("items.findAll{it.active}.size()", equalTo(5))
```

## 🐛 Debugging Tips

### Enable detailed logging:
```java
.log().all()          // Log all details
.log().body()         // Log only body
.log().headers()      // Log only headers
.log().ifValidationFails()  // Log only on failure
```

### Extract and print response:
```java
Response response = given()
    .get("/posts/1")
    .then()
    .extract()
    .response();

System.out.println(response.asString());
System.out.println(response.getStatusCode());
```

## 📖 Resources & Further Learning

- **Official Documentation:** https://rest-assured.io/
- **GitHub:** https://github.com/rest-assured/rest-assured
- **Hamcrest Matchers:** http://hamcrest.org/JavaHamcrest/
- **TestNG Documentation:** https://testng.org/

## 🎓 What's Next?

After completing basics:

1. Learn about **Response Specifications**
2. Explore **POJO Deserialization** for complex JSON
3. Implement **Request/Response Logging Filters**
4. Add **API Schema Validation** with JSON Schema
5. Learn **Authentication methods** (Bearer, API Key, Basic Auth)
6. Master **API Chaining** (use response from one API in another)
7. Explore **Performance Testing** with REST Assured

## 🤝 Contributing to Your Learning

As you learn, consider:

- Adding more test cases
- Creating tests for different scenarios
- Building a test utility library
- Implementing Page Object Model pattern
- Adding CI/CD pipeline

## ⚠️ Common Pitfalls to Avoid

1. ❌ Hardcoding URLs - Use `ApiConfig.java`
2. ❌ No logging - Always use `.log()` for debugging
3. ❌ Poor assertions - Be specific with validations
4. ❌ Ignoring timeouts - Set appropriate timeouts for APIs
5. ❌ Not handling errors - Implement error handling

## 📞 Need Help?

- Check test output for error messages
- Use `.log().all()` to see request/response
- Read REST Assured documentation
- Review example tests in this project

---

**Happy Learning! 🎉**

Remember: Consistency is key. Practice regularly and gradually increase complexity.

*Last Updated: August 2026*
