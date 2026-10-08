package com.skytech.restassured.config;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public final class ExtentManager {

    private static ExtentReports extentReports;

    private ExtentManager() {
    }

    public static synchronized ExtentReports getExtentReports() {

        if (extentReports == null) {

        	ExtentSparkReporter sparkReporter =
        	        new ExtentSparkReporter(
        	                "D:/Test/Practical/playwright-rahulshetty/rest-assured-framework/test-output/ExtentReport.html");

            sparkReporter.config()
                    .setReportName(
                            "REST Assured Automation Report");

            sparkReporter.config()
                    .setDocumentTitle(
                            "REST Assured Automation Report");

            extentReports =
                    new ExtentReports();

            extentReports.attachReporter(
                    sparkReporter);

            extentReports.setSystemInfo(
                    "Environment",
                    "QA");

            extentReports.setSystemInfo(
                    "Framework",
                    "REST Assured");

            extentReports.setSystemInfo(
                    "Tester",
                    "Yaqoob");
        }

        return extentReports;
    }
}