package Pages;

import Test.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;



public class SearchProduct extends BaseTest {

    public WebDriver driver;

    public SearchProduct(WebDriver driver) {
        this.driver = driver;
    }



    By searchBoxicon = By.xpath("(//span[@class='show-search-link__icon'])[2]");// Click on search icon homescreen
    public void clickSearchBoxIcon() {
        driver.findElement(searchBoxicon).click();
    }

    By inputsearchbox = By.xpath("//input[@class='main-search__input']");// Click on input search box to enter the text
    public void enterSearchText(String text) {
        driver.findElement(inputsearchbox).sendKeys("bottles");
    }

    By searchfor = By.xpath("(//button[@aria-label='Search'])[1]");// Click on the search button near the inoput box
    public void clickSearchFor() {
        driver.findElement(searchfor).click();
    }

    By closeSearchBox = By.xpath("(//button[@aria-label='Close'])[1]");// Click on the cross icon to close the search box
    public void closeSearchBox() {
        driver.findElement(closeSearchBox).click();
    }

    By viewResult = By.linkText("View all search results"); // After entering the text View result button should be enable to click on that
    public void clickViewResult() {
        driver.findElement(viewResult).click();
    }

    public boolean isViewResultDisplayed() {
        try {
            return driver.findElement(By.linkText("View all search results")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // part two of the search product page
     By inputsearchbox2 = By.xpath("(//input[@placeholder='Search our store'])[1]");

     public void clickInputSearchBox2() {
        driver.findElement(inputsearchbox2).click();
     }

     public void clearSearchbox(){
         driver.findElement(By.xpath("(//input[@placeholder='Search our store'])[1]")).clear();
     }

     public void enterSearchText2(String text) {
        driver.findElement(inputsearchbox2).sendKeys("Treadmills");
     }

    public void searchMultipleProducts(WebDriver driver, String[] searchTerms) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        By searchInput = By.xpath("(//input[@placeholder='Search our store'])[1]");
        By searchButton = By.xpath("(//button[@data-event-type='submit-search'])[1]");
        
        for (String term : searchTerms) {
            // Re-find element fresh each iteration to avoid StaleElementReferenceException
            WebElement inputField = wait.until(ExpectedConditions.elementToBeClickable(searchInput));
            inputField.clear();
            inputField.sendKeys(term);
            wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
            // Wait for page to stabilize after search
            try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }
    }





}



