package Pages;

import Test.BaseTest;
import org.openqa.selenium.By;

import static Test.BaseTest.driver;

public class UnlockOffers extends BaseTest {

    By Footwearlink = By.linkText("Footwear");

    public void clickFootwearlink(){
        driver.findElement(Footwearlink).click();
    }

    By Sportshoes = By.xpath("//div[@class='logo-list__logo-title' and normalize-space(text())='Sports Shoes']");

    public void clickSportshoes(){
        driver.findElement(Sportshoes).click();
    }

    public boolean isBestBackTextDisplayed(String expectedText) {
        try {
            By offerTextElement = By.xpath("//a[@class='has-paging__title h1' and @data-event-name='View Collection' and normalize-space(text())='Best is Back']");
            String actualText = driver.findElement(offerTextElement).getText();
            return actualText.contains(expectedText);
        } catch (Exception e) {
            return false;
        }
    }

    By shoes = By.xpath("//div[@class='product-block__title' and normalize-space(text())='cult Impact Men Training Shoes - Black']");

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

    public boolean isWelcome15TextDisplayed(String expectedText) {
        try {
            By offerTextElement = By.xpath("(//div[normalize-space()='WELCOME15'])[1]");
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

    By bluetshirt = By.xpath("//div[@class='product-block__title' and normalize-space(text())=\"Men's Sky Blue Workout Essential T-shirt\"]");

    public void clickBluetshirt() {
        driver.findElement(bluetshirt).click();
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
