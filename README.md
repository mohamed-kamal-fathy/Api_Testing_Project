# 🛒 Fake Store API Testing Project

A complete API testing project that covers **manual and automation** testing for the [Fake Store API](https://fakestoreapi.com/). This project was built as part of the **graduation project at ITI** and showcases both REST API validation using Postman/Newman and automated tests using **Rest Assured** and **TestNG**.

---

## 🔧 Tech Stack

- **Postman** – Manual API testing
- **Newman** – Command-line test runner for Postman collections
- **Rest Assured** – Java library for RESTful automation
- **TestNG** – Framework for organizing and running tests
- **Maven** – Project management and dependency handling
- **Allure Reports** – Beautiful test reporting
- **SLF4J (Logger)** – Structured logging
- **POJOs** – Clean request/response handling
- **Modular Java Structure** – Separated tests by module

---

## 📁 Project Structure

```
FakeStoreAPI-Testing/
│
├── BaseTest/
│   └── BaseTest.java           # Contains common setup for Rest Assured
│
├── Products/
│   └── Product_Test.java       # Tests for product endpoints
│
├── Users/
│   └── Users_Test.java         # Tests for user endpoints
│
├── Carts/
│   └── Carts_Test.java         # Tests for cart endpoints
│
├── Auth/
│   └── Login_Test.java         # Login/authentication tests
│
├── EndToEnd/
│   └── EndToEnd_Test.java      # Complete flow (user > product > cart > delete)
│
├── models/
│   ├── Product.java
│   ├── User.java
│   ├── Name.java
│   ├── Cart.java
│   └── ProductInCart.java
│
├── utils/
│   └── LoggerUtil.java         # Custom SLF4J logger utility
│
├── testng.xml                  # TestNG suite
└── pom.xml                     # Maven dependencies
```

---

## ✅ Covered Test Scenarios

### Manual Testing (Postman)
- ✅ Get all products, users, carts
- ✅ Create, update, delete product/user/cart
- ✅ Authentication (login)
- ✅ Query parameters (limit, sort, date filter)
- ✅ Negative test cases

### Automation Testing (Rest Assured + TestNG)
- ✅ Status Code Verification
- ✅ Response Body Content
- ✅ Response Time Checks
- ✅ POJO-based requests and deserialization
- ✅ Logging with SLF4J
- ✅ End-to-End test (create → validate → delete)

---

## ▶️ How to Run Automation Tests



1. **Install dependencies:**
   ```bash
   mvn clean install
   ```

2. **Run tests:**
   ```bash
   mvn test
   ```

3. **Generate Allure Report (optional):**
   ```bash
   mvn allure:serve
   ```
## ⚠️ Automation Challenges

| Challenge                            | Cause                                                                 | Solution                                                              |
|--------------------------------------|-----------------------------------------------------------------------|-----------------------------------------------------------------------|
| Authentication Enforcement           | Some endpoints require login or token                                 | Created dedicated login test with static credentials                  |
| Changing Data / IDs                  | Data is dynamic; IDs get deleted or updated during test execution     | Used POJOs to dynamically create and reuse new data in tests          |
| Inconsistent Product Object Structure| `products` in cart sometimes returns list of IDs or objects           | Handled using model deserialization and flexible assertions           |
| Rate Limiting / Timeouts             | Some endpoints are slow                                               | Applied `.time(lessThan(3000L))` to monitor and control response time|
| Data Deletion Causing Failures       | DELETE endpoints remove required data for later tests                 | Used end-to-end flow to clean up only at the end                      |

---
## 🧰 Tools Used

| Category     | Tools/Frameworks        |
|--------------|--------------------------|
| Manual API   | Postman, Newman          |
| Automation   | Rest Assured, TestNG     |
| Build Tool   | Maven                    |
| Reporting    | Allure, Logger (SLF4J)   |
| Language     | Java                     |

---


## 🙌 Credits

- Developed as part of the **ITI Graduation Project**
- Guided by mentors and supported by ITI training team

---





 **“Quality is never an accident; it is always the result of intelligent effort.”** – John Ruskin
