package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

import java.time.Duration;

import static Test.BaseTest.driver;

public class CheckoutLogin {


    By gymequipmentLink = By.linkText("Gym Equipment");

    public CheckoutLogin(WebDriver driver) {
    }

    public void clickGymEquipment() {
        driver.findElement(gymequipmentLink).click();
    }

    By exerciseBike = By.xpath("(//div[normalize-space()='Exercise Bikes'])[1]");

    public void clickExerciseBike() {
        driver.findElement(exerciseBike).click();
    }


   By bikeexercise = By.xpath("(//div[normalize-space()='Smartbike Danville | 6.5kg Flywheel | Max Weight-130kg | 100 Level Magnetic Resistance (with 6 months extended warranty)'])[1]");

    public void clickBikeExercise() {
        driver.findElement(bikeexercise).click();
    }

    By addcart = By.xpath("//button[@class='btn btn--large add-to-cart' and @type='submit' and @name='add' and @data-event-type='add-to-cart' and @data-event-name='Add to Cart']");

    public void clickaddcart()
    {
        driver.findElement(addcart).click();
    }



    By cartButton = By.xpath("(//span[@class='cart-link__icon'])[1]");
    public void clickCartButton() {
        driver.findElement(cartButton).click();
    }

    By checkoutButton = By.xpath("(//marmeto-checkout-button[@class='btn btn--large btn--wide marmeto-button--loading'])[1]");

    public void clickCheckoutButton() {
        driver.findElement(checkoutButton).click();
    }

    // This is the practise code ************************//

    // In CheckoutLogin.java
    public void completeCheckoutWithOtpTextValidation(String phoneNumber, String otp) {
        By phoneInputXpath = By.xpath("(//input[@id='cartDrawerPhoneNumber'])[1]");
        By continueBtnXpath = By.xpath("//button[@class='button continue--button btn btn--primary cart-drawer-phone-login-button marmeto-button--loading' and @type='submit' and @data-event-type='submit' and @data-event-name='Continue Button Click' and normalize-space(text())='Continue']");
        By enterOtpTextXpath = By.xpath("//h2[@class='h4 otp-title' and text()='Enter OTP']");
        By confirmOtpBtnXpath = By.xpath("//cart-drawer-otp-confirm-button[contains(@class, 'otp--confirm__button') and normalize-space(text())='Confirm']");
        By cartButton = By.xpath("(//span[@class='cart-link__icon'])[1]");

        int retryCount = 0;
        int maxRetries = 2;
        boolean otpScreenVisible = false;

        while (!otpScreenVisible && retryCount < maxRetries) {
            driver.findElement(phoneInputXpath).clear();
            driver.findElement(phoneInputXpath).sendKeys(phoneNumber);
            driver.findElement(continueBtnXpath).click();

            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
                WebElement otpTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(enterOtpTextXpath));
                otpScreenVisible = otpTitle.isDisplayed();
            } catch (Exception e) {
                otpScreenVisible = false;
            }

            if (!otpScreenVisible) {
                driver.navigate().refresh();
                driver.findElement(cartButton).click();
                retryCount++;
            }
        }

        if (otpScreenVisible) {
            try { Thread.sleep(5000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            for (int i = 1; i <= 6; i++) {
                WebElement otpInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("otp-digit-" + i)));
                otpInput.clear();
                otpInput.sendKeys(String.valueOf(otp.charAt(i - 1)));
            }
            try { Thread.sleep(2000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            driver.findElement(confirmOtpBtnXpath).click();
        } else {
            throw new RuntimeException("Failed to reach OTP screen after retries.");
        }
    }












   //*********************************************************************************************//

// The oiginal code which is commented
  /*  public void clickCheckoutButton() {
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

    public boolean isLoginToCheckoutDisplayed() {
        try {
            WebElement loginTitle = driver.findElement(By.xpath("//div[@class='h4 login-title']"));  //(//div[@class='h4 login-title'])[1]
            return loginTitle.isDisplayed() && "Login to checkout".equals(loginTitle.getText().trim());
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void enterPhoneNumber(String phoneNumber) {
        try {
            WebElement phoneInput = driver.findElement(By.xpath("(//input[@id='cartDrawerPhoneNumber'])[1]"));
            phoneInput.clear();
            phoneInput.sendKeys(phoneNumber);
            System.out.println("Entered phone number.");
        } catch (Exception e) {
            System.out.println("Failed to enter phone number: " + e.getMessage());
        }
    }

    By continuebt = By.xpath("//button[@class='button continue--button btn btn--primary cart-drawer-phone-login-button marmeto-button--loading' and @type='submit' and @data-event-type='submit' and @data-event-name='Continue Button Click' and normalize-space(text())='Continue']");

    public void clickContinue() {
        driver.findElement(continuebt).click();
    }

    public void enterOtp(String otp) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        for (int i = 1; i <= 6; i++) {
            WebElement otpInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id("otp-digit-" + i)
            ));
            otpInput.clear();
            otpInput.sendKeys(String.valueOf(otp.charAt(i - 1)));
        }
        System.out.println("Entered OTP: " + otp);
    }

    By confirmotop = By.xpath("//cart-drawer-otp-confirm-button[contains(@class, 'otp--confirm__button') and normalize-space(text())='Confirm']");

    public void clickConfirmOtp() {
        driver.findElement(confirmotop).click();
    }*/



















    public void verifyCheckoutElements() {
        String[] expectedTexts = {
                "DELIVERY ADDRESS",
                "", // po-header
                "", // offers-section
                "", // SUGGESTED-pil-UPI
                "Credit / Debit Card",
                "EMI OPTIONS"
        };

        By[] xpaths = {
                By.xpath("//div[@class='address-heading svelte-burbay' and normalize-space(text())='DELIVERY ADDRESS']"),
                By.xpath("(//div[@class='po-header svelte-19408u8'])[1]"),
                By.xpath("(//div[@class='offers-section svelte-shexhk'])[1]"),
                By.xpath("(//div[@data-pw='SUGGESTED-pil-UPI'])[1]"),
                By.xpath("(//div[contains(text(),'Credit / Debit Card')])[1]"),
                By.xpath("(//div[normalize-space()='EMI OPTIONS'])[1]")
        };

        for (int i = 0; i < xpaths.length; i++) {
            WebElement element = driver.findElement(xpaths[i]);
            if (!expectedTexts[i].isEmpty()) {
                Assert.assertEquals(element.getText().trim(), expectedTexts[i], "Text mismatch for element " + i);
            } else {
                Assert.assertTrue(element.isDisplayed(), "Element not displayed for index " + i);
            }
        }
    }






  /*  public void enterPhoneAndContinue(String phoneNumber) {
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
    }*/


    public boolean isEnterOtpDisplayed() {
        try {
            WebElement otpTitle = driver.findElement(By.xpath("(//h2[@class='h4 otp-title' and text()='Enter OTP'])[1]"));
            return otpTitle.isDisplayed() && "Enter OTP".equals(otpTitle.getText().trim());
        } catch (NoSuchElementException e) {
            return false;
        }
    }


}


