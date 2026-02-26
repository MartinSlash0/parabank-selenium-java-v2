package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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

    @Test
    public void moneyFlowTest() {
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
        paraBankPage.checkInitialAccountBalance();
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
        paraBankPage.openTransferFundsPanel();
        Assert.assertTrue(
                paraBankPage.isCorrectTextDisplayed(
                        Locators.PageTitleTransfer,
                        Config.TransferFundsPanelTitleText
                ),  "Failed to open transfer panel!"
        );
        paraBankPage.transferFunds(paraBankPage.mainAccountId, paraBankPage.newAccountId, Config.TransferAmount);
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
    public void afterMethod() {
        try{
            if (driver != null) {
                driver.quit();
            }
        } catch (Exception e) {
            logger.error("Error while quitting driver: {}",e.getMessage());
        }finally{
            driver = null;
        }
    }
}