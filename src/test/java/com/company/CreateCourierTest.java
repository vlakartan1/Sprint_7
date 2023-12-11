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
    private static final Courier newCourier = new Courier("karoline", "121212", "КаролинаА");
    private static final Courier authCourier = new Courier("karoline", "121212");

    @Before
    public void setUp() {
        RestAssured.baseURI = Constants.BASE_URL;
    }


    @Test
    @DisplayName("Проверка на создаение клиента с валидными данными и клиента с дублирующими данными")
    @Description("Тест: на невозможность создать одинаковых курьеров")
    public void courierTest() {
        setNewCourier();
        youCannotCreateIdenticalCouriers();
    }

    @Step("Создание нового курьера с уникальными данными")
    @Description("Создание нового курьера")
    private void setNewCourier() {
        courier.setNewCourier(Constants.CONTENT_TYPE, Constants.APPLICATION, newCourier, Constants.COURIER_API);
        System.out.println("Шаг 1: Клиент успешно создан");
    }

    @Step("Создание курьера с одинаковыми (теми же) данными")
    @Description("Создание курьера с одинаковыми данными")
    private void youCannotCreateIdenticalCouriers() {
        courier.youCannotCreateIdenticalCouriers(Constants.CONTENT_TYPE, Constants.APPLICATION, newCourier, Constants.COURIER_API);
        System.out.println("Шаг 2: Невозможно создать курьера с одинаковыми данными. Такой логин уже существует");
    }

    @Step("Удалить курьера по полученному ID и проверить, что курьера с таким ID не существует")
    @Description("Удалить курьера по полученному ID и проверить, что курьера с таким ID не существует")
    private void removeCourierById() {
        courierId = courier.authCourierAndReceiveId(Constants.CONTENT_TYPE, Constants.APPLICATION, authCourier, Constants.COURIER_LOGIN_API);
        courier.removeCourierById(courierId, Constants.REMOVE_COURIER_API);
        courier.removeCourierNonExistentId(courierId, Constants.REMOVE_COURIER_API);
        System.out.println("Шаг 3: Курьер успешно удален и с таким id курьеров не существует");
    }

    @After
    public void removeCourier() {
        removeCourierById();
    }
}
