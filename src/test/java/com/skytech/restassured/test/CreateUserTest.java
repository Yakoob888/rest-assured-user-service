package com.skytech.restassured.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.skytech.restassured.base.BaseTest;
import com.skytech.restassured.config.ExtentTestManager;
import com.skytech.restassured.model.UserRequest;
import com.skytech.restassured.service.UserService;
import com.skytech.restassured.utils.ApiReportLogger;
import com.skytech.restassured.utils.DataProviderUtils;

import io.restassured.response.Response;

public class CreateUserTest extends BaseTest {

	@Test(
		    dataProvider = "userData",
		    dataProviderClass = DataProviderUtils.class
		)
	public void createUserTest(UserRequest request) {

	    ExtentTest test =
	            ExtentTestManager.getTest();

	    test.info("Creating user: "
	            + request.getName());

	    UserService userService =
	            new UserService(requestSpecification);

	    test.info("Sending POST /users request");

	    Response response =
	            userService.createUser(request);

	    ApiReportLogger.logResponse(
	            test,
	            response);

	    Assert.assertEquals(
	            response.statusCode(),
	            201);

	    test.pass(
	            "User created successfully");

	    Assert.assertEquals(
	            response.jsonPath()
	                    .getString("name"),
	            request.getName());

	    Assert.assertEquals(
	            response.jsonPath()
	                    .getString("email"),
	            request.getEmail());

	    Assert.assertNotNull(
	            response.jsonPath()
	                    .get("id"));
	}
}