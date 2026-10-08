package com.skytech.restassured.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.skytech.restassured.base.BaseTest;
import com.skytech.restassured.config.ExtentTestManager;
import com.skytech.restassured.service.UserService;
import com.skytech.restassured.utils.ApiReportLogger;

import io.restassured.response.Response;

public class GetAllUsersTest extends BaseTest {

    @Test
    public void getAllUsersTest() {

        ExtentTest test =
                ExtentTestManager.getTest();

        test.info(
                "Fetching all users");

        UserService userService =
                new UserService(requestSpecification);

        test.info(
                "Sending GET /users request");

        Response response =
                userService.getAllUsers();

        ApiReportLogger.logResponse(
                test,
                response);

        Assert.assertEquals(
                response.statusCode(),
                200);

        Assert.assertNotNull(
                response.jsonPath()
                        .getList("$"));

        Assert.assertTrue(
                response.jsonPath()
                        .getList("$")
                        .size() > 0,
                "User list should not be empty");

        test.pass(
                "Successfully retrieved all users");
    }
}