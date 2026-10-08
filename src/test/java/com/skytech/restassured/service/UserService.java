package com.skytech.restassured.service;

import com.skytech.restassured.model.UserRequest;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class UserService {

    private final RequestSpecification requestSpecification;

    public UserService(
            RequestSpecification requestSpecification) {

        this.requestSpecification =
                requestSpecification;
    }

    // POST /users
    // Create a new user
    public Response createUser(
            UserRequest request) {

        return given()
                .spec(requestSpecification)
                .body(request)
                .log().body()
            .when()
                .post("/users")
            .then()
                .log().body()
                .extract()
                .response();
    }

    // GET /users/{id}
    // Get a specific user
    public Response getUser(
            int userId) {

        return given()
                .spec(requestSpecification)
                .pathParam("id", userId)
                .log().body()
            .when()
                .get("/users/{id}")
            .then()
                .log().body()
                .extract()
                .response();
    }

    // GET /users
    // Get all users
    public Response getAllUsers() {

        return given()
                .spec(requestSpecification)
                .log().body()
            .when()
                .get("/users")
            .then()
                .log().body()
                .extract()
                .response();
    }

    // PUT /users/{id}
    // Update an existing user
    public Response updateUser(
            int userId,
            UserRequest request) {

        return given()
                .spec(requestSpecification)
                .pathParam("id", userId)
                .body(request)
                .log().body()
            .when()
                .put("/users/{id}")
            .then()
                .log().body()
                .extract()
                .response();
    }

    // DELETE /users/{id}
    // Delete an existing user
    public Response deleteUser(
            int userId) {

        return given()
                .spec(requestSpecification)
                .pathParam("id", userId)
                .log().body()
            .when()
                .delete("/users/{id}")
            .then()
                .log().body()
                .extract()
                .response();
    }
}