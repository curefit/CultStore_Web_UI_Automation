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
    By cultCheckbox = By.xpath("(//span[normalize-space()='cult'])[1]");
    By treadmillsButton = By.xpath("(//div[normalize-space()='Treadmills'])[1]");
    By addToCartButton = By.xpath("(//span[@class='cart-link__icon'])[1]");

    // Constructor for CartCheckout
    public CartCheckout(WebDriver driver) {
        this.driver = driver;
    }

    // Method to click on the "Gym Equipment" link
    public void clickGymEquipment() {
        driver.findElement(gymEquipmentLink).click();
    }
    // Method to click on the "Filter" button
    public void clickFilter() {
        driver.findElement(filterButton).click();
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
    // Method to select the first available treadmill
    public void selectAvailabletreadmill() {
        boolean treadmillFound = false;
        for (int i = 1; i <= 5; i++) {
            String xpath = "(//div[contains(text(),'Smartrun Davie 7 HP Peak Treadmill | 15-level Auto')])[" + i + "]"; // (//div[contains(text(),'Smartrun Davie 7 HP Peak Treadmill | 15-level Auto')])[1]
            try {
                WebElement treadmill = driver.findElement(By.xpath(xpath));
                if (treadmill.isDisplayed()) {
                    treadmill.click();
                    treadmillFound = true;
                    break;
                }
            } catch (NoSuchElementException e) {
                // Try next iteration if the element is not found
            }
        }
        if (!treadmillFound) {
            System.out.println("No 'Smartrun Davie 7 HP Peak Treadmill | 15-level Auto' found in the specified range.");
        }
    }

    // Method to click the "Add to Cart" button
    public void clickAddToCart() {
        try {
            WebElement addToCartBtn = driver.findElement(By.xpath("(//button[@class='btn btn--large add-to-cart'])[1]"));
            // Check if button is visible and enabled
            if (addToCartBtn.isDisplayed() && addToCartBtn.isEnabled()) {
                addToCartBtn.click();
                System.out.println("Clicked 'Add to Cart' button.");
            } else {
                System.out.println("'Add to Cart' button is not visible or not enabled.");
            }
        } catch (Exception e) {
            System.out.println("'Add to Cart' button not found or not clickable.");
        }
    }
    // Method to click on the cart link
    public void clickCart() {
        try {
            WebElement cartLink = driver.findElement(addToCartButton);
            cartLink.click();
            System.out.println("Clicked on the cart link.");
        } catch (NoSuchElementException e) {
            System.out.println("Cart link not found.");
        }
    }

    // In src/test/java/Pages/CartCheckout.java

    public void clickCheckoutButton() {
        try {
            WebElement checkoutBtn = driver.findElement(By.xpath("(//marmeto-checkout-button[@class='btn btn--large btn--wide marmeto-button--loading'])[1]"));
            if (checkoutBtn.isDisplayed() && checkoutBtn.isEnabled()) {
                checkoutBtn.click();
                System.out.println("Clicked 'Checkout' button.");
            } else {
                System.out.println("'Checkout' button is not visible or not enabled.");
            }
        } catch (Exception e) {
            System.out.println("'Checkout' button not found or not clickable.");
        }
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
            WebElement phoneInput = driver.findElement(By.xpath("(//input[@id='cartDrawerPhoneNumber'])[1]"));
            phoneInput.clear();
            phoneInput.sendKeys(phoneNumber);

            // Wait for the button to become enabled (if needed)
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("(//form[@id='cart-drawer-phone-login-form']//button[contains(@class,'continue--button')])[1]")
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