package com.home.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object class with the description of the elements
 */
public class TShirtsShoppingPage {

    final public static String PATH = "controller=product";
    final public static String TITLE = "Faded Short Sleeve T-shirts - My Store";

    /**
     * T-shirts Menu
     */
    @FindBy(xpath = "(//a[text()='T-shirts'])[2]")
    public WebElement tShirtsMenu;

    /**
     * T-shirts image
     */
    @FindBy(className = "product_img_link")
    public WebElement tShirtsImage;

    /**
     * T-shirts model name
     */
    @FindBy(css = "div h1[itemprop=name]")
    public WebElement tShirtName;

    @FindBy(id = "bigpic")
    public WebElement tShirtsImageLarge;

    /**
     * T-shirt size
     */
    @FindBy(id = "group_1")
    public WebElement tShirtsSizeDropDown;

    /**
     * T-shirt color
     */
    @FindBy(id = "color_14")
    public WebElement tShirtsColorBlue;

    @FindBy(name = "Submit")
    public WebElement addToCart;
}