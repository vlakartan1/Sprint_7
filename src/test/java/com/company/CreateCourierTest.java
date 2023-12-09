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
    private static final Courier withoutLoginAndPassword = new Courier();

    @Before
    public void setUp() {
        RestAssured.baseURI = Constants.BASE_URL;
    }


    @Test
    @DisplayName("Проверка на создаение клиента с валидными/невалидными данными")
    @Description("Тесты:" +
            "на создание курьера, " +
            "на невозможность создать одинаковых курьеров," +
            "создание курьера с невалидными / неверными данными")
    public void courierTest() {
        requestWithoutLoginAndPassword();
        setNewCourier();
        youCannotCreateIdenticalCouriers();
        authCourierAndReceiveId();
    }

    @Step
    @Description("Создание учетной записи без логина и пароля")
    private void requestWithoutLoginAndPassword() {
        courier.requestWithoutLoginAndPassword(Constants.CONTENT_TYPE, Constants.APPLICATION, withoutLoginAndPassword, Constants.COURIER_API);
        System.out.println("Шаг 1: Недостаточно данных для создания учетной записи. Нет логина или пароля");
    }

    @Step
    @Description("Создание нового курьера")
    private void setNewCourier() {
        courier.setNewCourier(Constants.CONTENT_TYPE, Constants.APPLICATION, newCourier, Constants.COURIER_API);
        System.out.println("Шаг 2: Клиент успешно создан");
    }

    @Step
    @Description("Создание курьера с одинаковыми данными")
    private void youCannotCreateIdenticalCouriers() {
        courier.youCannotCreateIdenticalCouriers(Constants.CONTENT_TYPE, Constants.APPLICATION, newCourier, Constants.COURIER_API);
        System.out.println("Шаг 3: Невозможно создать курьера с одинаковыми данными. Такой логин уже существует");
    }

    @Step
    @Description("Авторизация курьера и получение его ID")
    private void authCourierAndReceiveId() {
        courierId = courier.authCourierAndReceiveId(Constants.CONTENT_TYPE, Constants.APPLICATION, authCourier, Constants.COURIER_LOGIN_API);
        System.out.println("Шаг 4: Курьер успешно авторизован и получен его ID: " + courierId);
    }

    @Step
    @Description("Удалить курьера по полученному ID и проверить, что курьера с таким ID не существует")
    private void removeCourierById() {
        courier.removeCourierById(courierId, Constants.REMOVE_COURIER_API);
        System.out.println("Шаг 5: Данные курьера успешно удалены");
        courier.removeCourierNonExistentId(courierId, Constants.REMOVE_COURIER_API);
        System.out.println("Шаг 6: Курьера с таким id не существует");
    }

    @After
    public void removeCourier() {
        removeCourierById();
    }

}