package Pages;

import Test.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BaseTest {
    WebDriver driver;

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Locator for the "New Arrivals" link
    By newArrivalsLink = By.linkText("New Arrivals");

    // Method to click on the "New Arrivals" link
    public void clickNewArrivals() {
        driver.findElement(newArrivalsLink).click();

    }

    // Locator for "Men" link
    By mensLink = By.linkText("Men");

    // Method to click on the "Men" link
    public void clickMen() {
        driver.findElement(mensLink).click();
    }

    // Locator for "Women" link
    By womensLink = By.linkText("Women");

    // Method to click on the "Women" link
    public void clickWomen() {
        driver.findElement(womensLink).click();
    }

    // Locator for "Apparel" link
    By apparelLink = By.linkText("Apparel");

    // Method to click on the "Apparel" link
    public void clickApparel() {
        driver.findElement(apparelLink).click();
    }

    // Locator for "Footwear" link
    By footwearLink = By.linkText("Footwear");

    // Method to click on the "Footwear" link
    public void clickFootwear() {
        driver.findElement(footwearLink).click();
    }

    // Locator for "Gym Equipment" link
    By gymEquipmentLink = By.linkText("Gym Equipment");

    // Method to click on the "Gym Equipment" link
    public void clickGymEquipment() {
        driver.findElement(gymEquipmentLink).click();
    }

    // Locator for "Massagers" link
    By massagersLink = By.linkText("Massagers");

    // Method to click on the "Massagers" link
    public void clickMassagers() {
        driver.findElement(massagersLink).click();
    }

    // Locator for "Accessories" link
    By accessoriesLink = By.linkText("Accessories");

    // Method to click on the "Accessories" link
    public void clickAccessories() {
        driver.findElement(accessoriesLink).click();
    }

    // Locator for "Cycles" link
    By cyclesLink = By.linkText("Cycles");

    // Method to click on the "Cycles" link
    public void clickCycles() {
        driver.findElement(cyclesLink).click();
    }

    // Locator for "Shop by Activity" link
    By shopByActivityLink = By.linkText("Shop by Activity");

    // Method to click on the "Shop by Activity" link
    public void clickShopByActivity() {
        driver.findElement(shopByActivityLink).click();
    }

    // Locator for "Store Locator" link
    By storeLocatorLink = By.linkText("Store Locator");

    // Method to click on the "Store Locator" link
    public void clickStoreLocator() {
        driver.findElement(storeLocatorLink).click();
    }

    By bulkOrdersLink = By.linkText("Bulk Orders");

    public void clickBulkOrders() {
        driver.findElement(bulkOrdersLink).click();
    }





}