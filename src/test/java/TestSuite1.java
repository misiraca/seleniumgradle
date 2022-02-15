import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.ApplyPage;
import pages.JobsPage;
import utils.Hooks;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(Hooks.class)
public class TestSuite1 {
    JobsPage jobsPage;
    ApplyPage applyPage;

    @BeforeEach
    public void preconditionSteps() {
        jobsPage = new JobsPage();
        applyPage = new ApplyPage();

        jobsPage.navigateToTheJobsPage();
        jobsPage.clickDepartmentMenuItem();
        jobsPage.selectValueFromDepartmentList("QA");
        jobsPage.clickLocationMenuItem();
        jobsPage.selectValueFromLocationList("Belgrade");
    }

    @Test
    @DisplayName("Verify that Senior QA Engineer is in the list with tags Belgrade and QA")
    public void test1() {
        Assertions.assertTrue(jobsPage.isSkyTeamPositionVisible(), "SkyTeam position is not in the list");
        Assertions.assertEquals("QA · Belgrade", jobsPage.getTags(), "Job's description doesn't contains QA tag");
        //verify that Senior SDET Engineer title is git bold
        Assertions.assertTrue(jobsPage.getFontWeightValue() >= 400, "Senior SDET Engineer is not bolded");
    }

    @Test
    @DisplayName("List all QA open job positions in Belgrade")
    public void test2() {
        //Find and list all open positions
        System.out.println(Arrays.toString(jobsPage.allOpenPositions().toArray()));
    }

    @Test
    @DisplayName("Apply for the SDET Engineer - SkyTeam Team position in Belgrade")
    public void test3() {
        jobsPage.clickSkyTeamTeamPosition();
        jobsPage.clickClickApplyThisJob();

        applyPage.enterFirstName("Dragutin");
        applyPage.enterLastName("Misiraca");
        applyPage.enterEmail("dragutin91@gmail.com");
        applyPage.enterPhone("+38169123456");
        applyPage.uploadResume();
        applyPage.uploadAdditionalFiles();
        applyPage.enterCoverLetter("Cover's letter text");
    }


    @Test
    @Tag("smoke")
    @DisplayName("Smoke test - Verify that there are all input form elements on the apply page")
    public void validateVisibilityOfFormElements() {
        jobsPage.clickSkyTeamTeamPosition();
        jobsPage.clickClickApplyThisJob();

        assertAll("Verify form elements",
                () -> assertTrue(applyPage.isElementVisible(applyPage.getFirstNameTxt()), "First name input field is not visible"),
                () -> assertTrue(applyPage.isElementVisible(applyPage.getLastNameTxt()), "Last name input field is not visible"),
                () -> assertTrue(applyPage.isElementVisible(applyPage.getEmailTxt()), "Email input field is not visible"),
                () -> assertTrue(applyPage.isElementVisible(applyPage.getPhoneTxt()), "Phone input field is not visible"),
                () -> assertTrue(applyPage.isElementVisible(applyPage.getResumeFile()), "Resume file  field is not visible"),
                () -> assertTrue(applyPage.isElementVisible(applyPage.getAdditionalFile()), "Additional file  field is not visible"),
                () -> assertTrue(applyPage.isElementVisible(applyPage.getCoverLetterTxt()), "Cover letter field is not visible")
        );
    }

    @Test
    @DisplayName("Verify that invalid email address is not allowed")
    public void test4() {
        preconditionSteps();
        jobsPage.clickSkyTeamTeamPosition();
        jobsPage.clickClickApplyThisJob();
        applyPage.enterEmail("invalidemail");
        applyPage.emailInputIsInvalid();
        applyPage.clickApplyBtn();
    }
}
