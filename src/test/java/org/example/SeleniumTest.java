package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.ITestResult;

public class SeleniumTest {

    protected static final Logger logger = LoggerFactory.getLogger(SeleniumTest.class);

    WebDriver driver;
    ParaBankPage paraBankPage;

    @BeforeMethod
    public void beforeMethod() {
        try {
            driver = new FirefoxDriver();
            paraBankPage = new ParaBankPage(driver);
            driver.manage().window().maximize();
            logger.info("Setup completed!");
        } catch (Exception e) {
            logger.error("Setup failed due to unexpected error: {}", e.getMessage());
            throw e;
        }
    }

    @Test(priority = 1)
    public void registerNewUserTest() {
        paraBankPage.goToUrl(Config.URL);
        Assert.assertTrue(
            paraBankPage.isElementVisible(
                Locators.PageLogo
            )&&
            paraBankPage.isCorrectTextDisplayed(
                Locators.PageLogoCaption,
                Config.LogoCaptionText
            )&&
            paraBankPage.isElementVisible(
                Locators.SideBarLoginPanel
            ), "Page not loaded!"
        );
        paraBankPage.openRegistrationPanel();
        Assert.assertTrue(
            paraBankPage.isCorrectTextDisplayed(
                Locators.PageTitleRegistration,
                Config.RegistrationPanelTitleText
            ), "Registration panel not loaded!"
        );
        paraBankPage.fillPersonalInfo(
            Config.FirstName,
            Config.LastName,
            Config.Address,
            Config.City,
            Config.State,
            Config.ZipCode,
            Config.Phone,
            Config.SocialSecurityNumber
        );
        paraBankPage.fillAccountCredentials(
            Config.Username,
            Config.Password
        );
        paraBankPage.submitRegistrationInfo();
        Assert.assertTrue(
            paraBankPage.isCorrectTextDisplayed(
                Locators.PageTitleRegistration,
                Config.RegistrationCompletionTitleText
            )&&
            paraBankPage.isElementVisible(
                Locators.SideBarAccountServicesPanel
            ), "Registration failed"
        );
        paraBankPage.openAccountOverviewPanel();
        Assert.assertTrue(
            paraBankPage.isCorrectTextDisplayed(
                Locators.PageTitleOverview,
                Config.AccountOverviewPanelTitleText
            )&&
            paraBankPage.isElementVisible(
                Locators.AccountOverviewPanel
            ), "Account Overview panel not loaded!"
        );
        paraBankPage.logout();
        Assert.assertTrue(
            paraBankPage.isElementVisible(
                Locators.PageLogo
            )&&
            paraBankPage.isCorrectTextDisplayed(
                Locators.PageLogoCaption,
                Config.LogoCaptionText
            )&&
            paraBankPage.isElementVisible(
                Locators.SideBarLoginPanel
            ), "Page not loaded!"
        );
    }

    @Test(priority = 2)
    public void openNewAccountTest() {
        paraBankPage.goToUrl(Config.URL);
        Assert.assertTrue(
            paraBankPage.isElementVisible(
                Locators.PageLogo
            )&&
            paraBankPage.isCorrectTextDisplayed(
                Locators.PageLogoCaption,
                Config.LogoCaptionText
            )&&
            paraBankPage.isElementVisible(
                Locators.SideBarLoginPanel
            ), "Page not loaded!"
        );
        paraBankPage.login(
            Config.Username,
            Config.Password
        );
        Assert.assertTrue(
            paraBankPage.isCorrectTextDisplayed(
                    Locators.PageTitleOverview,
                    Config.AccountOverviewPanelTitleText
                )&&
                paraBankPage.isElementVisible(
                    Locators.AccountOverviewPanel
                ), "Account Overview panel not loaded!"
        );
        paraBankPage.openAccountOverviewPanel();
        Assert.assertTrue(
            paraBankPage.isCorrectTextDisplayed(
                Locators.PageTitleOverview,
                Config.AccountOverviewPanelTitleText
            )&&
            paraBankPage.isElementVisible(
                Locators.AccountOverviewPanel
            ), "Account Overview panel not loaded!"
        );
        paraBankPage.checkInitialAccountId();
        paraBankPage.openNewAccountPanel();
        Assert.assertTrue(
            paraBankPage.isCorrectTextDisplayed(
                Locators.PageTitleOpenAccount,
                Config.NewAccountTitleText
            ),  "New Account panel not loaded!"
        );
        paraBankPage.openNewAccount(Config.NewAccountType, paraBankPage.mainAccountId);
        Assert.assertTrue(
            paraBankPage.isCorrectTextDisplayed(
                Locators.PageTitleOpenResult,
                Config.AccountOpenedTitleText
            ),  "Failed to open new account!"
        );
        paraBankPage.logout();
    }

    @Test(priority = 3)
    public void transferTest() {
        paraBankPage.goToUrl(Config.URL);
        Assert.assertTrue(
            paraBankPage.isElementVisible(
                Locators.PageLogo
            )&&
            paraBankPage.isCorrectTextDisplayed(
                Locators.PageLogoCaption,
                Config.LogoCaptionText
            )&&
            paraBankPage.isElementVisible(
                Locators.SideBarLoginPanel
            ), "Page not loaded!"
        );
        paraBankPage.login(
            Config.Username,
            Config.Password
        );
        Assert.assertTrue(
            paraBankPage.isCorrectTextDisplayed(
                Locators.PageTitleOverview,
                Config.AccountOverviewPanelTitleText
            )&&
            paraBankPage.isElementVisible(
                Locators.AccountOverviewPanel
            ), "Account Overview panel not loaded!"
        );
        paraBankPage.openAccountOverviewPanel();
        Assert.assertTrue(
            paraBankPage.isCorrectTextDisplayed(
                Locators.PageTitleOverview,
                Config.AccountOverviewPanelTitleText
            )&&
            paraBankPage.isElementVisible(
                Locators.AccountOverviewPanel
            ), "Account Overview panel not loaded!"
        );
        paraBankPage.getAccountsInfo();
        paraBankPage.openTransferFundsPanel();
        Assert.assertTrue(
            paraBankPage.isCorrectTextDisplayed(
                Locators.PageTitleTransfer,
                Config.TransferFundsPanelTitleText
            ),  "Failed to open transfer panel!"
        );
        paraBankPage.transferFunds(
                paraBankPage.mainAccountId,
                paraBankPage.newAccountId,
                Config.TransferAmount
        );
        Assert.assertTrue(
            paraBankPage.isCorrectTextDisplayed(
                Locators.PageTitleTransferResult,
                Config.TransferCompletionTitleText
            ),  "Transfer failed!"
        );
        paraBankPage.openAccountOverviewPanel();
        Assert.assertTrue(
            paraBankPage.isBalanceCorrect(
                Locators.AccountOverviewTotalBalance,
                paraBankPage.accountTotalBalanceDouble
            )&&
            paraBankPage.isBalanceCorrect(
                paraBankPage.getAccountBalanceLocator(paraBankPage.mainAccountId),
                paraBankPage.mainAccountBalanceDouble
            )&&
            paraBankPage.isBalanceCorrect(
                paraBankPage.getAccountBalanceLocator(paraBankPage.newAccountId),
                paraBankPage.newAccountBalanceDouble
            ), "Incorrect balance is displayed!"
        );
        paraBankPage.logout();
        Assert.assertTrue(
                paraBankPage.isElementVisible(
                    Locators.SideBarLoginPanel
                )&&
                paraBankPage.isCorrectTextDisplayed(
                    Locators.SideBarLoginPanelTitle,
                    Config.LoginPanelTitleText
                )&&
                paraBankPage.isElementVisible(
                    Locators.PageServicesPanel
                )
        );
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        try {
            if (result.getStatus() == ITestResult.FAILURE) {
                String testName = result.getMethod().getMethodName();
                paraBankPage.takeScreenshot(testName + "_FAILED");
                logger.error("Test failed: {}. Screenshot taken.", testName);
            }

            if (driver != null) {
                driver.quit();
            }
        } catch (Exception e) {
            logger.error("Error in afterMethod: {}", e.getMessage());
        } finally {
            driver = null;
        }
    }
}