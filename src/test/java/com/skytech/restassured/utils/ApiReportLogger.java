package com.skytech.restassured.utils;

import com.aventstack.extentreports.ExtentTest;
import io.restassured.response.Response;

public final class ApiReportLogger {

    private ApiReportLogger() {
    }

    public static void logRequest(
            ExtentTest test,
            String method,
            String endpoint,
            Object requestBody) {

        test.info(
                "<b>Request Method:</b> "
                + method);

        test.info(
                "<b>Endpoint:</b> "
                + endpoint);

        if (requestBody != null) {

            test.info(
                    "<b>Request Body:</b><pre>"
                    + requestBody
                    + "</pre>");
        }
    }

    public static void logResponse(
            ExtentTest test,
            Response response) {

        test.info(
                "<b>Status Code:</b> "
                + response.statusCode());

        test.info(
                "<b>Response Body:</b><pre>"
                + response.asPrettyString()
                + "</pre>");
    }
}