package org.example;

import org.openqa.selenium.By;

public class Locators {
    //-ParaBank Locators
    public static final By PageLogo = By.cssSelector("[class='logo']");
    public static final By PageLogoCaption = By.cssSelector("[class='caption']");
    public static final By PageServicesPanel = By.cssSelector("#rightPanel span");
    //-PageTitle texts
    public static final By PageTitleRegistration = By.cssSelector("#rightPanel > h1");
    public static final By PageTitleOverview = By.cssSelector("#showOverview > h1");
    public static final By PageTitleOpenResult = By.cssSelector("#openAccountResult > h1");
    public static final By PageTitleOpenAccount = By.cssSelector("#openAccountForm > h1");
    public static final By PageTitleTransfer = By.cssSelector("#showForm > h1");
    public static final By PageTitleTransferResult = By.cssSelector("#showResult > h1");
    //-Header Panel Button Locators
    public static final By HeaderPanelHomeButton = By.xpath("//a[text()='Home']");
    public static final By HeaderPanelAboutButton = By.xpath("//a[text()='About']");
    public static final By HeaderPanelContactButton = By.xpath("//a[text()='Contact']");
    //-Sidebar Account Services
    public static final By SideBarLoginPanelTitle = By.cssSelector("#leftPanel > h2");
    public static final By SideBarLoginPanel = By.id("loginPanel");
    public static final By SideBarLoginPanelUsernameField = By.cssSelector("[name='username']");
    public static final By SideBarLoginPanelPasswordField = By.cssSelector("[name='password']");
    public static final By SideBarLoginButton = By.cssSelector("[value='Log In']");
    public static final By SideBarAccountServicesPanel =  By.id("leftPanel");
    public static final By SideBarOpenNewAccountButton = By.xpath("//a[text()='Open New Account']");
    public static final By SideBarAccountsOverviewButton = By.xpath("//a[text()='Accounts Overview']");
    public static final By SideBarTransferFundsButton = By.xpath("//a[text()='Transfer Funds']");
    public static final By SideBarBillPayButton = By.xpath("//a[text()='Bill Pay']");
    public static final By SideBarFindTransactionsButton = By.xpath("//a[text()='Find Transactions']");
    public static final By SideBarUpdateContactInfoButton = By.xpath("//a[text()='Update Contact Info']");
    public static final By SideBarRequestLoanButton = By.xpath("//a[text()='Request Loan']");
    public static final By SideBarRegisterButton = By.xpath("//a[text()='Register']");
    public static final By SideBarLogoutButton = By.xpath("//a[text()='Log Out']");
    //-Registration Panel Locators
    public static final By RegistrationFirstNameField = By.id("customer.firstName");
    public static final By RegistrationLastNameField = By.id("customer.lastName");
    public static final By RegistrationAddressField = By.id("customer.address.street");
    public static final By RegistrationCityField = By.id("customer.address.city");
    public static final By RegistrationStateField = By.id("customer.address.state");
    public static final By RegistrationZipCodeField = By.id("customer.address.zipCode");
    public static final By RegistrationPhoneField = By.id("customer.phoneNumber");
    public static final By RegistrationSocialSecurityNumberField = By.id("customer.ssn");
    public static final By RegistrationUsernameField = By.id("customer.username");
    public static final By RegistrationPasswordField = By.id("customer.password");
    public static final By RegistrationConfirmPassField = By.id("repeatedPassword");
    public static final By RegistrationRegisterButton = By.cssSelector("[value='Register']");
    //-Account Panel Locators
    public static final By AccountOverviewPanel = By.id("overviewAccountsApp");
    public static final By AccountOverviewMainAccountId = By.cssSelector("#accountTable a");
    public static final By AccountOverviewTotalBalance = By.xpath("//table[@id='accountTable']//b[contains(text(),'$')]");
    //-Transfer Funds Panel Locators
    public static final By TransferFundsAmountField = By.id("amount");
    public static final By TransferFromIdField = By.id("fromAccountId");
    public static final By TransferToIdField = By.id("toAccountId");
    public static final By TransferButton = By.cssSelector("[value='Transfer']");
    //-New Account Locators
    public static final By NewAccountTypeField = By.id("type");
    public static final By NewAccountButton = By.cssSelector("[value='Open New Account']");
    public static final By NewAccountId = By.id("newAccountId");
    public static final By NewAccountInfoText = By.xpath("//*[@id='openAccountForm']/form/p[2]/b");
}