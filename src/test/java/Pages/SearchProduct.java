package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static Test.BaseTest.driver;

public class SearchProduct {



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
        for (String term : searchTerms) {
            driver.findElement(By.xpath("(//input[@placeholder='Search our store'])[1]")).clear();
            driver.findElement(By.xpath("(//input[@placeholder='Search our store'])[1]")).sendKeys(term);
            driver.findElement(By.xpath("(//button[@data-event-type='submit-search'])[1]")).click();
            try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }
    }





}



