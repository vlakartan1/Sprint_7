package com.company;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
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
    @DisplayName("Тест на получение списка заказов")
    @Description("Получение списка заказов")
    public void testGetOrders() {
        getListOfOrders.testGetOrders();
        System.out.println("Получен список заказов");
    }
}
