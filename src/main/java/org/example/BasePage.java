package org.example;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class BasePage {

    protected static final Logger logger = LoggerFactory.getLogger(BasePage.class);

    WebDriver driver;
    WebDriverWait waitFast;
    WebDriverWait waitShort;
    WebDriverWait waitMedium;
    WebDriverWait waitLong;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.waitFast = new WebDriverWait(driver,Duration.ofSeconds(1));
        this.waitShort = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.waitMedium = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.waitLong = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    protected void goToUrl(String url) {
        try{
            driver.get(url);
            logger.info("Loading page: " + url);
        } catch(TimeoutException e){
            logger.error("Loading page Timeout: " + url);
            Assert.fail("Failed to load page timeout: " + e.getMessage());
        } catch(Exception e){
            logger.error("Loading page Failed: " + url);
            Assert.fail("Failed to load page unexpected error: " + e.getMessage());
        }
    }

    protected void fillField(By locator, String value) {
        try{
            scrollToElement(locator);

            WebElement field = waitShort.until(
                    ExpectedConditions.elementToBeClickable(
                            locator
                    )
            );

            field.clear();
            field.sendKeys(value);

            logger.info("Filling field: {} Value: {}",locator ,value);
        } catch(TimeoutException e){
            logger.error("Timeout waiting for element: {}", locator);
            Assert.fail("Element not found within timeout: " + locator);
        } catch(Exception e){
            logger.error("Unexpected error filling field: {}", e.getMessage());
            Assert.fail("Failed to fill field: "+ locator +" Error: "  + e.getMessage());
        }
    }

    protected void clickElement(By locator) {
        int maxAttempts = 5;

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                scrollToElement(locator);

                WebElement element = waitShort.until(
                        ExpectedConditions.elementToBeClickable(locator)
                );

                element.click();

                logger.info("Element clicked: {}", locator);
                return;

            } catch(TimeoutException e) {
                logger.warn("Timeout waiting for element: {} (attempt {}/{})",
                        locator, attempt, maxAttempts);

                if (attempt == maxAttempts) {
                    logger.error("Element not found after {} attempts", maxAttempts);
                    Assert.fail("Element not found after " + maxAttempts + " attempts: " + locator);
                }

                try { Thread.sleep(500); } catch (InterruptedException ie) {}

            } catch(StaleElementReferenceException e) {
                logger.warn("Stale element: {} (attempt {}/{})", locator, attempt, maxAttempts);

                if (attempt == maxAttempts) {
                    logger.error("Element stale after {} attempts", maxAttempts);
                    Assert.fail("Stale element after " + maxAttempts + " attempts: " + locator);
                }

                try { Thread.sleep(500); } catch (InterruptedException ie) {}

            } catch (ElementClickInterceptedException e) {
                logger.warn("Click intercepted: {} (attempt {}/{})", locator, attempt, maxAttempts);

                if (attempt == maxAttempts) {
                    logger.error("Element still intercepted after {} attempts", maxAttempts);
                    Assert.fail("Click intercepted after " + maxAttempts + " attempts: " + locator);
                }

                try { Thread.sleep(1000); } catch (InterruptedException ie) {}

            } catch (Exception e) {
                logger.error("Unexpected error clicking element {}: {}", locator, e.getMessage());
                Assert.fail("Failed to click: " + locator + " - " + e.getMessage());
            }
        }
    }

    protected void scrollToElement(By locator) {
        try{
            WebElement element = waitShort.until(
                    ExpectedConditions.presenceOfElementLocated(
                            locator
                    )
            );

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", element);

            logger.info("Scrolled to: {}", locator);
        } catch (Exception e){
            logger.error("Failed to scroll to element: {}", locator);
            Assert.fail("Failed to scroll to element: "+ locator +" Error: "  + e.getMessage());
        }
    }

    protected void clickCustomDropdownOption(String option) {
        try{
            By optionLocator = By.xpath("//option[text()='"+option+"']");

            clickElement(optionLocator);

            logger.info("Clicked option in Dropdown: {}",optionLocator);
        } catch(Exception e){
            logger.error("Failed to click option in Dropdown: {}",option);
            Assert.fail("Failed to click: "+ option +" Error: "  + e.getMessage());
        }

    }

    protected void selectDropdownOption(By locator,String option) {
        try{
            WebElement dropdown = waitShort.until(
                    ExpectedConditions.presenceOfElementLocated(
                            locator
                    )
            );

            Select select = new Select(dropdown);
            Thread.sleep(500);
            select.selectByVisibleText(option);

            logger.info("Selected option: {} from dropdown: {}", option, locator);
        }catch(Exception e){
            logger.error("Failed to select option: {}", option);
            Assert.fail("Error trying to select option: " + e.getMessage());
        }

    }

    protected String getElementText(By locator) {
        try{
            WebElement element = waitShort.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            locator
                    )
            );
            String elementText = element.getText();

            logger.info("Got text: {} From: {}", elementText, locator);
            return elementText;
        } catch(TimeoutException e){
            logger.error("Timeout waiting for element: {}", locator);
            Assert.fail("Timeout error: " + e.getMessage());
            return "";
        } catch (Exception e){
            logger.error("Unexpected error getting text: {}", e.getMessage());
            Assert.fail("Failed to get text from: "+ locator +" Error: "  + e.getMessage());
            return "";
        }
    }

    protected By getAccountBalanceLocator(String accountId) {
        try{
            return By.xpath("//table[@id='accountTable']//tr[td/a[text()='" + accountId + "']]//td[2]");
        }catch(Exception e){
            logger.error("Failed to get balance locator: {}", accountId);
            return null;
        }
    }

    protected boolean isElementVisible(By locator) {
        try{
            WebElement element = waitShort.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            locator
                    )
            );

            boolean result = element.isDisplayed();

            if (result) {
                logger.info("Element is displayed: {}",locator);
            } else{
                logger.info("Element is not displayed: {}",locator);
            }

            return result;
        } catch(TimeoutException e){
            logger.debug("Element not visible: {}", locator);
            return false;
        } catch(Exception e){
            logger.error("Unexpected error finding element: {}", e.getMessage());
            return false;
        }
    }

    protected boolean isCorrectTextDisplayed(By locator, String expectedText) {
        try {
            String actualText = getElementText(locator);

            if (actualText.equals(expectedText)) {
                logger.info("Text matches: '{}'", expectedText);
                return true;
            } else {
                logger.warn("Text mismatch. Expected: '{}', Actual: '{}'", expectedText, actualText);
                return false;
            }
        } catch(Exception e) {
            logger.error("Error checking text for: {}", locator);
            return false;
        }
    }

    protected boolean isBalanceCorrect(By locator, double amount) {
        try{
            String balance = getElementText(locator);
            double balanceDouble = Double.parseDouble(balance.substring(1));
            logger.info("Amount: {} Balance: {}", amount, balanceDouble );
            return balanceDouble == amount;
        }catch(Exception e){
            logger.error("Error checking balance for: {}", locator);
            return false;
        }
    }

    //Screenshots
    public void takeScreenshot(String testName){
        try{
            File screenshotDir = new File("screenshots");

            if(!screenshotDir.exists()){
                screenshotDir.mkdir();
            }

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = testName + "_" + timestamp + ".png";

            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
            File destFile = new File("screenshots/", fileName);

            FileUtils.copyFile(srcFile, destFile);
            logger.info("Screenshot saved to: {}", destFile.getAbsolutePath());
        } catch(Exception e){
            logger.error("Failed to take screenshot: {}", e.getMessage());
        }
    }
}