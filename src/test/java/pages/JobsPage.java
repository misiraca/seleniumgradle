package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ConfigProperties;
import utils.Driver;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class JobsPage extends BasePage {
    WebDriver driver;
    int initialJobsCount;

    By departmentSelect = By.xpath("//*[@data-name='department']");
    By locationSelect = By.xpath("//*[@data-name='location']");
    By departmentList = By.xpath("//ul[contains(@class,'dropdown')]");
    By SkyTeamTitle = By.xpath("//span[text()='Senior SDET Engineer - SkyTeam team']");
    By clickApplyThisJob = By.xpath("//div[contains(@class, 'apply apply__top')]//a");

    public JobsPage() {
        driver = Driver.driver;
    }

    public void navigateToTheJobsPage() {
        driver.navigate().to(ConfigProperties.ENV_FRONTEND + "jobs");
    }

    public void clickDepartmentMenuItem() {
        driver.findElement(departmentSelect).click();
    }

    public void selectValueFromDepartmentList(String depratmentName) {
        initialJobsCount = waitUntilElementsAreVisible(By.xpath("//*[contains(@class,'jobs card')]/li/a"), 5).size();
        selectValueFromList(departmentList, depratmentName);
    }

    public void clickLocationMenuItem() {
        driver.findElement(locationSelect).click();
    }

    public void selectValueFromLocationList(String locationName) {
        selectValueFromList(locationSelect, locationName);

    }

    public int getFontWeightValue() {
        return Integer.parseInt(waitUntilElementIsVisible(SkyTeamTitle).getCssValue("font-weight"));
    }

    public boolean isSkyTeamPositionVisible() {
        return isElementVisible(SkyTeamTitle);
    }

    public String getTags() {
        String te = waitUntilElementIsVisible(with(By.tagName("span")).below(SkyTeamTitle)).getText();
        return waitUntilElementIsVisible(with(By.tagName("span")).below(SkyTeamTitle)).getText(); // a lil bit of Selenium 4 new locators
    }

    public void clickSkyTeamTeamPosition() {
        driver.findElement(SkyTeamTitle).click();
    }

    public void clickClickApplyThisJob() {
        waitUntilElementToBeClickable(clickApplyThisJob).click();
    }

    public List<String> allOpenPositions() {
        int currentJobCount = initialJobsCount;

        while (currentJobCount == initialJobsCount) {
            try {
                Thread.sleep(500);
                currentJobCount = driver.findElements(By.xpath("//*[contains(@class,'jobs card')]/li/a")).size();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }

        Utils.implicitWait(1000);

        List<WebElement> liJobsElements = waitUntilElementsAreVisible(By.xpath("//*[contains(@class,'jobs card')]/li/a"), 5);
        List<String> allJobsString = new ArrayList<>();
        liJobsElements.forEach((WebElement n) -> allJobsString.add(n.getText()));

        return allJobsString;
    }

    public void selectValueFromList(By by, String itemValue) {
        WebElement dropdown = driver.findElement(by); //ul locator
        List<WebElement> options = dropdown.findElements(By.tagName("li"));
        for (WebElement li : options) {
            if (li.getText().equals(itemValue)) {
                li.click();
                break;
            }
        }
    }
}

