package listeners;

import base.BaseTest;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

public class TestListener implements ITestListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test=new ThreadLocal<>();

    static {
        String reportPath=System.getProperty("user.dir") + "/reports/extent-report.html";

        ExtentSparkReporter reporter=new ExtentSparkReporter(reportPath);
        reporter.config().setReportName("ShopBot Automation Report");
        reporter.config().setDocumentTitle("Test Results");

        extent=new ExtentReports();
        extent.attachReporter(reporter);
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest=extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().fail(result.getThrowable());

        try {
            WebDriver driver=BaseTest.getDriver();

            String timestamp=new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

            String screenshotPath=System.getProperty("user.dir")+ "/screenshots/"+ result.getMethod().getMethodName()+ "_" + timestamp + ".png";

            File src=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest=new File(screenshotPath);

            dest.getParentFile().mkdirs();
            FileUtils.copyFile(src, dest);

            test.get().addScreenCaptureFromPath(screenshotPath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}