package com.home.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object class with the description of the elements
 */
public class LoginPage {

    @FindBy(id = "email_create") // Email address field
    public WebElement emailField;

    @FindBy(id = "SubmitCreate") // Create an Account button
    public WebElement createAccountButton;
}
