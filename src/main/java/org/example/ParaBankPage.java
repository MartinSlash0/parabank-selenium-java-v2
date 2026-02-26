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

    protected void checkInitialAccountBalance() {
        mainAccountId = getElementText(Locators.AccountOverviewMainAccountId);
        accountTotalBalance = getElementText(Locators.AccountOverviewTotalBalance);
        accountTotalBalance = accountTotalBalance.substring(1);
        accountTotalBalanceDouble =  Double.parseDouble(accountTotalBalance);
        logger.info("Account Balance is: {}", accountTotalBalance);
    }

    protected void openNewAccount(String type,String fromId) {
        selectDropdownOption(Locators.NewAccountTypeField, type);
        selectDropdownOption(Locators.TransferFromIdField, fromId);
        clickElement(Locators.NewAccountButton);
        String text = getElementText(Locators.NewAccountInfoText);
        double minAmountNewAccount = Double.parseDouble(text.substring(text.indexOf('$')+1, text.indexOf(" must")));
        newAccountId = getElementText(Locators.NewAccountId);
        mainAccountBalanceDouble = accountTotalBalanceDouble - minAmountNewAccount;
        newAccountBalanceDouble = minAmountNewAccount;
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