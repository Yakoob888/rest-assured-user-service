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

public class UpdateUserTest extends BaseTest {

    @Test
    public void updateUserTest() {

        ExtentTest test =
                ExtentTestManager.getTest();

        UserService userService =
                new UserService(requestSpecification);

        // ------------------------------------------------
        // Step 1: Create user
        // ------------------------------------------------

        UserRequest createRequest =
                new UserRequest(
                        "Update Test User",
                        TestDataUtils.uniqueEmail(
                                "updateduser@test.com"),
                        "male",
                        "active");

        test.info(
                "Creating user for update test");

        Response createResponse =
                userService.createUser(
                        createRequest);

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
        // Step 2: Prepare updated data
        // ------------------------------------------------

        UserRequest updateRequest =
                new UserRequest(
                        "Updated User",
                        TestDataUtils.uniqueEmail(
                                "updateduser@test.com"),
                        "female",
                        "active");

        test.info(
                "Updating user with ID: "
                + userId);

        // ------------------------------------------------
        // Step 3: PUT request
        // ------------------------------------------------

        Response response =
                userService.updateUser(
                        userId,
                        updateRequest);

        ApiReportLogger.logResponse(
                test,
                response);

        // ------------------------------------------------
        // Step 4: Validate response
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
                updateRequest.getName());

        Assert.assertEquals(
                response.jsonPath()
                        .getString("email"),
                updateRequest.getEmail());

        Assert.assertEquals(
                response.jsonPath()
                        .getString("gender"),
                updateRequest.getGender());

        Assert.assertEquals(
                response.jsonPath()
                        .getString("status"),
                updateRequest.getStatus());

        test.pass(
                "User updated successfully. ID: "
                + userId);
    }
}