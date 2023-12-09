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
    private static final Courier nonExistentlogin = new Courier("karoline888", "888");
    private static final Courier withoutPassword = new Courier("karoline", "");

    @Before
    public void setUp() {
        RestAssured.baseURI = Constants.BASE_URL;
    }

    @Description("Тесты:" +
            "на авторизацию курьера, " +
            "на недостаточность данных при авторизации" +
            "с неверными данными при авторизации")
    @DisplayName("Тесты на авторизацию с валидными/невалидными данными")
    @Test
    public void courierAPITest() {
        setNewCourier();
        authCourierAndReceiveId();
        authNonExistentlogin();
        authWithoutPassword();
    }

    @Step("Создание курьера для теста авторизации")
    private void setNewCourier(){
        courier.setNewCourier(Constants.CONTENT_TYPE, Constants.APPLICATION, newCourier, Constants.COURIER_API);
        System.out.println("Шаг 1: Клиент успешно создан");
    }

    @Step("Авторизация курьера и получение его ID")
    private void authCourierAndReceiveId(){
        courierId = courier.authCourierAndReceiveId(Constants.CONTENT_TYPE, Constants.APPLICATION, authCourier, Constants.COURIER_LOGIN_API);
        System.out.println("Шаг 2: Курьер успешно авторизован и получен его ID: " + courierId);
    }

    @Step("Тест на авторизацию с несуществующими данными")
    private void authNonExistentlogin(){
        courier.authNonExistentlogin(Constants.CONTENT_TYPE, Constants.APPLICATION, nonExistentlogin, Constants.COURIER_LOGIN_API);
        System.out.println("Шаг 3: Учетная запись не найдена");
    }

    @Step("Авторизация с невалидными данными. данными без пароля")
    private void authWithoutPassword(){
        courier.authWithoutPassword(Constants.CONTENT_TYPE, Constants.APPLICATION, withoutPassword, Constants.COURIER_LOGIN_API);
        System.out.println("Шаг 4: Недостаточно данных для авторизации. Перепроверьте данные.");
    }

    @Step("Удалить курьера по полученному ID и проверить, что курьера с таким ID не существует")
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