package com.company;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

public class GetListOfOrdersTest {
    GetListOfOrders getListOfOrders = new GetListOfOrders();

    @Before
    public void beforeTest() {
        RestAssured.baseURI = Constants.BASE_URL;
    }

    @Test
    @Step("Получение списка заказов")
    public void testGetOrders() {
        getListOfOrders.testGetOrders();
        System.out.println("Получен список заказов");
    }
}
