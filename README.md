# Globalsqa.com Testing

Java project for Globalsqa.com website tests execution.
Firstly, chromedriver and Chrome app are initialised, then main page is got with url. Test cases based on *Base Initialization Test*. Each of them require set up and tear down of browser and driver.
- Java
- Selenium Webdriver
- Selenide
- TestNG
- Maven
- Page Object Model
- Allure

## Usage

Clone the repo:
```java
git clone https://github.com/coffee-anna/GlobalsQATesting
```
For MAC OS users:
```java
cd src/test/resources/drivers/chromedriver_mac
xattr -d com.apple.quarantine chromedriver
```
Tests execution:
```java
./mvn clean test
```
Get pretty allure report:
```java
allure generate allure-results --clean
allure open
```