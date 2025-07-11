package Test;

import Pages.CartCheckout;
import Pages.CartEmpty;
import Pages.HomePage;
import Pages.ValidateServicibility;
import Pages.VerifyPDPComponents;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;

//import static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.header;

public class BaseTest {
    public static WebDriver driver;
    HomePage homePage;
    CartCheckout cartCheckout;
    ValidateServicibility validateServicibility;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() {


        // Set up ChromeDriver with options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--incognito");

        // Initialize WebDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().deleteAllCookies();

        // Initialize WebDriverWait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to the website
        driver.get("https://cultstore.com/");

        // Initialize page objects
        homePage = new HomePage(driver);
        cartCheckout = new CartCheckout(driver);
       /* WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://cultstore.com/");
        homePage = new HomePage(driver);
        cartCheckout = new CartCheckout(driver);*/
    }

    @Test(priority = 1)
    void validateTitles_Products() {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("New Arrivals")));
        homePage.clickNewArrivals();
        WebElement titleElement = driver.findElement(By.xpath("(//h1[normalize-space()='New Arrivals'])[1]"));
        String actualTitle = titleElement.getText();
        Assert.assertEquals(actualTitle, "New Arrivals", "Titledcd does not match");


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Men")));
        homePage.clickMen();
        titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//h1[normalize-space()=\"Men's Wear\"])[1]")
        ));
        actualTitle = titleElement.getText();
        Assert.assertEquals(actualTitle, "Men's Wear", "Title does not match");

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Women")));
        homePage.clickWomen();
        titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//h1[normalize-space()=\"Women's Wear\"])[1]")
        ));
        actualTitle = titleElement.getText();
        Assert.assertEquals(actualTitle, "Women's Wear", "Title does not match");

        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Apparel")));
        homePage.clickApparel();
        titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[contains(@class, 'hometitle') and contains(text(), 'Apparel')]")
        ));
        Assert.assertEquals(titleElement.getText(), "Apparel", "Title does not match");

        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Footwear")));
        homePage.clickFootwear();
        titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[contains(@class, 'hometitle') and contains(text(), 'Footwear')]")
        ));
        Assert.assertEquals(titleElement.getText(), "Footwear", "Title does not match");

        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Gym Equipment")));
        homePage.clickGymEquipment();
        titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[contains(@class, 'hometitle') and contains(text(), 'Treadmills, Exercise Bikes and More')]")
        ));
        Assert.assertEquals(titleElement.getText(), "Treadmills, Exercise Bikes and More", "Title does not match");

        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Massagers")));
        homePage.clickMassagers();
        titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[contains(@class, 'hometitle') and contains(text(), 'Recovery')]")
        ));
        Assert.assertEquals(titleElement.getText(), "Recovery", "Title does not match");

        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Accessories")));
        homePage.clickAccessories();
       /* titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[contains(@class, 'hometitle') and contains(text(), 'Accessories')]")
        ));
        Assert.assertEquals(titleElement.getText(), "Accessories", "Title does not match");*/

        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Cycles")));
        homePage.clickCycles();
        titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[contains(@class, 'hometitle') and contains(text(), 'Cycles')]")
        ));
        Assert.assertEquals(titleElement.getText(), "Cycles", "Title does not match");

        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

      /*  wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Smart Watch")));
        homePage.clickSmartWatch();
        titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//h1[normalize-space()='Smartwatch'])[1]")
        ));
        Assert.assertEquals(titleElement.getText(), "Smartwatch", "Title does not match");*/

        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        driver.findElement(By.xpath("(//a[@class='navigation__link' and normalize-space()='Shop by Activity'])[2]")).click();
        titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".hometitle.h1.spaced-row.align-center")));
        Assert.assertEquals(titleElement.getText().trim(), "Shop by Activity", "Title does not match");

        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Store Locator")));
        homePage.clickStoreLocator();
        titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//h1[normalize-space()='Cult exclusive stores'])[1]")
        ));
        Assert.assertEquals(titleElement.getText(), "Cult exclusive stores", "Title does not match");
    }

    @Test(priority = 2)
    public void filterByCultBrand() {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Gym Equipment")));
        cartCheckout.clickGymEquipment();

        WebElement filterElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[contains(text(),'Filter')])[1]")));
        Actions actions = new Actions(driver);
        actions.doubleClick(filterElement).perform();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[normalize-space()='Brand'])[1]")));
        cartCheckout.expandBrandSection();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[normalize-space()='cult'])[1]")));
        cartCheckout.clickCultCheckbox(); // If I add the delay after this line it will fail to find the element in the next step, so I removed it.
        //try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[normalize-space()='Treadmills'])[1]")));
        cartCheckout.clicktreadmillsButton();

        // select available T-shirt on PLP
        cartCheckout.selectAvailabletreadmill();
        try { Thread.sleep(5000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        // add to cart
        cartCheckout.clickAddToCart();
        // Wait for the cart icon to be clickable
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        // Click on the cart icon
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        cartCheckout.clickCart();
        // Click on the checkout button
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        cartCheckout.clickCheckoutButton();

        // Verify the checkout page title "Login to checkout"
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        Assert.assertTrue(cartCheckout.isLoginToCheckoutDisplayed(), "'Login to checkout' message is not displayed!");

        // Enter phone number and continue
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        cartCheckout.enterPhoneAndContinue("8830280256");

        // Verify the OTP title
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        Assert.assertTrue(cartCheckout.isEnterOtpDisplayed(), "'Enter OTP' screen is not displayed!");



        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    @Test(priority = 3)
    public void validateCartEmpty() {
        CartEmpty cartEmpty = new CartEmpty(driver);
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        cartEmpty.clickCartIcon();
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        cartEmpty.verifyEmptyCartText();
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        cartEmpty.clickBackToHomepage();
    }

    @Test(priority = 4)
    public void checkServiceability() {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Gym Equipment")));
        ValidateServicibility ValidateServicibility = new ValidateServicibility(driver);
        ValidateServicibility.clickGymEquipment();
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }


        WebElement filterElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[contains(text(),'Filter')])[1]")));
        Actions actions = new Actions(driver);
        actions.doubleClick(filterElement).perform();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[normalize-space()='Category'])[1]")));
        ValidateServicibility.expandCategorySection();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='filter-group__item__text'][normalize-space()='Treadmill'])[1]")));
        ValidateServicibility.clickTreadmillsCheckbox();
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[normalize-space()='Brand'])[1]")));
        ValidateServicibility.expandBrandSection();
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }


        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[normalize-space()='cult'])[1]")));
        ValidateServicibility.clickCultCheckbox();
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        ValidateServicibility.selectAvailabletreadmill();
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }


       ValidateServicibility.enterPincode("444607");
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        ValidateServicibility.clickCheckButton();
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }


        Assert.assertTrue(ValidateServicibility.isServiceableMessageDisplayed(), "delivery-text");
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        Assert.assertTrue(ValidateServicibility.isExchangeMessageDisplayed(), "Easy 10 days exchange available");
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        Assert.assertTrue(ValidateServicibility.isPayondeliveryMessageDisplayed(), "COD eligibility shown at checkout");
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        Assert.assertTrue(ValidateServicibility.isReturnMessageDisplayed(), "No Return Available");
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

      /*  Assert.assertTrue(ValidateServicibility.isWarrantyMessageDisplayed(), "Warranty Included");
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }*/

        ValidateServicibility.enterPincodeunservicable("372821");
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        ValidateServicibility.clickCheckButton();
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt();}

        Assert.assertTrue(ValidateServicibility.isUnserviceableMessageDisplayed(), "Unserviceable message is not displayed");
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }


    }

    @Test(priority = 5)
    public void verifyPDPComponents() {

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Massagers")));
        Pages.VerifyPDPComponents verifyPDPComponents = new Pages.VerifyPDPComponents(driver);
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        verifyPDPComponents.clickMassagerLink();
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        verifyPDPComponents.clickMassagchair();
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        verifyPDPComponents.clickBrandDropdown();
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        verifyPDPComponents.clickCultcheckbox();
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        verifyPDPComponents.clickMassagechairproduct();
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        Assert.assertTrue(
                driver.findElement(By.xpath("(//span[@class='badge-text'])[1]")).isDisplayed(),
                "534+ people bought this in last 30 days is not displayed"
        );
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        verifyPDPComponents.clickemiinfoicon();
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        // Pop up code
        VerifyPDPComponents pdp = new VerifyPDPComponents(driver);
        Assert.assertTrue(pdp.verifypopup(), "Pop-up text does not match expected value.");
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        verifyPDPComponents.closePopup();
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        verifyPDPComponents.clickBuyonemiButton();
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        verifyPDPComponents.closeCart();
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        Assert.assertTrue(
                verifyPDPComponents.isOfferTextDisplayed("Additional offers for you"),
                "Offer text is not displayed as expected");
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        verifyPDPComponents.clickPlusButton();
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }



        Assert.assertTrue(verifyPDPComponents.isOfferForyou(), "Offer for you is not displayed as expected");
        try { Thread.sleep(6000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        Assert.assertTrue(verifyPDPComponents.isBestOfferTextDisplayed("Best Offer - Apply at Checkout"),
                "Offer text is not displayed as expected");
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

    }

    @Test(priority = 6)
    public void searchProduct() {
        Pages.SearchProduct searchProduct = new Pages.SearchProduct(); // Page object for SearchProduct
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        // Part one to search for the bottles
        searchProduct.clickSearchBoxIcon();
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        searchProduct.closeSearchBox();
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        searchProduct.clickSearchBoxIcon();
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        searchProduct.enterSearchText("bottles");
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("View all search results")));
        Assert.assertTrue(driver.findElement(By.linkText("View all search results")).isDisplayed(), "Search results link is not displayed");

        searchProduct.clickViewResult();
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        // Part two to search for other categories
        searchProduct.clickInputSearchBox2();
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        searchProduct.clearSearchbox();
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        searchProduct.enterSearchText2("Treadmills");
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        driver.findElement(By.xpath("(//button[@data-event-type='submit-search'])[1]")).click();
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        String[] searchTerms = {"massage chair", "cycles", "apparel", "t-shirt", "shorts", "Shoes", "accessories", "smart watch"};
        searchProduct.searchMultipleProducts(driver, searchTerms);






    }





    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}