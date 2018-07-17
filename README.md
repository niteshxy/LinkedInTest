# LinkedInTest
LinkedInTestDemo - This is a sample project for UI automation using selenium.

This has been made for POC purpose and is created using data driven approach with Page Object pattern.
In this project Selenium is used with TestNG and Apache POI is used for read/write on file. Extent reports are used for reporting purpose.

The different components of this framework are mentioned below -
1. Utilities - LinkedInTest/src/main/java/Utilities contains the code for File read write and Driver Initialization.
2. Page Objects and Common methods - LinkedInTest/src/main/java/pageObjects contains page objects for different pages and also contain common 
functions used in application.
3. Test Class - LinkedInTest/src/main/java/testCases has the test class, more tests can be added to it.
4. Test Data - LinkedInTest/src/test/resources/TestData/TestData.xlsx contains the test data, for our purpose it has login credentials data.
5. Selenium Drivers - LinkedInTest/src/main/resources/drivers contains all the drivers for selenium.
