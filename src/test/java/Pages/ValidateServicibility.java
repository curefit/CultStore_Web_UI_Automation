package Pages;

import Test.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ValidateServicibility extends BaseTest {


    WebDriver driver;

    // Constructor
    public ValidateServicibility(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By gymEquipmentLink = By.linkText("Gym Equipment");
    By filterButton = By.xpath("(//span[contains(text(),'Filter')])[1]");
    By CategorySection = By.xpath("(//div[normalize-space()='Category'])[1]");
    By traidmillsCheckbox = By.xpath("(//span[@class='filter-group__item__text'][normalize-space()='Treadmill'])[1]");
    By brandSection = By.xpath("(//div[normalize-space()='Brand'])[1]");
    By cultCheckbox = By.xpath("(//span[normalize-space()='cult'])[1]");


    public void clickGymEquipment() {
        driver.findElement(gymEquipmentLink).click();
    }

    public void clickFilter() {
        driver.findElement(filterButton).click();
    }
    public void expandCategorySection() {
        driver.findElement(CategorySection).click();
    }

    public void clickTreadmillsCheckbox() {
        driver.findElement(traidmillsCheckbox).click();
    }

    public void expandBrandSection() {
        driver.findElement(brandSection).click();
    }

    public void clickCultCheckbox() {
        driver.findElement(cultCheckbox).click();
    }

    public void selectAvailabletreadmill() {
        boolean treadmillFound = false;
        for (int i = 1; i <= 5; i++) {
            String xpath = "(//div[contains(text(),'Smartrun Davie 7 HP Peak Treadmill | 15-level Auto')])[1][" + i + "]"; // (//div[contains(text(),'Smartrun Davie 7 HP Peak Treadmill | 15-level Auto')])[1]
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

    // The following code will require in the funture.
  /*  public void scrollToText(String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //By locator = By.xpath("((//summary[normalize-space()='Description'])[1]" + text + "']");
        By locator = By.xpath("//summary[normalize-space()='Description' and contains(., '" + text + "')]");
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        try {
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
        } catch (Exception e) {
            // Fallback to Actions if JS fails
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();
        }
    }*/

    public void enterPincode(String pincode) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By pincodeInput = By.xpath("(//input[@id='PincodeInput'])[1]");
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(pincodeInput));
        input.click();
        input.clear();
        input.sendKeys(pincode);
    }

    By cllickCheckbutton = By.xpath("(//button[normalize-space()='Check'])[1]");

    public void clickCheckButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement checkButton = wait.until(ExpectedConditions.elementToBeClickable(cllickCheckbutton));
        checkButton.click();
    }

    public boolean isServiceableMessageDisplayed() {
        try {
            WebElement element = driver.findElement(By.xpath("(//div[@class='delivery-text'])[1]"));
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isExchangeMessageDisplayed() {
        try {
            WebElement element = driver.findElement(By.xpath("(//li[normalize-space()='Easy 10 days exchange available'])[1]"));
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isPayondeliveryMessageDisplayed() {
        try {
            WebElement element = driver.findElement(By.xpath("(//li[normalize-space()='COD eligibility shown at checkout'])[1]"));
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isReturnMessageDisplayed() {
        try {
            WebElement element = driver.findElement(By.xpath("(//li[normalize-space()='No Return Available'])[1]"));
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isWarrantyMessageDisplayed() {
        try {
            WebElement element = driver.findElement(By.xpath("(//li[normalize-space()='Warranty Included'])[1]"));
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void enterPincodeunservicable(String pincode) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By pincodeInput = By.xpath("(//input[@id='PincodeInput'])[1]");
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(pincodeInput));
        input.click();
        input.clear();
        input.sendKeys(pincode);
    }


    public boolean isUnserviceableMessageDisplayed() {
        try {
            WebElement element = driver.findElement(By.xpath("(//li[@class='unserviceable'])[1]"));
            if (element.isDisplayed()) {
                System.out.println("Unserviceable message is displayed: " + element.getText());
                return true;  // Return true if the element is displayed
            } else {
                System.out.println("Unserviceable message is not displayed.");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Unserviceable message element not found.");
        }
        return false;
    }
}