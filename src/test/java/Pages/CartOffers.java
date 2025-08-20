package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static Test.BaseTest.driver;

public class CartOffers {

    By searchBoxicon = By.xpath("(//span[@class='show-search-link__icon'])[2]"); // Click on search icon homescreen

    public void clickSearchBoxIcon() {

        driver.findElement(searchBoxicon).click();
    }

    By inputsearchbox = By.xpath("//input[@class='main-search__input']");// Click on input search box to enter the text

    public void enterSearchText(String text) {
        driver.findElement(inputsearchbox).sendKeys("Smartrun Carson 5.5 HP Peak Treadmill");
    }

    By searchfor = By.xpath("(//button[@aria-label='Search'])[1]");// Click on the search button near the inoput box

    public void clickSearchFor() {
        driver.findElement(searchfor).click();
    }

    By viewResult = By.linkText("View all search results"); // After entering the text View result button should be enable to click on that

    public void clickViewResult() {
        driver.findElement(viewResult).click();
    }

    By treadmill1 = By.xpath("(//div[normalize-space()='Smartrun Carson 5.5 HP Peak Treadmill | 15-level Auto-Incline | Max Weight-130kg | Max Speed-16kmph (with 6 Months Extended Warranty)'])[1]");

    public void clickTreadmill1() {
        driver.findElement(treadmill1).click();
    }


    By addToCartButton = By.xpath("(//button[@class='btn btn--large add-to-cart'])[1]");

    public void clickAddToCartButton() {
        driver.findElement(addToCartButton).click();
    }


    By inputsearchbox1 = By.xpath("(//input[@placeholder='Search for'])[1]");

    public void enterSearchText1(String text) {
        driver.findElement(inputsearchbox1).sendKeys("Shoulder Pop Active T-shirt");
    }

    By greentshirt = By.xpath("(//div[@class='product-block__title'][normalize-space()='Shoulder Pop Active T-shirt'])[1]");

    public void clickGreenshirt() {
        driver.findElement(greentshirt).click();
    }

    By addToCartButton2 = By.xpath("(//button[@class='btn btn--large add-to-cart'])[1]");

    public void clickAddToCartButton2() {
        driver.findElement(addToCartButton2).click();
    }

    By tshirtsize = By.xpath("//label[@class='opt-label opt-label--btn btn relative text-center' and @for='product-form-template--18929590304936__main-8625439998120-size-opt-3']//span[@class='js-value' and text()='XL']");

    public void selectTshirtSize() {
        driver.findElement(tshirtsize).click();
    }

    By inputsearchbox2 = By.xpath("(//input[@placeholder='Search for'])[1]");

    public void enterSearchText2(String text) {
        driver.findElement(inputsearchbox2).sendKeys("Women's Black Training Essential Bra");
    }

    By sportsbra = By.xpath("//div[@class='product-block__title' and text()=\"Women's Black Training Essential Bra\"]");

    public void clickSportsBra() {
        driver.findElement(sportsbra).click();
    }

    By Brasize = By.xpath("(//label[@for='product-form-template--18929590304936__main-8625312432296-size-opt-1'])[1]");

    public void selectBraSize() {
        driver.findElement(Brasize).click();
    }

    By inputsearchbox3 = By.xpath("(//input[@placeholder='Search for'])[1]");

    public void enterSearchText3(String text) {
        driver.findElement(inputsearchbox2).sendKeys("Cult Apex Massage Chair with Zero Gravity");
    }

    By massagechair = By.xpath("//div[@class='product-block__title' and text()='Cult Apex Massage Chair with Zero Gravity, SL Track 2D Massage Technique and Bluetooth AI voice Function For Full Body Massage At Home']");

    public void clickMassageChair() {
        driver.findElement(massagechair).click();
    }

    By clickoncart = By.xpath("(//span[@class='cart-link__icon'])[1]");

    public void clickOnCart() {
        driver.findElement(clickoncart).click();
    }

    // Click on more offers button

    By moreOffersButton = By.xpath("(//span[@class='btn'])[1]");

    public void clickMoreOffersButton() {
        driver.findElement(moreOffersButton).click();
    }

    // Validate the Offers in the cart are persent or not and print the offefrs
    public void printCartOffers() {
        String[] offerXpaths = {
                "(//div[normalize-space()='RELAX20K'])[1]",
                "(//div[normalize-space()='RUN7K'])[1]",
                "(//div[normalize-space()='RUN4K'])[1]",
                "(//div[normalize-space()='RUN1K'])[1]",
                "(//div[normalize-space()='WELCOME15'])[1]"
        };

        for (String xpath : offerXpaths) {
            WebElement offerElement = driver.findElement(By.xpath(xpath));
            String offerText = offerElement.getText();
            System.out.println("Offer: " + offerText);
            // Add assertions or validations if needed
            if (offerText.isEmpty()) {
                System.out.println("No offers available.");
            } else {
                System.out.println("Offer is available: " + offerText);
            }
        }
    }


    public void applyRelax20KOfferIfAvailable() {
        List<WebElement> relaxOffer = driver.findElements(By.xpath("(//div[normalize-space()='RELAX20K'])[1]"));
        if (!relaxOffer.isEmpty()) {
            List<WebElement> applyButtons = driver.findElements(By.className("apply-btn-text"));
            applyButtons.get(1).click(); // Clicks the third "APPLY" button

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement offerPopup = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@class='cart-offer-applied__title h4 heading-font' and normalize-space()='₹20000 saved']")
            ));

            System.out.println("Offer applied pop-up: " + offerPopup.getText());
        } else {
            System.out.println("RELAX20K offer not available.");
        }
    }

    public void applyRun7KOfferIfAvailable() {
        List<WebElement> run7KOffer = driver.findElements(By.xpath("(//div[normalize-space()='RUN7K'])[1]"));
        if (!run7KOffer.isEmpty()) {
            List<WebElement> applyButtons = driver.findElements(By.className("apply-btn-text"));
            applyButtons.get(2).click(); // Clicks the fourth "APPLY" button

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement offerPopup = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@class='cart-offer-applied__title h4 heading-font' and normalize-space()='₹7000 saved']")
            ));

            System.out.println("Offer applied pop-up: " + offerPopup.getText());
        } else {
            System.out.println("RUN7K offer not available.");
        }
    }

    public void applyRun4KOfferIfAvailable() {
        List<WebElement> run4KOffer = driver.findElements(By.xpath("(//div[normalize-space()='RUN4K'])[1]"));
        if (!run4KOffer.isEmpty()) {
            List<WebElement> applyButtons = driver.findElements(By.className("apply-btn-text"));
            applyButtons.get(3).click(); // Clicks the fifth "APPLY" button

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement offerPopup = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@class='cart-offer-applied__title h4 heading-font' and normalize-space()='₹4000 saved']")
            ));

            System.out.println("Offer applied pop-up: " + offerPopup.getText());
        } else {
            System.out.println("RUN4K offer not available.");
        }
    }

    public void applyRun1KOfferIfAvailable() {
        List<WebElement> run1KOffer = driver.findElements(By.xpath("(//div[normalize-space()='RUN1K'])[1]"));
        if (!run1KOffer.isEmpty()) {
            List<WebElement> applyButtons = driver.findElements(By.className("apply-btn-text"));
            applyButtons.get(4).click(); // Clicks the sixth "APPLY" button

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement offerPopup = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@class='cart-offer-applied__title h4 heading-font' and normalize-space()='₹1000 saved']")
            ));

            System.out.println("Offer applied pop-up: " + offerPopup.getText());
        } else {
            System.out.println("RUN1K offer not available.");
        }
    }

    public void applyWelcome15OfferIfAvailable() {
        List<WebElement> welcome15Offer = driver.findElements(By.xpath("(//div[normalize-space()='WELCOME15'])[1]"));
        if (!welcome15Offer.isEmpty()) {
            List<WebElement> applyButtons = driver.findElements(By.className("apply-btn-text"));
            applyButtons.get(5).click(); // Clicks the seventh "APPLY" button

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement offerPopup = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@class='cart-offer-applied__title h4 heading-font' and normalize-space()='₹500 saved']")
            ));

            System.out.println("Offer applied pop-up: " + offerPopup.getText());
        } else {
            System.out.println("WELCOME15 offer not available.");
        }
    }


}




















































