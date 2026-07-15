package com.client.activities;

import com.model.activities.ActivityResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ActivitiesClient {

    private static final String BASE_URL = "https://fakerestapi.azurewebsites.net/api/v1";
    private static final String ACTIVITIES_ENDPOINT = "/Activities";

    private RequestSpecification getRequestSpec() {
        return RestAssured.given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .log().all(); // Loga os detalhes da requisição de saída
    }

    public Response getActivities() {
        return getRequestSpec()
                .when()
                .get(ACTIVITIES_ENDPOINT);
    }

    public Response postActivity(ActivityResponse body) {
        return getRequestSpec()
                .body(body) // Passa o objeto Java que o RestAssured vai converter em JSON
                .when()
                .post(ACTIVITIES_ENDPOINT);
    }
}
