package com.skytech.restassured.base;

import org.testng.annotations.BeforeClass;

import io.restassured.specification.RequestSpecification;

public class BaseTest {

    protected RequestSpecification requestSpecification;

    @BeforeClass
    public void setUp() {

        requestSpecification =
                RequestSpecificationFactory
                        .getRequestSpecification();
    }
}