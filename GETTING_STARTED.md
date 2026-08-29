# 🚀 Getting Started - Step by Step

## Step 1: Verify Prerequisites ✅

Run these commands to verify everything is installed:

```bash
java -version
# Expected: Java 11 or higher

mvn -version
# Expected: Maven 3.6 or higher
```

If you see version numbers, you're ready! If not, install Java and Maven.

---

## Step 2: Navigate to Project Directory

```bash
cd /workspaces/Daily_Practice
```

---

## Step 3: Download Dependencies

```bash
mvn clean install
```

This will:
- Clean previous builds
- Download all Maven dependencies
- Compile the code
- Create target directory

**Time:** First time takes 2-5 minutes (depending on internet)

---

## Step 4: View Test Files

Open these files in VS Code to understand the structure:

### Start Here (Basics):
1. [src/test/java/com/restassured/sdet/basics/BasicGetTest.java](src/test/java/com/restassured/sdet/basics/BasicGetTest.java)
   - Read the comments explaining each test
   - Understand the Given-When-Then pattern

2. [QUICK_REFERENCE.md](QUICK_REFERENCE.md)
   - Keep this open for quick lookup
   - Browse through common patterns

### Then Learn (CRUD):
3. [src/test/java/com/restassured/sdet/crud/CrudOperationsTest.java](src/test/java/com/restassured/sdet/crud/CrudOperationsTest.java)
   - Understand POST, PUT, DELETE operations
   - Study the complete CRUD workflow

---

## Step 5: Run Your First Test

### Run All Tests:
```bash
mvn test
```

### Run Specific Test:
```bash
mvn test -Dtest=BasicGetTest
```

### Run Single Test Method:
```bash
mvn test -Dtest=BasicGetTest#testGetWithStatusCodeValidation
```

**Expected Output:**
- Tests should PASS ✅
- You'll see build success message
- Response logs (if logging enabled)

---

## Step 6: Analyze Test Output

After running tests:

1. Look for `BUILD SUCCESS` message
2. Review test execution summary
3. Check log output (if present)
4. Note any failures or errors

---

## Step 7: Create Your First Custom Test

Try creating a new test method in `BasicGetTest.java`:

```java
@Test
public void testMyFirstCustomTest() {
    given()
        .get("/posts/5")
        .then()
        .statusCode(200)
        .body("id", equalTo(5))
        .log().body();
}
```

Run it:
```bash
mvn test -Dtest=BasicGetTest#testMyFirstCustomTest
```

---

## Step 8: Explore the Code

### Understand the Structure:

**`ApiConfig.java`** - Configuration management
- Base URIs for different environments
- API endpoints
- Timeout values

**`ApiUtils.java`** - Common methods
- Reusable request methods
- Common assertions
- Response handling

**`BasicGetTest.java`** - Learning examples
- GET requests
- Path/query parameters
- Assertions and validations

**`CrudOperationsTest.java`** - CRUD operations
- Create (POST)
- Read (GET)
- Update (PUT)
- Delete (DELETE)

---

## Step 9: Start Learning Path

### Week 1-2: Basics
- [ ] Understand Given-When-Then pattern
- [ ] Learn GET requests
- [ ] Practice status code validation
- [ ] Understand body assertions
- [ ] Learn query and path parameters

### Week 3-4: CRUD Operations
- [ ] Learn POST requests
- [ ] Learn PUT requests
- [ ] Learn PATCH requests
- [ ] Learn DELETE requests
- [ ] Practice complete CRUD workflow

### Week 5+: Advanced Topics
- [ ] Authentication methods
- [ ] Request/Response specifications
- [ ] POJO deserialization
- [ ] Schema validation
- [ ] Performance testing

---

## Step 10: Troubleshooting

If something goes wrong:

1. **Check [TROUBLESHOOTING.md](TROUBLESHOOTING.md)** for common issues
2. **Use `.log().all()`** to see request/response details
3. **Verify API is accessible:** `curl https://jsonplaceholder.typicode.com/posts/1`
4. **Check Java/Maven versions:** `java -version` and `mvn -version`
5. **Clean rebuild:** `mvn clean install`

---

## Quick Command Reference

```bash
# Navigate to project
cd /workspaces/Daily_Practice

# Download dependencies
mvn clean install

# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=BasicGetTest

# Run specific test method
mvn test -Dtest=BasicGetTest#testGetWithStatusCodeValidation

# Run with logging
mvn test -Dtest=BasicGetTest -X

# Skip tests during build
mvn install -DskipTests

# Clean build artifacts
mvn clean

# View quick reference
cat QUICK_REFERENCE.md
```

---

## File Guide

| File | Purpose |
|------|---------|
| README.md | Main project documentation |
| QUICK_REFERENCE.md | Syntax and pattern reference |
| TROUBLESHOOTING.md | Common issues and solutions |
| pom.xml | Maven dependencies configuration |
| src/test/java/.../BasicGetTest.java | GET request examples |
| src/test/java/.../CrudOperationsTest.java | CRUD operation examples |
| src/test/java/.../ApiConfig.java | Configuration management |
| src/test/java/.../ApiUtils.java | Utility methods |

---

## Next Steps

1. ✅ Verify Java and Maven are installed
2. ✅ Navigate to project directory
3. ✅ Run `mvn clean install`
4. ✅ Read BasicGetTest.java
5. ✅ Run `mvn test -Dtest=BasicGetTest`
6. ✅ Create your first custom test
7. ✅ Experiment with assertions
8. ✅ Move to CrudOperationsTest.java
9. ✅ Practice CRUD operations
10. ✅ Move to advanced topics

---

## Success Criteria

You'll know the setup is working when:

- ✅ `mvn clean install` completes with "BUILD SUCCESS"
- ✅ `mvn test` runs without errors
- ✅ All tests show as PASSED
- ✅ You can see request/response logs
- ✅ You can modify tests and see them run

---

## Need Help?

1. **Read the documentation files included in the project**
2. **Check the QUICK_REFERENCE.md for syntax examples**
3. **Review TROUBLESHOOTING.md for common issues**
4. **Visit https://rest-assured.io/ for official docs**
5. **Use `.log().all()` liberally for debugging**

---

**You're all set! Happy learning! 🎉**

Start with BasicGetTest.java and work your way through the learning path. Don't rush - master each concept before moving to the next.
