package com.company;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

@RunWith(Parameterized.class)
public class CreatingAnOrderTest {
    private final CreatingAnOrder creatingAnOrder;

    public CreatingAnOrderTest(CreatingAnOrder creatingAnOrder) {
        this.creatingAnOrder = creatingAnOrder;
    }

    @Before
    public void beforeTest() {
        RestAssured.baseURI = Constants.BASE_URL;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {new CreatingAnOrder("Дмитрий", "Иванов",
                        "Москва, проспект Мира-2", 1, "+7 910 910 10 10",
                        2, "2023-06-06", "Привезите срочно", List.of("BLACK"))},
                {new CreatingAnOrder("Луиза", "Бабкина",
                        "Москва, Тульския-15", 2, "+7 905 555 55 55",
                        3, "2023-06-06", "Перезвоните", List.of("GREY"))},
                {new CreatingAnOrder("Марк", "Пупкин",
                        "Москва, Буденого-6", 3, "+7 901 901 01 01",
                        4, "2023-10-12", "Saske, come back to Konoha", List.of("BLACK", "GREY"))},
                {new CreatingAnOrder("Андрей", "Бочков",
                        "Москва, Флерова-74", 4, "+7 800 355 35 35",
                        5, "2023-12-12", "Saske, come back to Konoha", List.of())}
        };
    }

    @Test
    @Step("Получить заказы")
    public void makeOrderTest() {
        creatingAnOrder.makeOrder(Constants.CONTENT_TYPE, Constants.APPLICATION, creatingAnOrder, Constants.ORDER_API);
        System.out.println("Заказ принят от " + creatingAnOrder.getFirstName());
    }
}