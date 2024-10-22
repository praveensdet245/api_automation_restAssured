package dev.restfil.api.common.utils;

import com.aventstack.extentreports.Status;
import org.testng.*;

public class ReportListener extends TestListenerAdapter implements ISuiteListener, ITestListener {

    @Override
    public void onStart(ISuite suite) {
        String reportPath = System.getProperty("user.dir") + "/test-output/automationReport.html";
        ExtentReportSetup.setupExtentReport(reportPath);
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Start a new test in the report
        ExtentReportSetup.startTest(result.getMethod().getMethodName());
        ExtentReportSetup.logInfo("Test started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReportSetup.logInfo("Test passed: " + result.getMethod().getMethodName());
        ExtentReportSetup.getTest().log(Status.PASS, "Test Case PASSED");
        ExtentReportSetup.endTest();  // End the test
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentReportSetup.logError("Test failed: " + result.getMethod().getMethodName(), result.getThrowable());
        ExtentReportSetup.getTest().log(Status.FAIL, "Test Case FAILED");
        ExtentReportSetup.endTest();  // End the test
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReportSetup.logWarn("Test skipped: " + result.getMethod().getMethodName());
        ExtentReportSetup.getTest().log(Status.SKIP, "Test Case SKIPPED");
        ExtentReportSetup.endTest();  // End the test
    }

    @Override
    public void onFinish(ISuite suite) {
        ExtentReportSetup.teardown();  // Flush the entire report when the suite is finished
    }
}