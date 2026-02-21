package Pages;

import Test.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static Test.BaseTest.driver;

public class UnlockOffers extends BaseTest {


    public WebDriver driver;

    // Add this constructor
    public UnlockOffers(WebDriver driver) {
        this.driver = driver;
    }

    By Footwearlink = By.linkText("Footwear");

    public void clickFootwearlink(){
        driver.findElement(Footwearlink).click();
    }


    By shoes = By.xpath("//div[normalize-space()=\"cult Men's Traverse Running Shoes - Off White\"]\n");

    public void clickShoes() {
        driver.findElement(shoes).click();
    }

    // Method to click on the "Unlock Offers" button
    public boolean isAdditonalOffersSectionDisplayed(String expectedText) {
        try {
            By offerTextElement = By.xpath("(//div[@class='product-discount-additional'])[1]");
            String actualText = driver.findElement(offerTextElement).getText();
            return actualText.contains(expectedText);
        } catch (Exception e) {
            return false;
        }
    }

    By plusButton = By.xpath("(//div[@class='product-discount-view-all product-discount-modal-button'])[1]");

    public void clickPlusButton() {
        driver.findElement(plusButton).click();
    }

    public boolean isBestOfferTextDisplayed(String expectedText) {
        try {
            By offerTextElement = By.xpath("(//div[@class='cart-offer-callout-card-header'])[1]");
            return driver.findElement(offerTextElement).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isUnlockOffersTextDisplayed(String expectedText) {
        try {
            By offerTextElement = By.xpath("(//span[normalize-space()='Unlock Offers'])[1]");
            String actualText = driver.findElement(offerTextElement).getText();
            return actualText.contains(expectedText);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isWELCOME500TextDisplayed(String expectedText) {
        try {
            By offerTextElement = By.xpath("//div[@class='cart-offer-callout-card-body-header column-reverse']//div[@class='offer-code'][normalize-space()='WELCOME500']");
            String actualText = driver.findElement(offerTextElement).getText();
            return actualText.contains(expectedText);
        } catch (Exception e) {
            return false;
        }
    }

    By knowmore = By.xpath("(//span[@class='cart-offer-callout-card-know-more-btn'][normalize-space()='Know More'])[5]");

    public void clickKnowMore() {
        driver.findElement(knowmore).click();   //(//span[@class='cart-offer-callout-card-know-more-btn'])[1]
    }

    By menslink = By.linkText("Men");

    public void clickMenslink() {
        driver.findElement(menslink).click();
    }

    By  tshirts = By.xpath("(//div[@class='logo-list__logo-title'])[1]");
    public void clickTshirts() {
        driver.findElement(tshirts).click();
    }

    By colourfilter = By.xpath("//div[normalize-space()='Color']");
    public void clickColourFilter() {
        driver.findElement(colourfilter).click();
    }

    By bluecheckbox = By.xpath("//span[normalize-space()='Blue']");
    public void clickBlueCheckbox() {
        driver.findElement(bluecheckbox).click();
    }

    // Locator for any product containing "Blue" in its title
    By bluetshirt = By.xpath("//div[@class='product-block__title' and contains(., 'Blue')]");

    public void clickBluetshirt() {
        // Find all blue t-shirts and click the first available one
        List<WebElement> blueTshirts = driver.findElements(bluetshirt);
        if (!blueTshirts.isEmpty()) {
            blueTshirts.get(0).click();
        } else {
            // Optional: Handle the case where no blue t-shirts are found
            System.out.println("No blue T-shirts found.");
        }
    }




    public boolean isUnlockPDPTextDisplayed(String expectedText) {
        try {
            By offerTextElement = By.xpath("(//div[@class='product-discount-additional aligncenter'])[1]");
            String actualText = driver.findElement(offerTextElement).getText();
            return actualText.contains(expectedText);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isFitstart20OfferTextDisplayed(String expectedText) {
        try {
            By offerTextElement = By.xpath("(//div[@class='offer-code'])[1]");
            String actualText = driver.findElement(offerTextElement).getText();
            return actualText.contains(expectedText);
        } catch (Exception e) {
            return false;
        }
    }



}
