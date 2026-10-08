package com.skytech.restassured.base;

import com.skytech.restassured.config.ConfigManager;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static io.restassured.http.ContentType.JSON;

public class RequestSpecificationFactory {

    public static RequestSpecification getRequestSpecification() {

        return new RequestSpecBuilder()
                .setBaseUri(
                        ConfigManager.get("base.url"))
                .setContentType(JSON)
                .setAccept(JSON)
                .addHeader(
                        "Authorization",
                        "Bearer "
                                + ConfigManager.get("token"))
                .build();
    }
}