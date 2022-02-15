package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Driver {

    public static WebDriver driver;

    public static void startDriver() {

        switch (ConfigProperties.BROWSER_NAME.toLowerCase())
        {
            case "chrome":
                driver = getChromeDriver();
                break;
            case "safari":
                driver=getSafariDriver();
                break;
            case "firefox":
                driver=getFirefoxDriver();
                break;
            default:
                driver=getChromeDriver();
                break;
        }

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    public static void closeDriver() {
        driver.quit();
    }

    private static ChromeDriver getChromeDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
    private static FirefoxDriver getFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }
    private static SafariDriver getSafariDriver() {
        WebDriverManager.safaridriver().setup();
        return new SafariDriver();
    }
}