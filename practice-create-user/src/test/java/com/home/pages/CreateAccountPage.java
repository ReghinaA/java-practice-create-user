package com.home.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object class with the description of the elements
 */

public class CreateAccountPage {

    @FindBy(className = "page-heading") // 'Create an Account' heading
    public WebElement createAccountHeading;

    @FindBy(id = "customer_firstname") // First Name
    public WebElement firstName;

    @FindBy(id = "customer_lastname") // Last Name
    public WebElement lastName;

    @FindBy(id = "passwd") // Email address field
    public WebElement passwordField;

    // Your address
    @FindBy(id = "address1") // Street address
    public WebElement addressField;

    @FindBy(id = "city")
    public WebElement cityField;

    @FindBy(id = "id_state")
    public WebElement stateField;

    @FindBy(id = "postcode")
    public WebElement zipCode;

    @FindBy(id = "phone_mobile")
    public WebElement mobilePhone;

    @FindBy(id = "submitAccount")
    public WebElement registerButton;
}
