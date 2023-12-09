package com.company;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.empty;


public class GetListOfOrders {

    public void testGetOrders() {
        given()
                .when()
                .get(Constants.ORDER_API)
                .then()
                .statusCode(200)
                .body("orders", not(empty()))
                .body("pageInfo.page", equalTo(0))
                .body("pageInfo.total", greaterThan(0))
                .body("pageInfo.limit", greaterThan(0))
                .body("availableStations", not(empty()));
    }
}