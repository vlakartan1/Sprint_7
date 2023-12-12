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
    private final static String message = "Курьера с таким id нет.";

    @Before
    public void setUp() {
        RestAssured.baseURI = Constants.BASE_URL;
        setNewCourier();
    }

    @Test
    @Description("Тест: на авторизацию курьера")
    @DisplayName("Тест на авторизацию курьера с валидными данными")
    public void courierAPITest() {
        authCourierAndReceiveId();
    }

    @Step("Создание курьера для теста авторизации")
    private void setNewCourier() {
        courier.setNewCourier(newCourier, 201, true);
    }

    @Step("Авторизация курьера и получение его ID")
    private void authCourierAndReceiveId() {
        courierId = courier.authCourierAndReceiveId(authCourier, 200);
    }


    @Step("Удалить курьера по полученному ID и проверить, что курьера с таким ID не существует")
    private void removeCourierById() {
        courier.removeCourierById(courierId, 200, true);
        courier.removeCourierNonExistentId(courierId, 404, message);
    }

    @After
    public void removeCourier() {
        removeCourierById();
    }
}