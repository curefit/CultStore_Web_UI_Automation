package Test.ExtentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExtentReportListener implements ITestListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        Path reportDirectory = Paths.get("target", "extent-report");
        Path reportPath = reportDirectory.resolve("extentReport.html");

        try {
            Files.createDirectories(reportDirectory);
        } catch (Exception e) {
            throw new RuntimeException("Unable to create Extent report directory: " + reportDirectory, e);
        }

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath.toString());
        sparkReporter.config().setReportName("Automation Test Results");
        sparkReporter.config().setDocumentTitle("Test Report");
        sparkReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Environment", "Production");
        extent.setSystemInfo("Tester", "Abhishek Telange");

        String buildNumber = System.getenv("BUILD_NUMBER");
        if (buildNumber != null && !buildNumber.isBlank()) {
            extent.setSystemInfo("Jenkins Build", buildNumber);
        }

        String jobName = System.getenv("JOB_NAME");
        if (jobName != null && !jobName.isBlank()) {
            extent.setSystemInfo("Jenkins Job", jobName);
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
        }
    }
}