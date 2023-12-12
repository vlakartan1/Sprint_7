package com.company;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.empty;

public class GetListOfOrdersTest {

    @Before
    public void beforeTest() {
        RestAssured.baseURI = Constants.BASE_URL;
    }

    @Test
    @Step("Получение списка заказов")
    @DisplayName("Тест на получение списка заказов")
    @Description("Получение списка заказов")
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
