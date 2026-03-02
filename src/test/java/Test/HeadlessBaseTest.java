package Test;

import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
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

//import static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.header;

public class HeadlessBaseTest {
    public static WebDriver driver;
    HomePage homePage;
    CartCheckout cartCheckout;
    ValidateServicibility validateServicibility;
    WebDriverWait wait;
    SearchProduct searchProduct;

    @BeforeMethod
    public void setup() {


        // Set up ChromeDriver with options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--incognito");

        // Additional code for the headless mode
        options.addArguments("--headless=new"); // Use modern headless mode in CI
        options.addArguments("--no-sandbox"); // Sandbox typically required in CI environments
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-debugging-port=9222");
        options.addArguments("--disable-software-rasterizer");

        // The "Masking" kit from BaseTest
        options.setExperimentalOption("excludeSwitches", java.util.Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--disable-blink-features=AutomationControlled");

        // Add a REAL User-Agent from BaseTest
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36");

        String chromeBinary = System.getenv("CHROME_BIN");
        if (chromeBinary != null && !chromeBinary.isBlank()) {
            options.setBinary(chromeBinary);
        }

        // Initialize WebDriver
        WebDriverManager.chromedriver().setup();
        driver = startChromeWithRetry(options, 2);
        driver.manage().deleteAllCookies();

        // Initialize WebDriverWait
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Navigate to the website
        driver.get("https://cultstore.com/");

        // Initialize page objects
        homePage = new HomePage(driver);
        cartCheckout = new CartCheckout(driver);

    }

    private WebDriver startChromeWithRetry(ChromeOptions options, int attempts) {
        RuntimeException lastError = null;
        for (int i = 1; i <= attempts; i++) {
            try {
                return new ChromeDriver(options);
            } catch (RuntimeException e) {
                lastError = e;
                System.err.println("Chrome start attempt " + i + " failed: " + e.getClass().getSimpleName() + " - " + e.getMessage());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw e;
                }
            }
        }
        throw lastError;
    }

    @Test(priority = 1)
    public void validateTitles_Products() {
        // This is the code for headless Automation mode

        // New Arrivals
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("New Arrivals"))).click();
        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//h1[normalize-space()='New Arrivals'])[1]")));
        Assert.assertEquals(titleElement.getText(), "New Arrivals", "Title does not match"); //(//h1[normalize-space()='New Arrivals'])[1]

        // Men's Wear
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Men"))).click();
        titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//h1[normalize-space()=\"Men's Sports Wear\"])[1]")));
        Assert.assertEquals(titleElement.getText(), "Men's Sports Wear", "Title does not match");

        // Women's Wear
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Women"))).click();
        titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//h1[normalize-space()=\"Women's Sports Wear\"])[1]")
        ));
        Assert.assertEquals(titleElement.getText(), "Women's Sports Wear", "Title does not match");

        // Apparel
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Apparel")));
        homePage.clickApparel();
        // Verify the title contains "Apparel"
        titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[contains(@class, 'hometitle') and contains(text(), 'Apparel')]")
        ));
        Assert.assertTrue(titleElement.getText().contains("Apparel"),
                "Title match failed! Expected text to contain 'Apparel' but found: [" + titleElement.getText() + "]");

        //Footwear
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Footwear"))).click();

        //Gym Equipment
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Gym Equipment"))).click();
        titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//h1[normalize-space()='Fitness & Gym Equipment'])[1]")
        ));
        Assert.assertEquals(titleElement.getText(), "Fitness & Gym Equipment", "Title does not match");


        //Massagers
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Massagers"))).click();
        titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//h1[normalize-space()='Relaxation Machines'])[1]")
        ));
        Assert.assertEquals(titleElement.getText(), "Relaxation Machines", "Title does not match");


        //Accessories
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Accessories"))).click();

        // Cycles
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Cycles"))).click();
        titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[contains(@class, 'hometitle') and contains(text(), 'Cycles')]")
        ));
        Assert.assertEquals(titleElement.getText(), "Cycles", "Title does not match");

        // Shop by Activity

        driver.findElement(By.xpath("(//a[@class='navigation__link' and normalize-space()='Shop by Activity'])[2]")).click();
        titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".hometitle.h1.spaced-row.align-center")
        ));
        Assert.assertEquals(titleElement.getText().trim(), "Fitness Essentials for Every Activity", "Title does not match");

        // Store Locator
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Store Locator"))).click();
        titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//h1[normalize-space()='Cult exclusive stores'])[1]")
        ));
        Assert.assertEquals(titleElement.getText(), "Cult exclusive stores", "Title does not match");

        // Continue similarly for other interactions in the test
    }


    @Test(priority = 2)
    public void filterByCultBrand() {


        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Gym Equipment")));
        cartCheckout.clickGymEquipment();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[normalize-space()='Treadmills'])[1]")));
        cartCheckout.clicktreadmillsButton();

        // Wait for the brand section to be clickable and then expand it
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[normalize-space()='Brand'])[1]")));
        cartCheckout.expandBrandSection();

        // Wait for the 'cult' checkbox to be clickable and then click it
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='filter-group__item__text'][normalize-space()='RPM Fitness by Cult']")));
        cartCheckout.clickCultCheckbox();

        // Wait for the treadmill product to be clickable and then click it
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[normalize-space()='RPM Active1100DCM 6HP Peak Treadmill | 15-level Auto-Incline | Max Weight-140kg | Max Speed-18kmph (with 6 months extended warranty)']")));
        cartCheckout.selectTreadmill();

        // Wait for the 'Add to Cart' button to be clickable and then click it
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='Add to Cart'])[1]")));
        cartCheckout.clickAddToCartButton();

        // Wait for the cart icon to be clickable and then click it
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='cart-link__icon']")));
        cartCheckout.clickCart();

        try {Thread.sleep(3000);} catch (InterruptedException e) {Thread.currentThread().interrupt();}

        // Wait for the checkout button to be clickable and then click it
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Checkout']")));
        cartCheckout.clickCheckoutButton();

        // Verify the checkout page title "Login to checkout"
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='h4 login-title']")));
        Assert.assertTrue(cartCheckout.isLoginToCheckoutDisplayed(), "'Login to checkout' message is not displayed!");

        // Enter phone number and continue
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='rs-cart-drawer__login rs-login-modal']//input[@placeholder='Enter your phone number']")));
        cartCheckout.enterPhoneAndContinue("8792514524");


    }

    @Test(priority = 3)
    public void validateCartEmpty() {


        CartEmpty cartEmpty = new CartEmpty(driver);

        // Wait for the cart icon to be clickable, then click it
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='cart-link__icon']")));
        cartEmpty.clickCartIcon();

        // Wait for the empty cart text to be visible, then verify it
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[normalize-space()='Your cart is empty']")));
        cartEmpty.verifyEmptyCartText();

        // Wait for the "Back to Homepage" button to be clickable, then click it
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Continue Shopping']")));
        cartEmpty.clickBackToHomepage();
    }


    @Test(priority = 4)
    public void checkServiceability() {

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Gym Equipment")));
        ValidateServicibility ValidateServicibility = new ValidateServicibility(driver);
        ValidateServicibility.clickGymEquipment();
        try {Thread.sleep(3000);} catch (InterruptedException e) {Thread.currentThread().interrupt();}


        WebElement filterElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[contains(text(),'Filter')])[1]")));
        Actions actions = new Actions(driver);
        actions.doubleClick(filterElement).perform();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[normalize-space()='Category'])[1]")));
        ValidateServicibility.expandCategorySection();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='filter-group__item__text'][normalize-space()='Treadmill'])[1]")));
        ValidateServicibility.clickTreadmillsCheckbox();


        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[normalize-space()='Brand'])[1]")));
        ValidateServicibility.expandBrandSection();



        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[normalize-space()='cult'])[1]")));
        ValidateServicibility.clickCultCheckbox();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[contains(text(),'Smartrun Davie 7 HP Peak Treadmill | 15-level Auto')])[1]")));
        ValidateServicibility.selectAvailabletreadmill();


        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");// Use JavaScript to scroll down the page(x,y) component x- vertical, y- horizontal
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@id='PincodeInput'])[1]")));
        ValidateServicibility.enterPincode("444607");


        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='Check'])[1]")));
        ValidateServicibility.clickCheckButton();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='delivery-text'])[1]")));
        Assert.assertTrue(ValidateServicibility.isServiceableMessageDisplayed(), "delivery-text");


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//li[normalize-space()='Easy 10 days exchange available'])[1]")));
        Assert.assertTrue(ValidateServicibility.isExchangeMessageDisplayed(), "Easy 10 days exchange available");


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//li[normalize-space()='COD eligibility shown at checkout'])[1]")));
        Assert.assertTrue(ValidateServicibility.isPayondeliveryMessageDisplayed(), "COD eligibility shown at checkout");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//li[normalize-space()='No Return Available'])[1]")));
        Assert.assertTrue(ValidateServicibility.isReturnMessageDisplayed(), "No Return Available");


      /*  Assert.assertTrue(ValidateServicibility.isWarrantyMessageDisplayed(), "Warranty Included");*/

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@id='PincodeInput'])[1]")));
        ValidateServicibility.enterPincodeunservicable("372821");


        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='Check'])[1]")));
        ValidateServicibility.clickCheckButton();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//li[@class='unserviceable'])[1]")));
        Assert.assertTrue(ValidateServicibility.isUnserviceableMessageDisplayed(), "Unserviceable message is not displayed");


    }


    @Test(priority = 5)
    public void verifyPDPComponents() {

        Pages.VerifyPDPComponents verifyPDPComponents = new Pages.VerifyPDPComponents(driver);

        // Click on the massagers link
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Massagers")));
        verifyPDPComponents.clickMassagerLink();

        // Wait for the massage chair product to be clickable and then click it
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[normalize-space()='Massage Chair'])[1]")));
        verifyPDPComponents.clickMassagchair();

        // Wait for the brand section to be clickable and then expand it
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[normalize-space()='Brand'])[1]")));
        verifyPDPComponents.clickBrandDropdown();

        // Wait for the 'cult' checkbox to be clickable and then click it
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[normalize-space()='cult'])[1]")));
        verifyPDPComponents.clickCultcheckbox();

        // Wait for the massage chair product to be clickable and then click it
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[normalize-space()='Cult LUXE Massage Chair | Zero Gravity with AI Voice & Bluetooth | 3D Experience | Smart Dial & 18 Preset Programs | Smart Touch Screen'])[1]")));
        verifyPDPComponents.clickMassagechairproduct();
        // Wait for the EMI info icon to be clickable and then click it
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@class='snap_cult_info_img']")));
        try {Thread.sleep(3000);} catch (InterruptedException e) {Thread.currentThread().interrupt();}
        try {Thread.sleep(3000);} catch (InterruptedException e) {Thread.currentThread().interrupt();}
        verifyPDPComponents.clickemiinfoicon();


        // Wait for the "No Cost EMIs" text to be visible and verify it
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='snap_emi_option' and text()='EMI Options']")));
        Assert.assertTrue(
                driver.findElement(By.xpath("//div[@class='snap_emi_option' and text()='EMI Options']")).isDisplayed(),
                "EMI Options text is displayed"
        );

        // Wait for the close icon to be clickable and then click it
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@id='snapModalCloseon_page']")));
        verifyPDPComponents.closePopup();

        // Wait for the "Buy on EMI" button to be clickable and then click it
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@class='snap_buy_now_btn']")));
        verifyPDPComponents.clickBuyonemiButton();
        // Wait for the cart icon to be clickable and then click it
       // wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='cart-link__icon']")));
      //  verifyPDPComponents.openCart();

        // Wait for the "No Cost EMIs" text to be visible and verify it
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Close cart']")));
        verifyPDPComponents.closeCart();

        // Wait for the "Additional offers for you" section to be visible and verify it
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='product-discount-additional'])[1]")));//(//div[@class='product-discount-additional'])[1]
        Assert.assertTrue(
                verifyPDPComponents.isOfferTextDisplayed("Additional offers for you"),
                "Offer text is not displayed as expected");

        // Wait for the plus button to be clickable and then click it
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='product-discount-view-all product-discount-modal-button']")));
        try {Thread.sleep(3000);} catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        verifyPDPComponents.clickPlusButton();

        // Wait for the "Offers for you" title to be visible and verify it
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//h2[normalize-space()='Offers for you'])[1]")));
        Assert.assertTrue(verifyPDPComponents.isOfferForyou(), "Offer for you is not displayed as expected");

        // Wait for the "Best Offer - Apply at Checkout" text to be visible and verify it
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='cart-offer-callout-card-header' and normalize-space()='Best Offer - Apply at Checkout']\n")));
        Assert.assertTrue(verifyPDPComponents.isBestOfferTextDisplayed("Best Offer - Apply at Checkout"),
                "Offer text is not displayed as expected");

        // Wait for the "Know More" button to be clickable and then click it
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='offer-code'][normalize-space()='RELAX10K'])[2]")));
        Assert.assertTrue(verifyPDPComponents.isRelax10kOfferTextDisplayed("RELAX10K"),
                "RELAX10K offer text is not displayed as expected");
        // Wait for the "WELCOME500" offer text to be clickable and verify it
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='offer-code'][normalize-space()='WELCOME500']")));
        Assert.assertTrue(verifyPDPComponents.isWELCOME500OfferTextDisplayed("WELCOME500"),
                "WELCOME500 offer text is not displayed as expected");



    }

    @Test(priority = 6)
    public void searchProduct() {
        Pages.SearchProduct searchProduct = new Pages.SearchProduct(driver); // Page object for SearchProduct


        // Part one to search for the bottles
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='show-search-link__icon'])[2]")));
        searchProduct.clickSearchBoxIcon();
        // Wait for the search input box to be clickable and then click it
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='main-search__input']")));
        searchProduct.closeSearchBox();
        // Wait for the search box icon to be clickable and then click it again to open the search box
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='show-search-link__icon'])[2]")));
        searchProduct.clickSearchBoxIcon();
        // Wait for the search input box to be clickable and then enter the search text
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='main-search__input']")));
        searchProduct.enterSearchText("bottles");
        // Wait for the search button to be clickable and then click it
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("View all search results")));
        Assert.assertTrue(driver.findElement(By.linkText("View all search results")).isDisplayed(), "Search results link is not displayed");
        // Wait for the "View all search results" link to be clickable and then click it  and verify the search results page is displayed
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("View all search results")));
        searchProduct.clickViewResult();
        // Part two to search for other categories
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@placeholder='Search our store'])[1]")));
        searchProduct.clickInputSearchBox2();
        // Wait for the search input box to be clickable and then clear the existing text
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@placeholder='Search our store'])[1]")));
        searchProduct.clearSearchbox();
        // Wait for the search input box to be clickable and then enter the search text
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@placeholder='Search our store'])[1]")));
        searchProduct.enterSearchText2("Treadmills");

        // Wait for the search button to be clickable and then click it
        driver.findElement(By.xpath("(//button[@data-event-type='submit-search'])[1]")).click();


        String[] searchTerms = {"massage chair", "cycles", "apparel", "t-shirt", "shorts", "Shoes", "accessories", "smart watch"};
        searchProduct.searchMultipleProducts(driver, searchTerms);

    }

    @Test(priority = 7)
    public void unlockOffers() {
        Pages.UnlockOffers unlockOffers = new Pages.UnlockOffers(driver);

        // Click on the footwear link
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Footwear")));
        unlockOffers.clickFootwearlink();
        //  ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)"); --> Use to add the scroll down the page if the element is not visible in viewport
        // Wait for the shoes product to be clickable and then click it
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[normalize-space()=\"cult Men's Traverse Running Shoes - Off White\"]\n")));
        unlockOffers.clickShoes();
        // Wait for the "Additional offers for you" section to be visible and verify it
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='product-discount-additional'])[1]")));
        unlockOffers.isAdditonalOffersSectionDisplayed("Additional offers for you");
        // Wait for the plus button to be clickable and then click it
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.product-discount-view-all.product-discount-modal-button")));
        unlockOffers.clickPlusButton();
        // Wait for the "Offers for you" title to be visible and verify it
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='cart-offer-callout-card-header'])[1]"))); //(//div[@class='cart-offer-callout-card-header'])[1]
        unlockOffers.isBestOfferTextDisplayed("Best Offer - Apply at Checkout");
        // Wait for the "Unlock Offers" text to be clickable and verify it
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[normalize-space()='Unlock Offers'])[1]")));
        unlockOffers.isUnlockOffersTextDisplayed("Unlock Offers");
        // Wait for the "RELAX10K" offer text to be clickable and verify it
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='cart-offer-callout-card-body-header column-reverse']//div[@class='offer-code'][normalize-space()='WELCOME500']")));
        Assert.assertTrue(unlockOffers.isWELCOME500TextDisplayed("WELCOME500"), "WELCOME500 offer text is not displayed as expected");
        // Close the offers modal
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='offer-modal-close'])[1]")));
        driver.findElement(By.xpath("(//span[@class='offer-modal-close'])[1]")).click();
        // Click on the men link
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Men")));
        unlockOffers.clickMenslink();
        // Wait for the t-shirts product to be clickable and then click it
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='logo-list__logo-title'])[1]")));
        unlockOffers.clickTshirts();
        // Wait for the color filter to be clickable and then click it
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[normalize-space()='Color']")));
        unlockOffers.clickColourFilter();
        // Wait for the blue color checkbox to be clickable and then click it
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Blue']")));
        unlockOffers.clickBlueCheckbox();
        // Wait for the blue t-shirt product to be clickable and then click it
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='product-block__title' and contains(., 'Blue')]")));
        unlockOffers.clickBluetshirt();
        // Wait for the "Unlock Offers" text to be visible and verify it
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='product-discount-additional aligncenter'])[1]")));
        unlockOffers.isUnlockPDPTextDisplayed("Unlock Offers");
        // Wait for the plus button to be clickable and then click it
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='product-discount-view-all product-discount-modal-button'])[1]")));
        unlockOffers.clickPlusButton();
        // Wait for the "Offers for you" title to be visible and verify it
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='offer-code'])[1]")));
        unlockOffers.isFitstart20OfferTextDisplayed("FITSTART20");
        // Wait for the "Know More" button to be clickable and then click it
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='cart-offer-callout-card-know-more-btn'])[1]")));
        driver.findElement(By.xpath("(//span[@class='cart-offer-callout-card-know-more-btn'])[1]")).click();
    }

    @Test(priority = 8)
    public void cartOffers() {
        Pages.CartOffers cartOffers = new Pages.CartOffers(driver);


        // Add the treadmill
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='show-search-link__icon'])[2]")));
        cartOffers.clickSearchBoxIcon();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='main-search__input']")));
        cartOffers.enterSearchText("Smartrun Carson 5.5 HP Peak Treadmill");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@aria-label='Search'])[1]")));
        cartOffers.clickSearchFor();

        // Below line is used to scroll down the page to view the treadmill product
        //((JavascriptExecutor) driver).executeScript("window.scrollBy(0,700)"); // Use JavaScript to scroll down the page(x,y) component x- vertical, y- horizontal

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='RPM Fitness by Cult']")));
        cartOffers.clickRPMFilterButton();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[normalize-space()='RPM Active1100DCM 6HP Peak Treadmill | 15-level Auto-Incline | Max Weight-140kg | Max Speed-18kmph (with 6 months extended warranty)']")));
        cartOffers.clickTreadmill1();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='Add to Cart'])[1]")));
        cartOffers.clickAddToCartButton();


        // Add the Shoulder Pop Active T-shirt
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='show-search-link__icon'])[2]")));
        cartOffers.clickSearchBoxIcon();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='main-search__input']")));
        cartOffers.enterSearchText1("Shoulder Pop Active T-shirt");

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("View all search results")));
        cartOffers.clickSearchFor();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='product-block__title'][normalize-space()='Shoulder Pop Active T-shirt'])[1]")));
        cartOffers.clickGreenshirt();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//fieldset[contains(@class, 'option--size')]//label")));
        cartOffers.selectTshirtSize();

        // Add the Sports Bra
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='show-search-link__icon'])[2]")));
        cartOffers.clickSearchBoxIcon();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='main-search__input']")));
        cartOffers.enterSearchText2("Sports Bra");

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("View all search results")));
        cartOffers.clickViewResult();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='product-block__title' and text()=\"Women's Black Training Essential Bra\"]")));
        cartOffers.clickSportsBra();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//fieldset[contains(@class, 'option--size')]//label")));
        cartOffers.selectBraSize();


        // Add the Massage Chair
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='show-search-link__icon'])[2]")));
        cartOffers.clickSearchBoxIcon();


        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='main-search__input']")));
        cartOffers.enterSearchText3("Massage Chair");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@aria-label='Search'])[1]")));
        cartOffers.clickSearchFor();


        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[normalize-space()='Cult Zen Massage Chair with Zero Gravity, SL Track 2D Massage Technique and Bluetooth AI voice Function For Full Body Massage At Home']")));
        cartOffers.clickMassageChair();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='Add to Cart'])[1]")));
        cartOffers.clickAddToCartButton();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='cart-link__icon']")));
        cartOffers.clickOnCart();


        // Locate the scrollable element
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='rs-cart-drawer__content cart-drawer__content-upper']")));
        WebElement scrollableElement = driver.findElement(By.xpath("//div[@class='rs-cart-drawer__content cart-drawer__content-upper']"));
        // Scroll down by 500 pixels
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop = arguments[0].scrollTop + 500;", scrollableElement);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'More Offers')]\n")));
        cartOffers.clickMoreOffersButton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }


        cartOffers.printCartOffers();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        cartOffers.applyAllAvailableOffers();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

    @Test(priority = 9)
    public void CheckoutLogin() {
        Pages.CheckoutLogin checkoutLogin = new Pages.CheckoutLogin(driver);
        // Pages.CartOffers cartOffers = new Pages.CartOffers(driver);
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Gym Equipment")));
        checkoutLogin.clickGymEquipment();


        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[normalize-space()='Exercise Cycles'])[1]")));
        checkoutLogin.clickExerciseBike();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[normalize-space()='Smartbike Danville | 6.5kg Flywheel | Max Weight-130kg | 100 Level Magnetic Resistance (with 6 months extended warranty)'])[1]")));
        checkoutLogin.clickBikeExercise();


        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn--large add-to-cart' and @type='submit' and @name='add' and @data-event-type='add-to-cart' and @data-event-name='Add to Cart']")));
        checkoutLogin.clickaddcart();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='cart-link__icon'])[1]")));
        checkoutLogin.clickCartButton();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Checkout']")));
        checkoutLogin.clickCheckoutButton();
        try {Thread.sleep(3000);} catch (InterruptedException e) {Thread.currentThread().interrupt();}


        WebElement phoneInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='rs-cart-drawer__login rs-login-modal']//input[@placeholder='Enter your phone number']")));
        phoneInput.clear();
        phoneInput.sendKeys("8792514524");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'continue--button') and text()='Continue']\n"))).click();

    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }







}


