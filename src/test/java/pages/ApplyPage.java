package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Utils;

public class ApplyPage extends BasePage{

    By firstNameTxt=By.id("candidate_first_name");
    By lastNameTxt=By.id("candidate_last_name");
    By emailTxt=By.id("candidate_email");
    By phoneTxt=By.id("candidate_phone");
    By resumeFile=By.id("candidate_resume_remote_url");
    By additionalFile=By.id("candidate_file_remote_url");
    By coverLetterTxt=By.id("candidate_job_applications_attributes_0_cover_letter");
    By applyBtn=By.name("commit");


    public By getFirstNameTxt() {
        return firstNameTxt;
    }

    public By getLastNameTxt() {
        return lastNameTxt;
    }

    public By getEmailTxt() {
        return emailTxt;
    }

    public By getPhoneTxt() {
        return phoneTxt;
    }

    public By getResumeFile() {
        return resumeFile;
    }

    public By getAdditionalFile() {
        return additionalFile;
    }

    public By getCoverLetterTxt() {
        return coverLetterTxt;
    }


    public void enterFirstName(String firstName)
    {
        waitUntilElementIsVisible(firstNameTxt).sendKeys(firstName);
    }

    public void enterLastName(String lastName)
    {
        waitUntilElementIsVisible(lastNameTxt).sendKeys(lastName);
    }

    public void enterEmail(String email)
    {
        waitUntilElementIsVisible(emailTxt).sendKeys(email);
    }

    public void enterPhone(String phone)
    {
        waitUntilElementIsVisible(phoneTxt).sendKeys(phone);
    }

    public void uploadResume()
    {
        waitUntilElementIsVisible(resumeFile).sendKeys(Utils.getResourceFilePath("resume.pdf"));

    }

    public void uploadAdditionalFiles()
    {
        waitUntilElementIsVisible(additionalFile).sendKeys(Utils.getResourceFilePath("coverletter.pdf"));
    }

    public void enterCoverLetter(String coverLetter)
    {
        waitUntilElementIsVisible(coverLetterTxt).sendKeys(coverLetter);
    }

    public void clickApplyBtn()
    {
        waitUntilElementToBeClickable(applyBtn).click();
    }
    public void emailInputIsInvalid()
    {
        WebElement email=driver.findElement(emailTxt);
        boolean valid = Utils.isFromInputValid(driver,email);
        Assertions.assertFalse(valid,"Invalid email format is allowed to be passed through the form");
    }
}

