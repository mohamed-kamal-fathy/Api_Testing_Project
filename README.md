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

1. **Clone the repo:**
   ```bash
   git clone https://github.com/your-username/fakestoreapi-testing.git
   cd fakestoreapi-testing
   ```

2. **Install dependencies:**
   ```bash
   mvn clean install
   ```

3. **Run tests:**
   ```bash
   mvn test
   ```

4. **Generate Allure Report (optional):**
   ```bash
   mvn allure:serve
   ```

---

## 📌 Sample Allure Report

![Allure Report Screenshot](docs/allure-screenshot.png)

---

## 🙌 Credits

- Developed as part of the **ITI Graduation Project**
- Guided by mentors and supported by ITI training team

---

## 📎 License

This project is licensed under the [MIT License](LICENSE).

---

> “Quality is never an accident; it is always the result of intelligent effort.” – John Ruskin
