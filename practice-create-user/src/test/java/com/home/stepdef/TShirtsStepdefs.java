package com.home.stepdef;

import com.home.pages.CreateAccountPage;
import com.home.pages.LoginPage;
import com.home.pages.PopUpSuccessWindow;
import com.home.pages.TShirtsShoppingPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;


public class TShirtsStepdefs {
    /**
     * Logger
     */
    final private static Logger LOG = LoggerFactory.getLogger(TShirtsStepdefs.class);

    public static WebDriver webDriver;

    @Given("T-shirts shopping page opened and t-shirt model is {string}")
    public void verifyTshirtsDetailPageOpened(String expectedTShirtName) {
        LOG.info("Verification that page title is '{}'", expectedTShirtName);

        final TShirtsShoppingPage tshirtsShoppingPage = new TShirtsShoppingPage();
        PageFactory.initElements(webDriver, tshirtsShoppingPage);

        /**
         * Softly assertion example
         */

        final SoftAssertions softly = new SoftAssertions();
        softly.assertThat(tshirtsShoppingPage.tShirtName.getText())
                .as("T-Shirt name should be " + expectedTShirtName)
                .isEqualTo(expectedTShirtName);
        softly.assertThat(webDriver.getCurrentUrl())
                .as("URL should end with " + TShirtsShoppingPage.PATH)
                .endsWith(TShirtsShoppingPage.PATH);
        softly.assertThat(webDriver.getTitle())
                .as("Title name should be" + TShirtsShoppingPage.TITLE)
                .endsWith(TShirtsShoppingPage.TITLE);
        softly.assertThat(tshirtsShoppingPage.tShirtsImageLarge.isDisplayed())
                .as("Check T-shirt large image")
                .isTrue();

        softly.assertAll();
        LOG.info("Verification is successful");
    }

    @Given("I create an account using my e-mail")
    public void iCreateAnAccountUsingMyEMail() {

        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();

        webDriver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account"); // Open login page and create an account

        LoginPage loginPage = new LoginPage();
        PageFactory.initElements(webDriver, loginPage);

        /**
         * Time stamp
         */
        loginPage.emailField.sendKeys(new Date().getTime() + "moldy.mozzarella@it.com");
        loginPage.createAccountButton.click();
    }

    @And("I Fill out Your Personal Information fields")
    public void iFillOutYourPersonalInformationFields() {
        CreateAccountPage createAccountPage = new CreateAccountPage();
        PageFactory.initElements(webDriver, createAccountPage);

        Wait<WebDriver> waitWorker = new WebDriverWait(webDriver, 10);
        waitWorker.until(ExpectedConditions.visibilityOf(createAccountPage.createAccountHeading));

        waitWorker.until(ExpectedConditions.visibilityOf(createAccountPage.firstName));
        createAccountPage.firstName.sendKeys("Geronimo");

        waitWorker.until(ExpectedConditions.visibilityOf(createAccountPage.lastName));
        createAccountPage.lastName.sendKeys("Stilton");

        waitWorker.until(ExpectedConditions.visibilityOf(createAccountPage.passwordField));
        createAccountPage.passwordField.sendKeys("test1");

        // 'Your Address' fields
        waitWorker.until(ExpectedConditions.visibilityOf(createAccountPage.addressField));
        createAccountPage.addressField.sendKeys("876 Maple Dr");

        waitWorker.until(ExpectedConditions.visibilityOf(createAccountPage.cityField));
        createAccountPage.cityField.sendKeys("Pleasantville");


        // stateField with select tag is invisible for the user
        // we can find it's parent `<div>` and check it's visibility
        // findElement(By.xpath("./..")) - this is how we will find the parent using Xpath
        WebElement parent = createAccountPage.stateField.findElement(By.xpath("./.."));
        waitWorker.until(ExpectedConditions.visibilityOf(parent));

        Select dropdownState = new Select(createAccountPage.stateField);
        dropdownState.selectByVisibleText("Maryland");

        waitWorker.until(ExpectedConditions.visibilityOf(createAccountPage.zipCode));
        createAccountPage.zipCode.sendKeys("26613");

        waitWorker.until(ExpectedConditions.visibilityOf(createAccountPage.mobilePhone));
        createAccountPage.mobilePhone.sendKeys("202-683-7807");

        waitWorker.until(ExpectedConditions.visibilityOf(createAccountPage.registerButton));
        createAccountPage.registerButton.click();
    }

    // Navigating to T-shirts page from My Account Page
    @Given("I navigate to T-shirts page")
    public void iNavigateToTShirtsPage() throws InterruptedException {

        TShirtsShoppingPage tshirtsShoppingPage = new TShirtsShoppingPage();
        PageFactory.initElements(webDriver, tshirtsShoppingPage);

        Wait<WebDriver> waitWorker = new WebDriverWait(webDriver, 10);
        waitWorker.until(ExpectedConditions.visibilityOf(tshirtsShoppingPage.tShirtsMenu));
        tshirtsShoppingPage.tShirtsMenu.click();
    }

    @And("I select Faded Short Sleeve T-shirts")
    public void iSelectFadedShortSleeveTShirts() {
        TShirtsShoppingPage tshirtsShoppingPage = new TShirtsShoppingPage();
        PageFactory.initElements(webDriver, tshirtsShoppingPage);

        Wait<WebDriver> waitWorker = new WebDriverWait(webDriver, 10);
        waitWorker.until(ExpectedConditions.visibilityOf(tshirtsShoppingPage.tShirtsImage));

        Actions actions = new Actions(webDriver);
        actions.moveToElement(tshirtsShoppingPage.tShirtsImage).click().perform();
    }

    @And("I select T-shirt quantity, size, color and click Add to cart")
    public void iSelectTShirtQuantitySizeColorAndClickAddToCart() {

        final TShirtsShoppingPage tshirtsShoppingPage = new TShirtsShoppingPage();
        PageFactory.initElements(webDriver, tshirtsShoppingPage);

        Select dropdownSize = new Select(tshirtsShoppingPage.tShirtsSizeDropDown);
        dropdownSize.selectByVisibleText("M");

        tshirtsShoppingPage.tShirtsColorBlue.click();
        tshirtsShoppingPage.addToCart.click();
    }

    // Pop up confirmation window part
    @When("Pop up confirmation window {string} comes up")
    public void popUpConfirmationWindowComesUp(String expectedSuccessMessage) {
        LOG.info("Verification that success message appears in pop up window '{}'", expectedSuccessMessage);

        final PopUpSuccessWindow popUpSuccessWindow = new PopUpSuccessWindow();
        PageFactory.initElements(webDriver, popUpSuccessWindow);

        Wait<WebDriver> waitWorker = new WebDriverWait(webDriver, 10);
        waitWorker.until(ExpectedConditions.visibilityOf(popUpSuccessWindow.successMessage));

        final SoftAssertions softly = new SoftAssertions();

        softly.assertThat(popUpSuccessWindow.successMessage.getText())
                .as("Success message should be " + expectedSuccessMessage)
                .isEqualTo(expectedSuccessMessage);
    }

    @Then("I check product name {string} and Total")
    public void iCheckProductNameAndTotal(String expectedProductName) {

        LOG.info("Verification that Product name appears in pop up window '{}'", expectedProductName);
        LOG.info("Verification that Total Price appears in pop up window");

        final PopUpSuccessWindow popUpSuccessWindow = new PopUpSuccessWindow();
        PageFactory.initElements(webDriver, popUpSuccessWindow);

        final SoftAssertions softly = new SoftAssertions();

        softly.assertThat(popUpSuccessWindow.productName.getText())
                .as("T-Shirt name should be " + expectedProductName)
                .isEqualTo(expectedProductName);

        softly.assertThat(popUpSuccessWindow.totalPrice.isDisplayed())
                .as("Check Total")
                .isTrue();

        softly.assertAll();
        LOG.info("Verification is successful");
    }

    @And("I select Proceed to checkout")
    public void iSelectProceedToCheckout() {
        final PopUpSuccessWindow popUpSuccessWindow = new PopUpSuccessWindow();
        PageFactory.initElements(webDriver, popUpSuccessWindow);

        popUpSuccessWindow.checkOutButton.click();
    }
}