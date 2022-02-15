package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigProperties;
import utils.Driver;

import java.time.Duration;
import java.util.List;

public class BasePage {


    public WebDriver driver;
    private final int DEFAULT_TIMEOUT = ConfigProperties.DEFAULT_TIMEOUT;

    public BasePage() {
        driver = Driver.driver;
    }

    public WebElement waitUntilElementIsVisible(By by) {
        return waitUntilElementIsVisible(by, DEFAULT_TIMEOUT);
    }

    public WebElement waitUntilElementIsVisible(By by, int timeOut) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public List<WebElement> waitUntilElementsAreVisible(By by, int timeOut) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeOut))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public WebElement waitUntilElementToBeClickable(By by) {
        return waitUntilElementToBeClickable(by, DEFAULT_TIMEOUT);
    }

    public WebElement waitUntilElementToBeClickable(By by, int timeOut) {

        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(by));
    }

    public boolean isElementVisible(By by) {
        try {
            WebElement element = waitUntilElementIsVisible(by, 3);
            return true;
        } catch (NoSuchElementException e) {
            System.out.println(e.getSupportUrl());
            return false;
        }
    }
}
