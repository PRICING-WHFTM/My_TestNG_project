package com.business.Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners implements ITestListener {
    public ExtentReports extent = ExtentReportUtil.extentReportGenerator();
    public ExtentTest test;
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Passed");
    }

    public void onTestFailure(ITestResult result) {
        String screenshotLocation = BrowserUtil.getScreenshotPath(result.getName());
        extentTest.get().log(Status.FAIL, "Failed");
        extentTest.get().fail(result.getThrowable());
        try {
            extentTest.get().addScreenCaptureFromPath(screenshotLocation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onFinish(ITestContext context) {
        extent.flush();
    }


}
