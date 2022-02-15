package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Hooks implements BeforeEachCallback, BeforeTestExecutionCallback, AfterEachCallback, BeforeAllCallback, AfterTestExecutionCallback, AfterAllCallback {
    static ExtentReports reports;
    static ExtentTest test;

    @Override
    public void beforeEach(ExtensionContext context) {
        Driver.startDriver();
    }

    @Override
    public void afterEach(ExtensionContext context) {
        Driver.closeDriver();
    }

    @Override
    public void beforeAll(ExtensionContext context) {

        String filename = System.getProperty("user.dir") + "/test-output/" + context.getDisplayName() + "_Results.html";
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(filename);
        reports = new ExtentReports();
        reports.attachReporter(htmlReporter);
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        if (!context.getExecutionException().isPresent()) {
            test.pass(context.getDisplayName() + " - passed");
        } else {
            test.fail(context.getExecutionException().get().getLocalizedMessage());
            File src = ((TakesScreenshot) Driver.driver).getScreenshotAs(OutputType.FILE);
            String pathname = System.getProperty("user.dir") + "/test-output/screenshoots/" + context.getDisplayName().substring(0, 10) + ".jpg";
            FileUtils.copyFile(src, new File(pathname));
            test.addScreenCaptureFromPath(pathname);
        }
    }

    @Override
    public void afterAll(ExtensionContext context) {
        reports.flush();
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) {
        test = reports.createTest(context.getDisplayName());

        test.log(Status.INFO, context.getDisplayName() + " - started");
    }
}
