package reporters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.Utils;

import java.io.IOException;

//https://extentreports.com/docs/versions/2/java/
public class ExtentReporter implements ITestListener {

    public static ExtentHtmlReporter htmlReporter;
    public static ExtentTest test;
    public static ExtentReports extent;
    public static WebDriver driver;

    public static void assignDriver(WebDriver tDriver){
        driver = tDriver;

    }

    public static void startReporter() {

        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/ExtentReportResults.html");
        htmlReporter.config().setDocumentTitle("Automation Execution Report");
        htmlReporter.config().setReportName("Ecommerce Test Report");
        htmlReporter.config().setTheme(Theme.DARK);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Hostname", "Mitul's PC");
        extent.setSystemInfo("OS", "WIN10");
        extent.setSystemInfo("SDET", "Mitulsinh Vaghela");
    }

    public static ExtentTest startTest(String testName) {
        return extent.createTest(testName);

    }

    public static void GenerateReport() {
        extent.flush();

    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        test = ExtentReporter.startTest(iTestResult.getName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        test.log(Status.PASS, "Test case is pass");

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        test.log(Status.FAIL, iTestResult.getThrowable());
        try {
            String screenshotPath = Utils.getScreenshot(driver,iTestResult.getName());
            test.addScreenCaptureFromPath(screenshotPath);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        ExtentReporter.startReporter();

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        ExtentReporter.GenerateReport();

    }
}