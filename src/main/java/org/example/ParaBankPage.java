package org.example;

import org.openqa.selenium.WebDriver;

public class ParaBankPage extends BasePage {

    public ParaBankPage(WebDriver driver) {
        super(driver);
    }

    String accountTotalBalance;
    double accountTotalBalanceDouble;
    String mainAccountBalance;
    double mainAccountBalanceDouble;
    String newAccountBalance;
    double newAccountBalanceDouble;
    String mainAccountId;
    String newAccountId;


    protected void openRegistrationPanel() {
        clickElement(Locators.SideBarRegisterButton);
    }

    protected void openAccountOverviewPanel() {
        clickElement(Locators.SideBarAccountsOverviewButton);
    }

    protected void openNewAccountPanel() {
        clickElement(Locators.SideBarOpenNewAccountButton);
    }

    protected void openTransferFundsPanel() {
        clickElement(Locators.SideBarTransferFundsButton);
    }

    protected void fillPersonalInfo(
            String firstName,
            String lastName,
            String address,
            String city,
            String state,
            String zipCode,
            String phone,
            String ssn
    ) {
        fillField(Locators.RegistrationFirstNameField, firstName);
        fillField(Locators.RegistrationLastNameField,lastName );
        fillField(Locators.RegistrationAddressField, address);
        fillField(Locators.RegistrationCityField, city);
        fillField(Locators.RegistrationStateField, state);
        fillField(Locators.RegistrationZipCodeField, zipCode);
        fillField(Locators.RegistrationPhoneField, phone);
        fillField(Locators.RegistrationSocialSecurityNumberField, ssn);
    }

    protected void fillAccountCredentials(
            String username,
            String password
    ) {
        fillField(Locators.RegistrationUsernameField, username);
        fillField(Locators.RegistrationPasswordField, password);
        fillField(Locators.RegistrationConfirmPassField, password);
    }

    protected void submitRegistrationInfo() {
        clickElement(Locators.RegistrationRegisterButton);
    }

    protected void login(String username, String password) {
        fillField(Locators.SideBarLoginPanelUsernameField, username);
        fillField(Locators.SideBarLoginPanelPasswordField, password);
        clickElement(Locators.SideBarLoginButton);
    }

    protected void logout() {
        clickElement(Locators.SideBarLogoutButton);
    }

    protected void checkInitialAccountId() {
        mainAccountId = getElementText(Locators.MainAccountId);
        logger.info("Main Account ID is: {}", mainAccountId);
    }

    protected void openNewAccount(String type,String fromId) {
        selectDropdownOption(Locators.NewAccountTypeField, type);
        selectDropdownOption(Locators.TransferFromIdField, fromId);
        clickElement(Locators.NewAccountButton);
    }

    protected void getAccountsInfo() {
        mainAccountId = getElementText(Locators.MainAccountId);
        mainAccountBalance = getElementText(Locators.MainAccountBalance);
        mainAccountBalance = mainAccountBalance.substring(1);
        mainAccountBalanceDouble =  Double.parseDouble(mainAccountBalance);
        logger.info("Main Account Balance is: {}", mainAccountBalance);
        newAccountId = getElementText(Locators.NewAccountId);
        newAccountBalance = getElementText(Locators.NewAccountBalance);
        newAccountBalance = newAccountBalance.substring(1);
        newAccountBalanceDouble =  Double.parseDouble(newAccountBalance);
        logger.info("New Account Balance is: {}", newAccountBalance);
        accountTotalBalance = getElementText(Locators.AccountOverviewTotalBalance);
        accountTotalBalance = accountTotalBalance.substring(1);
        accountTotalBalanceDouble =  Double.parseDouble(accountTotalBalance);

    }

    protected void transferFunds(String fromId, String toId, String amount) {
        fillField(Locators.TransferFundsAmountField, amount);
        selectDropdownOption(Locators.TransferFromIdField, fromId);
        selectDropdownOption(Locators.TransferToIdField, toId);
        clickElement(Locators.TransferButton);
        mainAccountBalanceDouble = mainAccountBalanceDouble - Double.parseDouble(amount);
        newAccountBalanceDouble = newAccountBalanceDouble + Double.parseDouble(amount);
    }
}