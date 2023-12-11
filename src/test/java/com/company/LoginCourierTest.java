package com.company;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginCourierTest {
    Courier courier = new Courier();
    String courierId;
    private static final Courier newCourier = new Courier("karoline", "121212", "КаролинаА");
    private static final Courier authCourier = new Courier("karoline", "121212");


    @Before
    public void setUp() {
        RestAssured.baseURI = Constants.BASE_URL;
    }

    @Test
    @Description("Тест: на авторизацию курьера")
    @DisplayName("Тест на авторизацию курьера с валидными данными")
    public void courierAPITest() {
        setNewCourier();
        authCourierAndReceiveId();
    }

    @Step("Создание курьера для теста авторизации")
    private void setNewCourier() {
        courier.setNewCourier(Constants.CONTENT_TYPE, Constants.APPLICATION, newCourier, Constants.COURIER_API);
        System.out.println("Шаг 1: Клиент успешно создан для проверки авторизации");
    }

    @Step("Авторизация курьера и получение его ID")
    private void authCourierAndReceiveId() {
        courierId = courier.authCourierAndReceiveId(Constants.CONTENT_TYPE, Constants.APPLICATION, authCourier, Constants.COURIER_LOGIN_API);
        System.out.println("Шаг 2: Курьер успешно авторизован и получен его ID: " + courierId);
    }


    @Step("Удалить курьера по полученному ID и проверить, что курьера с таким ID не существует")
    private void removeCourierById() {
        courier.removeCourierById(courierId, Constants.REMOVE_COURIER_API);
        courier.removeCourierNonExistentId(courierId, Constants.REMOVE_COURIER_API);
        System.out.println("Шаг 3: Данные курьера успешно удалены и Курьера с таким id не существует");
    }

    @After
    public void removeCourier() {
        removeCourierById();
    }
}