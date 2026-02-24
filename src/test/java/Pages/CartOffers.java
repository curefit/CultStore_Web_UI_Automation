package Pages;

import Test.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.stream.Collectors;


public class CartOffers extends BaseTest {

    public WebDriver driver;

    // Add this constructor
    public CartOffers(WebDriver driver) {
        this.driver = driver;
    }


    By searchBoxicon = By.xpath("(//span[@class='show-search-link__icon'])[2]"); // Click on search icon homescreen

    public void clickSearchBoxIcon() {

        driver.findElement(searchBoxicon).click();
    }

    By inputsearchbox = By.xpath("//input[@class='main-search__input']");// Click on input search box to enter the text

    public void enterSearchText(String text) {
        driver.findElement(inputsearchbox).sendKeys("Smartrun Carson 5.5 HP Peak Treadmill");
    }

    By searchfor = By.xpath("(//button[@aria-label='Search'])[1]");// Click on the search button near the inoput box

    public void clickSearchFor() {
        driver.findElement(searchfor).click();
    }

    By viewResult = By.linkText("View all search results"); // After entering the text View result button should be enable to click on that

    public void clickViewResult() {
        driver.findElement(viewResult).click();
    }


    By RPMfilterbutton = By.xpath("//span[normalize-space()='RPM Fitness by Cult']");

    public void clickRPMFilterButton() {
        driver.findElement(RPMfilterbutton).click();
    }

    By treadmill1 = By.xpath("//div[normalize-space()='RPM Active1100DCM 6HP Peak Treadmill | 15-level Auto-Incline | Max Weight-140kg | Max Speed-18kmph (with 6 months extended warranty)']");

    public void clickTreadmill1() {
        driver.findElement(treadmill1).click(); //
    }


    By addToCartButton = By.xpath("(//button[@class='btn btn--large add-to-cart'])[1]");

    public void clickAddToCartButton() {
        driver.findElement(addToCartButton).click();
    }


    By inputsearchbox1 = By.xpath("(//input[@placeholder='Search for'])[1]");

    public void enterSearchText1(String text) {
        driver.findElement(inputsearchbox1).sendKeys("Shoulder Pop Active T-shirt");
    }

    By greentshirt = By.xpath("(//div[@class='product-block__title'][normalize-space()='Shoulder Pop Active T-shirt'])[1]");

    public void clickGreenshirt() {
        driver.findElement(greentshirt).click();
    }

    By addToCartButton2 = By.xpath("(//button[@class='btn btn--large add-to-cart'])[1]");

    public void clickAddToCartButton2() {
        driver.findElement(addToCartButton2).click();
    }


    public void selectTshirtSize() {
        boolean sizeSelectedAndAdded = false;
        // 1. Find all available size options.
        List<WebElement> sizeOptions = driver.findElements(By.xpath("//fieldset[contains(@class, 'option--size')]//label"));

        if (sizeOptions.isEmpty()) {
            System.out.println("No size options found for the T-shirt.");
            return;
        }

        System.out.println("Found " + sizeOptions.size() + " total size options. Checking availability...");
        List<String> availableSizes = sizeOptions.stream().map(WebElement::getText).collect(Collectors.toList());
        System.out.println("Available sizes on page: " + availableSizes);

        // 2. Iterate through each size, click it, and check the button status.
        for (WebElement sizeOption : sizeOptions) {
            String sizeText = sizeOption.getText();
            try {
                // Click the size option to check its status.
                sizeOption.click();
                // A short pause for the UI to update.
                Thread.sleep(500);

                // 3. Check if the "Add to Cart" button is visible and has the correct text.
                List<WebElement> addToCartButtons = driver.findElements(By.xpath("//button[@class='btn btn--large add-to-cart']"));

                if (!addToCartButtons.isEmpty() && addToCartButtons.get(0).isDisplayed() && addToCartButtons.get(0).getText().equalsIgnoreCase("Add to Cart")) {
                    System.out.println("Size '" + sizeText + "' is in stock. Selecting this size and adding to cart.");
                    addToCartButtons.get(0).click(); // Click the "Add to Cart" button.
                    sizeSelectedAndAdded = true;
                    break; // Exit the loop as we've found and added an available size.
                } else {
                    // 4. If "Add to Cart" is not there, check for the "Sold out" indicator.
                    List<WebElement> soldOutIndicators = driver.findElements(By.xpath("//div[@class='quantity-submit-row__flex flex align-center']//button[contains(text(), 'Sold out')]"));
                    if (!soldOutIndicators.isEmpty() && soldOutIndicators.get(0).isDisplayed()) {
                        System.out.println("Size '" + sizeText + "' is Sold Out.");
                    } else {
                        System.out.println("Could not determine stock status for size '" + sizeText + "'. Neither 'Add to Cart' nor 'Sold out' found.");
                    }
                }
            } catch (Exception e) {
                System.out.println("An error occurred while checking size '" + sizeText + "': " + e.getMessage());
            }
        }

        if (!sizeSelectedAndAdded) {
            System.out.println("No available T-shirt sizes were found in stock to add to cart.");
        }
    }


    By inputsearchbox2 = By.xpath("(//input[@placeholder='Search for'])[1]");

    public void enterSearchText2(String text) {
        driver.findElement(inputsearchbox2).sendKeys("Women's Black Training Essential Bra");
    }

    By sportsbra = By.xpath("//div[@class='product-block__title' and text()=\"Women's Black Training Essential Bra\"]");

    public void clickSportsBra() {
        driver.findElement(sportsbra).click();
    }


    public void selectBraSize() {
        boolean sizeSelectedAndAdded = false;
        // 1. Find all available size options.
        List<WebElement> sizeOptions = driver.findElements(By.xpath("//fieldset[contains(@class, 'option--size')]//label"));

        if (sizeOptions.isEmpty()) {
            System.out.println("No size options found for the bra.");
            return;
        }

        System.out.println("Found " + sizeOptions.size() + " total size options. Checking availability...");
        List<String> availableSizes = sizeOptions.stream().map(WebElement::getText).collect(Collectors.toList());
        System.out.println("Available sizes on page: " + availableSizes);

        // 2. Iterate through each size, click it, and check the button status.
        for (WebElement sizeOption : sizeOptions) {
            String sizeText = sizeOption.getText();
            try {
                // Click the size option to check its status.
                sizeOption.click();
                // A short pause for the UI to update.
                Thread.sleep(500);

                // 3. Check if the "Add to Cart" button is visible.
                List<WebElement> addToCartButtons = driver.findElements(By.xpath("//button[@class='btn btn--large add-to-cart']"));

                if (!addToCartButtons.isEmpty() && addToCartButtons.get(0).isDisplayed() && addToCartButtons.get(0).getText().equalsIgnoreCase("Add to Cart")) {
                    System.out.println("Size '" + sizeText + "' is in stock. Selecting this size and adding to cart.");
                    addToCartButtons.get(0).click(); // Click the "Add to Cart" button.
                    sizeSelectedAndAdded = true;
                    break; // Exit the loop as we've found and added an available size.
                } else {
                    // 4. If "Add to Cart" is not there, check for the "Sold out" indicator.
                    List<WebElement> soldOutIndicators = driver.findElements(By.xpath("//div[@class='quantity-submit-row__flex flex align-center']"));
                    if (!soldOutIndicators.isEmpty() && soldOutIndicators.get(0).isDisplayed()) {
                        System.out.println("Size '" + sizeText + "' is Sold Out.");
                    } else {
                        System.out.println("Could not determine stock status for size '" + sizeText + "'. Neither 'Add to Cart' nor 'Sold out' found.");
                    }
                }
            } catch (Exception e) {
                System.out.println("An error occurred while checking size '" + sizeText + "': " + e.getMessage());
            }
        }

        if (!sizeSelectedAndAdded) {
            System.out.println("No available bra sizes were found in stock to add to cart.");
        }
    }


    public void enterSearchText3(String text) {
        driver.findElement(inputsearchbox2).sendKeys("Cult Apex Massage Chair with Zero Gravity");
    }

    By massagechair = By.xpath("//div[normalize-space()='Cult Zen Massage Chair with Zero Gravity, SL Track 2D Massage Technique and Bluetooth AI voice Function For Full Body Massage At Home']");

    public void clickMassageChair() {
        driver.findElement(massagechair).click();
    }

    By clickoncart = By.xpath("(//span[@class='cart-link__icon'])[1]");

    public void clickOnCart() {
        driver.findElement(clickoncart).click();
    }

    // Click on more offers button

    By moreOffersButton = By.xpath("//span[contains(text(), 'More Offers')]\n");

    public void clickMoreOffersButton() {
        driver.findElement(moreOffersButton).click();
    }


    // Validate the Offers in the cart are present or not and print the offers
    public void printCartOffers() {
        String[] offerXpaths = {
                "//extra-offers-container[@class='cart-offer-callout-card-container']//div[@class='offer-code'][normalize-space()='RELAX10K']",
                "//div[normalize-space()='RUN2K']",
                "//div[normalize-space()='BURNOFF1K']",
                "//div[@class='cart-offer-callout-card-show-more-container-eligible']//div[@class='offer-code'][normalize-space()='WELCOME500']",
                "//div[normalize-space()='ACTIVE200']"
        };

        System.out.println("Available offers:");
        for (String xpath : offerXpaths) {
            try {
                WebElement offerElement = driver.findElement(By.xpath(xpath));
                String offerText = offerElement.getText();
                if (!offerText.isEmpty()) {
                    System.out.println("- " + offerText);
                }
            } catch (Exception e) {
                // Offer not found, do nothing
            }
        }
    }

    // Clicks the "Apply" button for each available offer.
    public void applyAllAvailableOffers() {
        // This XPath should find all "APPLY" buttons for the eligible offers.
        List<WebElement> applyButtons = driver.findElements(By.xpath("//button[contains(@class, 'rs-legacy-offer-card__action') and text()='APPLY']\n"));

        if (applyButtons.isEmpty()) {
            System.out.println("No 'Apply' buttons found for available offers.");
            return;
        }

        System.out.println("Found " + applyButtons.size() + " offers to apply. Clicking them sequentially.");

        // Iterate and click each apply button.
        // Note: Applying multiple coupons might not be possible on the website.
        // This will attempt to click all visible "Apply" buttons.
        for (WebElement applyButton : applyButtons) {
            try {
                applyButton.click();
                System.out.println("Clicked an 'Apply' button.");
                // A short pause might be needed for the UI to react before the next click.
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("Could not click an 'Apply' button: " + e.getMessage());
            }
        }
    }
}





















































