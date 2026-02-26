package org.example;

public class Config {
    //Url
    public static final String URL = "https://parabank.parasoft.com/";
    //Random username
    public static final String Username = "Vanko" + String.valueOf((int)(Math.random() * 9000)+1000);
    //Load test data
    public static final String LogoCaptionText = "Experience the difference";
    public static final String RegistrationPanelTitleText = "Signing up is easy!";
    public static final String RegistrationCompletionTitleText = "Welcome "+ Username;
    public static final String AccountOverviewPanelTitleText = "Accounts Overview";
    public static final String TransferFundsPanelTitleText = "Transfer Funds";
    public static final String TransferCompletionTitleText = "Transfer Complete!";
    public static final String NewAccountTitleText = "Open New Account";
    public static final String AccountOpenedTitleText = "Account Opened!";
    public static final String LoginPanelTitleText = "Customer Login";
    //Registration details
    public static final String FirstName = "Ivan";
    public static final String LastName = "Ivanov";
    public static final String Address = "Ulitsa st. 10";
    public static final String City = "Balchik";
    public static final String State = "Dbrich";
    public static final String ZipCode = "9611";
    public static final String Phone = "+359889075296";
    public static final String SocialSecurityNumber = "9902116767";
    public static final String Password = "MyPass123";
    //New Account data
    public static final String NewAccountType = "SAVINGS";
    public static final String TransferAmount = "100";
}