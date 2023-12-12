package com.company;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;


public class AuthWithoutPasswordTest {
    Courier courier = new Courier();
    private static final Courier withoutPassword = new Courier("karoline", "");
    private static final String message = "Недостаточно данных для входа";

    @Before
    public void setUp() {
        RestAssured.baseURI = Constants.BASE_URL;
    }

    @Description("Тест: на недостаточность данных при авторизации")
    @DisplayName("Тест на авторизацию при недостаточности данных")
    @Test
    public void courierAPITest() {
        authWithoutPassword();
    }

    @Step("Авторизация без обязательных данных: без пароля")
    private void authWithoutPassword() {
        courier.authWithoutPassword(withoutPassword, 400, message);
    }
}