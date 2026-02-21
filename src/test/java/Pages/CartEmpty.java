package Pages;

import Test.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class CartEmpty extends BaseTest {
    WebDriver driver;

    private final By cartIcon = By.xpath("(//span[@class='cart-link__icon'])[1]");
    private final By emptyCartText = By.xpath("//h2[normalize-space()='Your cart is empty']");
    private final By backToHomepageBtn = By.xpath("//a[normalize-space()='Continue Shopping']");

    public CartEmpty(WebDriver driver) {
        this.driver = driver;
       // super();
    }

    public void clickCartIcon() {
        driver.findElement(cartIcon).click();
    }

    public void verifyEmptyCartText() {
        WebElement textElement = driver.findElement(emptyCartText);
        Assert.assertTrue(textElement.isDisplayed(), "Empty cart text is not displayed!");
        Assert.assertEquals(textElement.getText().trim(), "Your cart is empty");
    }

    public void clickBackToHomepage() {
        driver.findElement(backToHomepageBtn).click();
    }


}
