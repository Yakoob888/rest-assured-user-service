package com.skytech.restassured.utils;

import java.util.Map;

import com.skytech.restassured.model.UserRequest;

public final class UserRequestMapper {

    private UserRequestMapper() {
    }

    public static UserRequest map(
            Map<String, String> excelData) {

        String firstName =
                excelData.get("FirstName");

        String lastName =
                excelData.get("LastName");

        String email =
                TestDataUtils.uniqueEmail(
                        excelData.get("Email"));

        String gender =
                excelData.getOrDefault(
                        "Gender",
                        "male");

        String status =
                excelData.getOrDefault(
                        "Status",
                        "active");

        String name =
                firstName + " " + lastName;

        return new UserRequest(
                name,
                email,
                gender,
                status);
    }
}