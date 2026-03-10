package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Utility class for common WebDriver operations.
 * Provides reusable methods for popup handling, safe clicking, etc.
 */
public class WebUtils {

    /**
     * Dismisses all MoEngage marketing popup overlays from the DOM.
     * These popups can intercept clicks on underlying elements.
     */
    public static void dismissMoEngagePopups(WebDriver driver) {
        try {
            List<WebElement> popups = driver.findElements(By.cssSelector("div[id^='moe-onsite-campaign']"));
            for (WebElement popup : popups) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].remove();", popup);
            }
        } catch (Exception e) {
            // Ignore - popup may not exist
        }
    }

    /**
     * Dismisses all common overlay popups (MoEngage, modals, etc.)
     */
    public static void dismissAllPopups(WebDriver driver) {
        dismissMoEngagePopups(driver);
        // Add other popup dismissals here as needed
        try {
            // Dismiss any generic overlay/modal backdrops
            List<WebElement> overlays = driver.findElements(By.cssSelector(
                "div[class*='overlay'], div[class*='modal-backdrop'], div[style*='z-index: 2147483647']"
            ));
            for (WebElement overlay : overlays) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].remove();", overlay);
            }
        } catch (Exception e) {
            // Ignore
        }
    }

    /**
     * Safely clicks an element by:
     * 1. Dismissing popups
     * 2. Waiting for element presence
     * 3. Scrolling into view
     * 4. Using JavaScript click to bypass overlay interception
     */
    public static void safeClick(WebDriver driver, By locator) {
        safeClick(driver, locator, 30);
    }

    /**
     * Safely clicks an element with custom timeout.
     */
    public static void safeClick(WebDriver driver, By locator, int timeoutSeconds) {
        dismissAllPopups(driver);
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        try { Thread.sleep(300); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    /**
     * Safely clicks an element with fallback locator if primary fails.
     */
    public static void safeClickWithFallback(WebDriver driver, By primaryLocator, By fallbackLocator) {
        dismissAllPopups(driver);
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement element;
        
        try {
            element = wait.until(ExpectedConditions.presenceOfElementLocated(primaryLocator));
        } catch (Exception e) {
            // Try fallback locator
            element = wait.until(ExpectedConditions.presenceOfElementLocated(fallbackLocator));
        }
        
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        try { Thread.sleep(300); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    /**
     * Waits for element and returns it, with popup dismissal.
     */
    public static WebElement waitForElement(WebDriver driver, By locator, int timeoutSeconds) {
        dismissAllPopups(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
