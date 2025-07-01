package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

class LoadTest implements Runnable {
    private String url;

    LoadTest(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");

        // Setup WebDriver
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(options);

        try {
            // Open the target URL
            driver.get("https://cultsport.com/gym-equipment");

            // Simulate user actions, such as navigating pages, clicking buttons
            WebElement element = driver.findElement(By.xpath("//h1"));
            System.out.println("Thread " + Thread.currentThread().getId() + ": " + element.getText());

            // Simulate a delay to mimic user reading or pause
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Ensure the driver is closed
            driver.quit();
        }
    }
}

public class SeleniumLoadTest {
    public static void main(String[] args) {
        String[] endpoints = {
                "https://cultsport.com/gym-machines",
                "https://cultsport.com/cycles"
                // Add more endpoints as needed
        };

        int numThreads = 100; // Number of threads to simulate users

        for (String endpoint : endpoints) {
            Thread thread = new Thread(new LoadTest(endpoint));
            thread.start();
        }
    }
}