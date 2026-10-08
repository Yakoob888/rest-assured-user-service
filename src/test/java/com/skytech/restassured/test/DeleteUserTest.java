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

public class DeleteUserTest extends BaseTest {

    @Test
    public void deleteUserTest() {

        ExtentTest test =
                ExtentTestManager.getTest();

        UserService userService =
                new UserService(requestSpecification);

        // ------------------------------------------------
        // Step 1: Create user
        // ------------------------------------------------

        UserRequest request =
                new UserRequest(
                        "Delete Test User",
                        TestDataUtils.uniqueEmail(
                                "deleteuser@test.com"),
                        "male",
                        "active");

        test.info(
                "Creating user for delete test");

        Response createResponse =
                userService.createUser(request);

        Assert.assertEquals(
                createResponse.statusCode(),
                201);

        int userId =
                createResponse.jsonPath()
                        .getInt("id");

        test.info(
                "Created user with ID: "
                + userId);

        // ------------------------------------------------
        // Step 2: Delete user
        // ------------------------------------------------

        test.info(
                "Deleting user with ID: "
                + userId);

        Response deleteResponse =
                userService.deleteUser(userId);

        ApiReportLogger.logResponse(
                test,
                deleteResponse);

        Assert.assertEquals(
                deleteResponse.statusCode(),
                204);

        test.pass(
                "Delete request successful");

        // ------------------------------------------------
        // Step 3: Verify user is deleted
        // ------------------------------------------------

        test.info(
                "Verifying user no longer exists");

        Response getResponse =
                userService.getUser(userId);

        ApiReportLogger.logResponse(
                test,
                getResponse);

        Assert.assertEquals(
                getResponse.statusCode(),
                404);

        test.pass(
                "User successfully deleted. ID: "
                + userId);
    }
}