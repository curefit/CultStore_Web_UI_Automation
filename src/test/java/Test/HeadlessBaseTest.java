package Test;

import Pages.CartCheckout;
import Pages.CartEmpty;
import Pages.HomePage;
import Pages.ValidateServicibility;
import Pages.VerifyPDPComponents;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;

//import static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.header;

public class HeadlessBaseTest {
    public static WebDriver driver;
    HomePage homePage;
    CartCheckout cartCheckout;
    ValidateServicibility validateServicibility;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() {


        // Set up ChromeDriver with options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--incognito");

        // Additional code for the headless mode
        options.addArguments("--headless"); // Add headless mode
        options.addArguments("--no-sandbox"); // Sandbox typically required in CI environments
        options.addArguments("--disable-dev-shm-usage");

        // Initialize WebDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().deleteAllCookies();

        // Initialize WebDriverWait
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Navigate to the website
        driver.get("https://cultstore.com/");

        // Initialize page objects
        homePage = new HomePage(driver);
        cartCheckout = new CartCheckout(driver);

    }

    @Test(priority = 1)
    void validateTitles_Products() {
        // This is the code for headless Automation mode
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("New Arrivals"))).click();
        try { Thread.sleep(3000);} catch (InterruptedException e) {Thread.currentThread().interrupt();}
        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//h1[normalize-space()='New Arrivals'])[1]")));
        Assert.assertEquals(titleElement.getText(), "New Arrivals", "Title does not match"); //(//h1[normalize-space()='New Arrivals'])[1]

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Men"))).click();
        try { Thread.sleep(3000);} catch (InterruptedException e) {Thread.currentThread().interrupt();}
        titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//h1[normalize-space()=\"Men's Wear\"])[1]")));
        Assert.assertEquals(titleElement.getText(), "Men's Wear", "Title does not match");

      /*  wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Women"))).click();
        try { Thread.sleep(3000);} catch (InterruptedException e) {Thread.currentThread().interrupt();}
        titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//h1[normalize-space()=\"Women's Wear\"])[1]")));
        Assert.assertEquals(titleElement.getText(),"Women's Wear", "Title does not match"); //(//h1[normalize-space()="Women's Wear"])[1]*/

     /*   wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Apparel"))).click();
        try { Thread.sleep(5000);} catch (InterruptedException e) {Thread.currentThread().interrupt();}
        titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//h1[normalize-space()='Apparel'])[1]")));
        Assert.assertEquals(titleElement.getText(), "Apparel", "Title does not match"); //(//h1[normalize-space()='Apparel'])[1]*/



        // Continue similarly for other interactions in the test
    }
}