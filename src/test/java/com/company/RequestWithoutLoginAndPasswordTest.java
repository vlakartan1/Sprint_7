package com.company;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;

import org.junit.Before;
import org.junit.Test;


public class RequestWithoutLoginAndPasswordTest {
    Courier courier = new Courier();
    private static final Courier withoutLoginAndPassword = new Courier();
    private static final String resp = "Недостаточно данных для создания учетной записи";


    @Before
    public void setUp() {
        RestAssured.baseURI = Constants.BASE_URL;
    }


    @Test
    @DisplayName("Проверка на создаение курьера без логина и пароля")
    @Description("Тест: создание курьера с невалидными / неверными данными")
    public void courierTest() {
        requestWithoutLoginAndPassword();
    }

    @Step("Тест: на Создание учетной записи без логина и пароля")
    @Description("Тест: Создание учетной записи без логина и пароля")
    private void requestWithoutLoginAndPassword() {
        courier.requestWithoutLoginAndPassword(withoutLoginAndPassword, 400, resp);
    }

}
