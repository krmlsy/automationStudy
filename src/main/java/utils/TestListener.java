package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.extend.reports.ExtentTestManager;

public class TestListener implements ITestListener {

    public ExtentReports extent;

    @Override
    public void onTestStart(ITestResult iTestResult) {
        String methodName = iTestResult.getMethod().getConstructorOrMethod().getName();
        String testKey = methodName + "_" + iTestResult.getTestContext().getAttribute("browser").toString();

        ExtentTest extentTest = extent.createTest(testKey);
        extentTest.assignAuthor(iTestResult.getTestContext().getAttribute("author").toString());
        extentTest.assignDevice(iTestResult.getTestContext().getAttribute("browser").toString());

        ExtentTestManager.setTest(testKey, extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        String methodName = iTestResult.getMethod().getConstructorOrMethod().getName();
        String testKey = methodName + "_" + iTestResult.getTestContext().getAttribute("browser").toString();

        WebDriver webDriver = (WebDriver) iTestResult.getTestContext().getAttribute("driver");

        //Take base64Screenshot screenshot.
        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) webDriver).
                getScreenshotAs(OutputType.BASE64);

        //ExtentReports log and screenshot operations for failed tests.
        ExtentTestManager.getTest(testKey).pass("Test Passed");
        ExtentTestManager.getTest(testKey).addScreenCaptureFromBase64String(base64Screenshot);

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {

        String methodName = iTestResult.getMethod().getConstructorOrMethod().getName();
        String testKey = methodName + "_" + iTestResult.getTestContext().getAttribute("browser").toString();

        WebDriver webDriver = (WebDriver) iTestResult.getTestContext().getAttribute("driver");

        //Take base64Screenshot screenshot.
        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) webDriver).
                getScreenshotAs(OutputType.BASE64);

        //ExtentReports log and screenshot operations for failed tests.
        ExtentTestManager.getTest(testKey).fail("Test Failed");
        ExtentTestManager.getTest(testKey).fail(iTestResult.getThrowable());

        ExtentTestManager.getTest(testKey).addScreenCaptureFromBase64String(base64Screenshot);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        String methodName = iTestResult.getMethod().getConstructorOrMethod().getName();
        String testKey = methodName + "_" + iTestResult.getTestContext().getAttribute("browser").toString();

        ExtentTestManager.getTest(testKey).skip("Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReports/Spark.html");
        extent.attachReporter(spark);
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        extent.flush();
    }
}
