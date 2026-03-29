---===ParaBank Java Selenium Framework===---
                                         made by MartinSlash0
   
                              This project is a UI test automation framework
                            I built with Java, Selenium and TestNG following
                               the Page Object Model (POM) design pattern.

                          The framework automates from start to finish tests for:
                          - User registration functionality
                          - Account creation & management
                          - Money transfer between accounts
                          
                                            TESTED WEBSITES:
                          - https://parabank.parasoft.com/
                          
                                               TECH STACK:                  
                          
                          - Java 11                    - SLF4J + Logback
                          - Selenium WebDriver 4.27.0  - Firefox (GeckoDriver)
                          - TestNG 7.10.2              - Apache Commons IO
                          - Maven                       - Page Object Model (POM)
                          
                                            PROJECT STRUCTURE:
                          
                                          parabank-automation/
                                          │
                                          ├── src/main/java/org/example/
                                          │ ├── BasePage.java
                                          │ ├── ParaBankPage.java
                                          │ ├── Config.java
                                          │ ├── Locators.java
                                          │ └── SeleniumTest.java
                                          │
                                          ├── screenshots/
                                          ├── target/
                                          │ └── surefire-reports/
                                          ├── pom.xml
                                          └── README.md
                          
                                          HOW TO RUN TESTS:
                          
                          1) Clone my repository
                          
                          --> git clone https://github.com/MartinSlash0/parabank-selenium-java-v2
                          cd parabank-selenium-java-v2
                          
                          2) Install dependencies
                          
                          --> mvn clean install
                          
                          3) Run all tests
                          
                          --> mvn clean test
                          
                                          TEST SCENARIOS COVERED:
                          
                          - User registration flow
                          = Complete user signup process
                          = Registration validation using UI elements
                          = Welcome message verification

                          - Account management
                          = Login with created credentials
                          = Open new savings account
                          = Account creation confirmation

                          - Money transfer functionality
                          = Transfer funds between accounts
                          = Balance verification before/after transfer
                          = Transaction completion validation
                          
                                          SCREENSHOTS ON FAILURE:
                          - Screenshots are automatically captured on test failures
                          - Stored in the screenshots/ directory with timestamps
                          - Comprehensive logging for debugging
                          
                          ========================================================
                             This project was built as part of my QA Automation
                            learning path and an attempt to fulfill my dream of
                           working in the field of QA Automation. Building a bank
                             application test framework was the next step in my 
                           learning path. I tried focusing on best practices and
                                   real-world test automation patterns.
                          
                             Thank you for reviewing my new project. I hope you
                                    guys like it. Feel free to leave some 
                                          constructive criticism.
