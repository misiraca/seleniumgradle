package utils;

import com.sun.jndi.toolkit.url.Uri;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Objects;

public class Utils {

    public static String getResourceFilePath(String fileName)
    {
        ClassLoader classLoader = Utils.class.getClassLoader();
        return Objects.requireNonNull(classLoader.getResource(fileName)).getPath().replace("%20"," ");
    }

    public static void implicitWait(int milliseconds)
    {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static boolean isFromInputValid(WebDriver driver, WebElement element)
    {
       return (Boolean)((JavascriptExecutor)driver).executeScript("return arguments[0].validity.valid;", element);
    }
}