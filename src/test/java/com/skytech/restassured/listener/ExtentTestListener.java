package com.skytech.restassured.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;

import com.skytech.restassured.config.ExtentManager;
import com.skytech.restassured.config.ExtentTestManager;

public class ExtentTestListener implements ITestListener {

    private static final ExtentReports extent =
            ExtentManager.getExtentReports();

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTestManager.setTest(
                extent.createTest(
                        result.getMethod()
                                .getMethodName()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        ExtentTestManager
                .getTest()
                .log(
                        Status.PASS,
                        "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        ExtentTestManager
                .getTest()
                .log(
                        Status.FAIL,
                        "Test failed");

        ExtentTestManager
                .getTest()
                .fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        ExtentTestManager
                .getTest()
                .log(
                        Status.SKIP,
                        "Test skipped");
    }

    @Override
    public void onFinish(ITestContext context) {

        extent.flush();

        ExtentTestManager.removeTest();
    }
}