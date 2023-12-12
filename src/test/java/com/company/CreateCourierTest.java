package com.company;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CreateCourierTest {
    Courier courier = new Courier();
    String courierId;
    private final static String message = "Курьера с таким id нет.";
    private static final String messageLogin = "Этот логин уже используется. Попробуйте другой.";
    private static final Courier newCourier = new Courier("karoline", "121212", "КаролинаА");
    private static final Courier authCourier = new Courier("karoline", "121212");

    @Before
    public void setUp() {
        RestAssured.baseURI = Constants.BASE_URL;
        setNewCourier();
    }


    @Test
    @DisplayName("Проверка на создаение клиента с валидными данными и клиента с дублирующими данными")
    @Description("Тест: на невозможность создать одинаковых курьеров")
    public void courierTest() {
        youCannotCreateIdenticalCouriers();
    }

    @Step("Создание нового курьера с уникальными данными")
    @Description("Создание нового курьера")
    private void setNewCourier() {
        courier.setNewCourier(newCourier, 201, true);
    }

    @Step("Создание курьера с одинаковыми (теми же) данными")
    @Description("Создание курьера с одинаковыми данными")
    private void youCannotCreateIdenticalCouriers() {
        courier.youCannotCreateIdenticalCouriers(newCourier, 409, messageLogin);
    }

    @Step("Удалить курьера по полученному ID и проверить, что курьера с таким ID не существует")
    @Description("Удалить курьера по полученному ID и проверить, что курьера с таким ID не существует")
    private void removeCourierById() {
        courierId = courier.authCourierAndReceiveId(authCourier, 200);
        courier.removeCourierById(courierId, 200, true);
        courier.removeCourierNonExistentId(courierId, 404, message);
    }

    @After
    public void removeCourier() {
        removeCourierById();
    }
}
