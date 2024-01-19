package StepDefinition;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;



public class MyStepdefs extends SetUp.base {

    WebDriver driver = getDriver();
    Actions action = new Actions(driver);
    DataTable loginCreds;


    public Wait<WebDriver> wait = new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(45))
            .pollingEvery(Duration.ofSeconds(3))
            .ignoring(NoSuchElementException.class, ElementClickInterceptedException.class);

//    Locator Objects




    // Amazon test Page Objects

    By searchBox = By.cssSelector("#twotabsearchtextbox");
    By searchButton = By.id("nav-search-submit-button");
    By searchResultsLocator = By.xpath("//div[@class='a-section']/div/div[1]");
    By selectedProductPrice = By.cssSelector("#corePriceDisplay_desktop_feature_div > div.a-section.a-spacing-none.aok-align-center > span.a-price.aok-align-center.reinventPricePriceToPayMargin.priceToPay > span:nth-child(2)");
    By addToCartButton = By.cssSelector("#add-to-cart-button");
    By productAddedSuccessMessage = By.xpath("//span[contains(text(), 'Added to Cart')]");
    By cartIcon = By.cssSelector("#nav-cart-count-container");
    By cartProductPriceEl = By.cssSelector(".sc-badge-price-to-pay");
    By cartProductSubTotalPrice = By.cssSelector("#sc-subtotal-amount-buybox>span");

    String productPrice;
    //    Step Definitions
    @Given("That user is on amazon website")
    public void thatUserIsOnAmazonWebsite() throws InterruptedException {
        driver.get("https://www.amazon.com/");
        driver.manage().window().maximize();
    }

    @When("user searches for product {string}")
    public void userSearchesForProductMonitor(String productName) {
        driver.findElement(searchBox).sendKeys(productName);
        driver.findElement(searchButton).click();

    }

    @When("user selects first preferred product")
    public void userSelectsFirstPreferredProduct() throws InterruptedException {
        Thread.sleep(4000);
        List<WebElement> searchResults = driver.findElements(searchResultsLocator);
        if(!searchResults.isEmpty()){
            WebElement desiredElement = searchResults.get(0);
            desiredElement.click();
        } else {System.out.println("No matching record Found");}
    }

    @When("user selects second preferred product")
    public void userSelectsSecondPreferredProduct() throws InterruptedException {
        Thread.sleep(4000);
        List<WebElement> searchResults = driver.findElements(searchResultsLocator);
        if(!searchResults.isEmpty()){
            WebElement desiredElement = searchResults.get(1);
            desiredElement.click();
        } else {System.out.println("No matching record Found");}
    }

    @Then("System displays product details")
    public void systemDisplaysProductDetails() {
        String productPriceRaw = driver.findElement(selectedProductPrice).getText();
        productPrice = productPriceRaw.replace("\n", ".");


    }

    @When("user adds product to cart")
    public void userAddsProductToCart() {
        driver.findElement(addToCartButton).click();
    }

    @Then("Product is added to cart successfully")
    public void productIsAddedToCartSuccessfully() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(productAddedSuccessMessage).isDisplayed();
    }

    @When("user navigates to cart")
    public void userNavigatesToCart() {
        driver.findElement(cartIcon).click();
    }

    @Then("system displays cart details with product price equal to product details price")
    public void systemDisplaysCartDetailsWithProductPriceEqualToProductDetailsPrice() throws InterruptedException {
        Thread.sleep(3000);
        String cartProductPrice = driver.findElement(cartProductPriceEl).getText();
        Assert.assertEquals(cartProductPrice,productPrice);
    }

    @And("system displays cart details with product subtotal price equal to product details price")
    public void systemDisplaysCartDetailsWithProductSubtotalPriceEqualToProductDetailsPrice() {

        WebElement subTotalPriceEl = driver.findElement(cartProductSubTotalPrice);
        String subTotalPrice = subTotalPriceEl.getText();
        Assert.assertEquals(subTotalPrice,productPrice);

    }
}
