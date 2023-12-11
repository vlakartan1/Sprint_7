package com.company;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;


public class AuthNonExistentLoginTest {
    Courier courier = new Courier();
    private static final Courier nonExistentlogin = new Courier("karoline888", "888");

    @Before
    public void setUp() {
        RestAssured.baseURI = Constants.BASE_URL;
    }

    @Test
    @Description("Тест: на авторизацию курьера с несуществующими данными")
    @DisplayName("Тесты на авторизацию с невалидными данными")
    public void courierAPITest() {
        authNonExistentlogin();
    }

    @Step("Тест на авторизацию с несуществующими данными")
    private void authNonExistentlogin() {
        courier.authNonExistentlogin(Constants.CONTENT_TYPE, Constants.APPLICATION, nonExistentlogin, Constants.COURIER_LOGIN_API);
        System.out.println("Учетная запись не найдена");
    }

}
