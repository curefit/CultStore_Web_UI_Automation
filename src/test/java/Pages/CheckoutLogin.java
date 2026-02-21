package Pages;

import Test.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;


public class CheckoutLogin extends BaseTest {



    public WebDriver driver;

    public CheckoutLogin(WebDriver driver) {
        this.driver = driver;
    }

    By gymequipmentLink = By.linkText("Gym Equipment");

    public void clickGymEquipment() {
        driver.findElement(gymequipmentLink).click();
    }

    By exerciseBike = By.xpath("(//div[normalize-space()='Exercise Cycles'])[1]");

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

    By checkoutButton = By.xpath("//button[normalize-space()='Checkout']");

    public void clickCheckoutButton() {
        driver.findElement(checkoutButton).click();
    }

    // Java
    By phoneInputXpath = By.xpath("//div[@class='rs-cart-drawer__login rs-login-modal']//input[@placeholder='Enter your phone number']");
    By continueBtnXpath = By.xpath("//button[contains(@class, 'continue--button') and text()='Continue']\n");
    By enterOtpTextXpath = By.xpath("//h2[@class='h4 otp-title' and text()='Enter OTP']");
    By confirmOtpBtnXpath = By.xpath("//cart-drawer-otp-confirm-button[contains(@class, 'otp--confirm__button') and normalize-space(text())='Confirm']");

    public void enterPhoneNumber(String phoneNumber) {
        driver.findElement(phoneInputXpath).clear();
        driver.findElement(phoneInputXpath).sendKeys(phoneNumber);
    }

    public void clickContinueBtn() {
        driver.findElement(continueBtnXpath).click();
    }

    public boolean isOtpScreenDisplayed() {
        try {
            return driver.findElement(enterOtpTextXpath).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void enterOtpDigits(String otp) {
        for (int i = 1; i <= 6; i++) {
            WebElement otpInput = driver.findElement(By.id("otp-digit-" + i));
            otpInput.clear();
            otpInput.sendKeys(String.valueOf(otp.charAt(i - 1)));
        }
    }

    public void clickConfirmOtpBtn() {
        driver.findElement(confirmOtpBtnXpath).click();
    }



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



    public boolean isEnterOtpDisplayed() {
        try {
            WebElement otpTitle = driver.findElement(By.xpath("(//h2[@class='h4 otp-title' and text()='Enter OTP'])[1]"));
            return otpTitle.isDisplayed() && "Enter OTP".equals(otpTitle.getText().trim());
        } catch (NoSuchElementException e) {
            return false;
        }
    }


}


