# AmazonDemoProject

This project demonstrates advanced automated web interactions with Amazon's website using Selenium WebDriver. 
It covers a variety of scenarios, including product search, scraping deals, selecting items from dropdowns, managing payments, and handling Prime subscriptions.

## Features

### **1. Add to Cart and Checkout**
File: `AddtoCartuntillPayment.java`
- Searches for a specific product and sorts results by price (high to low).
- Handles product selection and dynamically switches between multiple browser windows.
- Automates the login process and selects a default shipping address.
- Allows users to choose a preferred payment method, including NetBanking.
- Integrates custom logic for handling unexpected UI changes during checkout.
- Features a robust logout mechanism and browser cleanup.

### **2. Scraping Amazon's Deals**
File: `AmazonDealsScraper.java`
- Navigates to "Today's Deals" and selects "Deal of the Day".
- Extracts and displays product titles and prices from deals.
- Automates adding a selected deal to the shopping cart.
- Demonstrates effective handling of dynamically loaded elements.

### **3. Managing Amazon Prime Subscriptions**
File: `AmazonPrimeSubscription.java`
- Automates navigation to the Prime Membership page.
- Logs in using provided credentials and explores subscription plans.
- Demonstrates selection of the monthly plan for joining Amazon Prime.
- Includes custom logout functionality for account security.

### **4. Enhanced Search with Screenshots**
File: `AmazonSearchwithScreenshot.java`
- Implements advanced product search with custom price range filters.
- Navigates to product detail pages and captures screenshots for reference.
- Provides an organized approach to storing screenshots in designated directories.

### **5. Selecting Items from Dropdown Menus**
File: `SelectingBooksfromDropdown.java`
- Automates department selection using dropdown menus (e.g., selecting "Books").
- Filters and selects specific items based on user input.
- Allows dynamic selection of the maximum available quantity of an item.
- Adds the selected item to the cart with a streamlined workflow.

## Additional Enhancements
- **User Experience Improvements**:
  - Integrated custom wait logic for dynamically loaded elements, ensuring smoother automation.
  - Used `Actions` for advanced interaction with UI elements like dropdowns and hover menus.
- **Robust Error Handling**:
  - Exception handling with meaningful console logs for better debugging.
  - Fallback mechanisms for alternate locators in checkout flows.
- **Optimized Code**:
  - Centralized constants for easy configuration and maintenance.
  - String formatting for dynamic XPath generation.
- **Utility Features**:
  - Screenshot capturing for post-automation verification (`AmazonSearchwithScreenshot`).
  - Dynamic dropdown and multi-window handling (`SelectingBooksfromDropdown`).

## Project Setup
1. Clone this repository.
2. Install [Maven](https://maven.apache.org/) for dependency management.
3. The `pom.xml` file includes:
   - Selenium WebDriver (`4.16.1`)
   - TestNG (`7.9.0`)
   - Apache Commons IO (`2.6`)
   - JUnit for testing purposes.

## How to Run
1. Compile the Java files using an IDE or Maven (`mvn compile`).
2. Run the individual classes (`*.java`) as standalone applications.
3. Ensure that ChromeDriver is in your system path.

## Prerequisites
- Java Development Kit (JDK 8 or higher).
- Compatible ChromeDriver for your browser version.
- Maven for managing dependencies.

## Disclaimer
This project is designed for educational purposes and is not affiliated with or endorsed by Amazon. Please adhere to Amazon's terms of service when running automated scripts.

---

Feel free to contribute by suggesting new features or reporting issues via pull requests or the issue tracker.
