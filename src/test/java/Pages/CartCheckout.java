// src/test/java/Pages/CartCheckout.java
package Pages;

import Test.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartCheckout extends BaseTest {
    WebDriver driver;

    By gymEquipmentLink = By.linkText("Gym Equipment");
    By filterButton = By.xpath("(//span[contains(text(),'Filter')])[1]");
    By brandSection = By.xpath("(//div[normalize-space()='Brand'])[1]");
    By cultCheckbox = By.xpath("//span[@class='filter-group__item__text'][normalize-space()='RPM Fitness by Cult']");
    By treadmillsButton = By.xpath("(//div[normalize-space()='Treadmills'])[1]");
    By addToCartButton = By.xpath("(//button[@class='btn btn--large add-to-cart'])[1]");

    // Constructor for CartCheckout
    public CartCheckout(WebDriver driver) {
        this.driver = driver;
    }

    // Method to click on the "Gym Equipment" link
    public void clickGymEquipment() {
        driver.findElement(gymEquipmentLink).click();
    }
    // Method to expand the "Brand" section in the filter
    public void expandBrandSection() {
        driver.findElement(brandSection).click();
    }
    // Method to click on the "cult" checkbox in the brand filter
    public void clickCultCheckbox() {
        driver.findElement(cultCheckbox).click();
    }
    // Method to click on the "Treadmills" button in the filter
    public void clicktreadmillsButton() {
        driver.findElement(treadmillsButton).click();
    }
    // Method to select the available treadmill

    By treadmill = By.xpath("//div[normalize-space()='RPM Active1100DCM 6HP Peak Treadmill | 15-level Auto-Incline | Max Weight-140kg | Max Speed-18kmph (with 6 months extended warranty)']");
    public void selectTreadmill() {
        driver.findElement(treadmill).click();
    }

    public void clickAddToCartButton(){
        driver.findElement(addToCartButton).click();
    }


    // Method to click the "Add to Cart" button

    By skipButton = By.xpath("(//button[normalize-space()='Skip to Cart'])[1]");

    public void clickSkipButton(){
        driver.findElement(skipButton).click();
    }


    By  cartLink = By.xpath("(//span[@class='cart-link__icon'])[1]");
    public void clickCart(){
        driver.findElement(cartLink).click();
    } //


    // In src/test/java/Pages/CartCheckout.java

    By checkoutButton = By.xpath("//button[normalize-space()='Checkout']");
    public void clickCheckoutButton(){
        driver.findElement(checkoutButton).click();
    }



    // Method to check if the "Login to checkout" message is displayed
    public boolean isLoginToCheckoutDisplayed() {
        try {
            WebElement loginTitle = driver.findElement(By.xpath("//div[@class='h4 login-title']"));  //(//div[@class='h4 login-title'])[1]
            return loginTitle.isDisplayed() && "Login to checkout".equals(loginTitle.getText().trim());
        } catch (NoSuchElementException e) {
            return false;
        }
    }

   public void enterPhoneAndContinue(String phoneNumber) {
        try {
            // Enter phone number
            WebElement phoneInput = driver.findElement(By.xpath("//div[@class='rs-cart-drawer__login rs-login-modal']//input[@placeholder='Enter your phone number']"));
            phoneInput.clear();
            phoneInput.sendKeys(phoneNumber);

            // Wait for the button to become enabled (if needed)
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(@class, 'continue--button') and text()='Continue']\n")
            ));

            continueBtn.click();
            System.out.println("Entered phone and clicked Continue.");
        } catch (Exception e) {
            System.out.println("Failed to enter phone or click Continue: " + e.getMessage());
        }
    }

    public boolean isEnterOtpDisplayed() {
        try {
            WebElement otpTitle = driver.findElement(By.xpath("//h2[@class='h4 otp-title']"));
            return otpTitle.isDisplayed() && "Enter OTP".equals(otpTitle.getText().trim());
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}