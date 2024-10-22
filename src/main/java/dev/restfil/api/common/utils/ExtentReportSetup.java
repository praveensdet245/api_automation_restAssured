package dev.restfil.api.common.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportSetup {
    public static ExtentReports extent;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static void setupExtentReport(String reportPath) {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

        sparkReporter.config().setDocumentTitle("RTZEN_API_Automation");
        sparkReporter.config().setReportName("Test Execution Report");
        sparkReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    /**
     * Start a new test in the report.
     */
    public static void startTest(String testName) {
        ExtentTest test = extent.createTest(testName);
        extentTest.set(test);  // Set the ExtentTest for the current thread
    }

    /**
     * Get the current thread's ExtentTest.
     */
    public static ExtentTest getTest() {
        return extentTest.get();
    }

    /**
     * Log information to the current test.
     */
    public static void logInfo(String infoMsg) {
        getTest().log(Status.INFO, infoMsg);
    }

    /**
     * Log warnings to the current test.
     */
    public static void logWarn(String warnMsg) {
        getTest().log(Status.WARNING, warnMsg);
    }

    /**
     * Log errors to the current test.
     */
    public static void logError(String errorMsg, Throwable throwable) {
        getTest().log(Status.FAIL, errorMsg);
        getTest().log(Status.FAIL, throwable);
    }

    /**
     * Mark the test as ended for the current thread.
     */
    public static void endTest() {
        extent.flush();  // Optional: If needed, you can flush after each test
    }

    /**
     * Flush the entire report.
     */
    public static void teardown() {
        extent.flush();
    }
}