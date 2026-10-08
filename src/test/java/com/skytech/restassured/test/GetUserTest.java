package com.skytech.restassured.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.skytech.restassured.base.BaseTest;
import com.skytech.restassured.config.ExtentTestManager;
import com.skytech.restassured.model.UserRequest;
import com.skytech.restassured.service.UserService;
import com.skytech.restassured.utils.ApiReportLogger;
import com.skytech.restassured.utils.TestDataUtils;

import io.restassured.response.Response;

public class GetUserTest extends BaseTest {

    @Test
    public void getUserTest() {

        ExtentTest test =
                ExtentTestManager.getTest();

        UserService userService =
                new UserService(requestSpecification);

        // ------------------------------------------------
        // Step 1: Create a user
        // ------------------------------------------------

        UserRequest request =
                new UserRequest(
                        "Get Test User",
                        TestDataUtils.uniqueEmail(
                                "getuser@test.com"),
                        "male",
                        "active");

        test.info(
                "Creating test user");

        Response createResponse =
                userService.createUser(request);

        Assert.assertEquals(
                createResponse.statusCode(),
                201);

        int userId =
                createResponse.jsonPath()
                        .getInt("id");

        test.info(
                "Created test user with ID: "
                + userId);

        // ------------------------------------------------
        // Step 2: Get the user
        // ------------------------------------------------

        test.info(
                "Sending GET /users/{id} request");

        Response response =
                userService.getUser(userId);

        ApiReportLogger.logResponse(
                test,
                response);

        // ------------------------------------------------
        // Step 3: Validate response
        // ------------------------------------------------

        Assert.assertEquals(
                response.statusCode(),
                200);

        Assert.assertEquals(
                response.jsonPath()
                        .getInt("id"),
                userId);

        Assert.assertEquals(
                response.jsonPath()
                        .getString("name"),
                request.getName());

        Assert.assertEquals(
                response.jsonPath()
                        .getString("email"),
                request.getEmail());

        Assert.assertEquals(
                response.jsonPath()
                        .getString("gender"),
                request.getGender());

        Assert.assertEquals(
                response.jsonPath()
                        .getString("status"),
                request.getStatus());

        test.pass(
                "Successfully retrieved user with ID: "
                + userId);
    }
}