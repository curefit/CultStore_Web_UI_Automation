package Pages;

import Test.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class VerifyPDPComponents extends BaseTest {

    WebDriver driver;
    WebDriverWait wait;

    public VerifyPDPComponents(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    By massagerLink = By.linkText("Massagers");
    By massagchair = By.xpath("(//div[normalize-space()='Massage Chair'])[1]");
    By brandDropdown = By.xpath("(//div[normalize-space()='Brand'])[1]");
    By cultcheckbox = By.xpath("(//span[normalize-space()='cult'])[1]");
    By massagechairproduct = By.xpath("(//div[normalize-space()='Cult LUXE Massage Chair | Zero Gravity with AI Voice & Bluetooth | 3D Experience | Smart Dial & 18 Preset Programs | Smart Touch Screen'])[1]");
    By emiinfoicon = By.xpath("//img[@class='snap_cult_info_img']");
    By emiPopupcloseButton = By.xpath("//img[@id='snapModalCloseon_page']");
    By buyonemiButton = By.xpath("//img[@class='snap_buy_now_btn']");
    By opencart = By.xpath("//span[@class='cart-link__icon']");
    By closecartButton = By.xpath("//button[@aria-label='Close cart']");
    By plusButton = By.xpath("//div[@class='product-discount-view-all product-discount-modal-button']"); // This is for additional offer
                                           //(//button[@data-event-name='Close Cart Drawer'])[1]



    public void clickMassagerLink() {
        driver.findElement(massagerLink).click();
    }

    public void clickMassagchair() {
        driver.findElement(massagchair).click();
    }

    public void clickBrandDropdown() {
        driver.findElement(brandDropdown).click();
    }

    public void clickCultcheckbox() {
        driver.findElement(cultcheckbox).click();
    }

    public void clickMassagechairproduct() {
        driver.findElement(massagechairproduct).click();
    }

    public void clickemiinfoicon() {
        driver.findElement(emiinfoicon).click();
    }

    public boolean verifypopup() {

        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='snap_pay_only_text']")));
        String popupText = popup.getText();
        String expectedText = "Pay only ₹31250 Now";
        if (popupText.contains(expectedText)) {
            System.out.println("Pop-up text matches the expected text.");
        } else {
            System.out.println("Pop-up text does not match. Found: " + popupText);
        }
        return popupText.contains(expectedText);
    }

    public void closePopup() {
        driver.findElement(emiPopupcloseButton).click();
    }

    public void clickBuyonemiButton() {
        driver.findElement(buyonemiButton).click();
    }

    public void openCart() {
        driver.findElement(opencart).click();
    }

    public void closeCart() {
        driver.findElement(closecartButton).click();
    }

    public boolean isOfferTextDisplayed(String expectedText) {
        try {
            WebElement offerTextElement = driver.findElement(By.xpath("(//div[@class='product-discount-additional'])[1]"));
            String actualText = offerTextElement.getText();
            return actualText.contains(expectedText);
        } catch (Exception e) {
            return false;
        }
    }

    public void clickPlusButton() {
        driver.findElement(plusButton).click();
    }

    public boolean isOfferForyou(){
        try {
            WebElement offerTextElement = driver.findElement(By.xpath("(//h2[normalize-space()='Offers for you'])[1]"));
            String actualText = offerTextElement.getText();
            return actualText.contains("Offers for you");
        } catch (Exception e) {
            return false;
        }

    }

    public boolean isBestOfferTextDisplayed(String expectedText) {
        try {
            WebElement offerTextElement = driver.findElement(By.cssSelector("div.cart-offer-callout-card-header"));
            return offerTextElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isRelax10kOfferTextDisplayed(String expectedText) {
        try {
            WebElement offerTextElement = driver.findElement(By.xpath("(//div[@class='offer-code'][normalize-space()='RELAX10K'])[2]"));//
            String actualText = offerTextElement.getText();
            return actualText.contains(expectedText);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isWELCOME500OfferTextDisplayed(String expectedText) {
        try {
            WebElement offerTextElement = driver.findElement(By.xpath("//div[@class='offer-code'][normalize-space()='WELCOME500']"));
            String actualText = offerTextElement.getText();
            return actualText.contains(expectedText);
        } catch (Exception e) {
            return false;
        }
    }















}
