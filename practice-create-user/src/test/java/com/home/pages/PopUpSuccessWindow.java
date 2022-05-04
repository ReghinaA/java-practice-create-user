package com.home.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PopUpSuccessWindow {

    @FindBy(tagName = "h2")
    public WebElement successMessage;

    @FindBy(id = "layer_cart_product_title")
    public WebElement productName;

    @FindBy(xpath = "//span[@class='ajax_block_cart_total']")
    public WebElement totalPrice;

    @FindBy(css = ".button-container a")
    public WebElement checkOutButton;
}
