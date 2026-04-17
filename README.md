# 🧪 ShopBot – Selenium Java Automation Framework

## 📌 Project Overview

ShopBot is a Selenium-based automation framework built using Java to test a retail e-commerce application (**SauceDemo**).

The framework follows the Page Object Model (POM) design pattern and supports:

* Scalable test design
* Parallel execution
* Retry mechanism
* Screenshot capture on failure
* Extent Reports for execution results

---

## ⚙️ Tech Stack

* Java
* Selenium WebDriver
* TestNG
* Maven
* WebDriverManager
* Extent Reports

---

## 🏗️ Project Structure

```
ShopBot
│
├── src/
│   ├── main/
│   │   ├── java/
│   │      ├── base/
│   │      │   └── BasePage.java          # Common reusable page methods
│   │      │
│   │      ├── pages/
│   │      │   ├── LoginPage.java        # Handles login functionality
│   │      │   ├── ProductPage.java      # Product listing and actions
│   │      │   ├── CartPage.java         # Cart operations
│   │      │   └── CheckoutPage.java     # Checkout flow handling
│   │      │
│   │      └── utils/
│   │          ├── ConfigReader.java     # Reads configuration properties
│   │          ├── ExcelUtil.java        # Handles Excel test data
│   │          └── ExtentManager.java    # Manages Extent Reports
│   │
│   ├── test/
│   │   ├── java/
│   │   │   ├── base/
│   │   │   │   └── BaseTest.java        # Test setup and teardown
│   │   │   │
│   │   │   ├── listeners/
│   │   │   │   ├── TestListener.java    # Reporting listener
│   │   │   │   └── RetryListener.java   # Retry mechanism listener
│   │   │   │
│   │   │   ├── tests/
│   │   │   │   ├── LoginTest.java       # Login test cases
│   │   │   │   ├── ProductTest.java     # Product-related tests
│   │   │   │   ├── CartTest.java        # Cart functionality tests
│   │   │   │   ├── CheckoutTest.java    # Checkout validation tests
│   │   │   │   └── ProblemUserTest.java # Edge case scenarios
│   │   │   │
│   │   │   └── utils/
│   │   │       └── RetryAnalyzer.java   # Retry failed test logic
│   │   │
│   │   └── resources/
│   │       ├── config.properties       # Test configuration (URL, credentials)
│   │       └── testdata/
│   │           └── LoginData.xlsx      # External test data (Excel)
│
├── reports/                           # Extent Reports output
├── screenshots/                       # Failure screenshots
│
├── pom.xml                            # Maven dependencies and plugins
└── testng.xml                         # TestNG suite configuration
```

---

## 🧪 Test Modules Covered

### 1. User Authentication

* Login with valid credentials
* Locked user validation
* Empty credentials validation
* Logout verification

### 2. Product Listing & Sorting

* Verify product visibility
* Sort by name (A–Z)
* Sort by price (low → high)
* Product detail validation

### 3. Shopping Cart

* Add single and multiple products
* Remove product
* Cart persistence validation

### 4. Checkout Flow

* Enter customer details
* Verify order summary
* Complete order and confirmation

### 5. Problem User Validation

* Verify broken UI behavior (image issues)
* Validate add-to-cart behavior
* Detect image mismatch

---

## ▶️ How to Run Tests

###🔹 Using Maven

```bash
mvn test
```

### 🔹 Using TestNG XML

1. Right-click on `testng.xml`
2. Select **Run As → TestNG Suite**
---

## 📊 Reports

After execution:

* 📁 `reports/extent-report.html` → Execution report
* 📁 `screenshots/` → Failure screenshots

---

## ⚙️ Configuration

Located in:

```
src/main/resources/config.properties
```

```
browser=chrome
baseUrl=https://www.saucedemo.com
timeout=10
user=standard_user
password=secret_sauce
```

---

## 📌 Conclusion

This framework demonstrates a robust, scalable, and maintainable automation solution using Selenium and TestNG. It follows best practices like POM, parallel execution, and reporting.

---
