package com.company;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class Courier {
    private String login;
    private String password;
    private String firstName;

    public Courier(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Courier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public Courier() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    //Создание учетной записи без логина и пароля
    public void requestWithoutLoginAndPassword(Courier withoutLoginAndPassword, int code, String resp) {
        Response response =
                given()
                        .header(Constants.CONTENT_TYPE, Constants.APPLICATION)
                        .body(withoutLoginAndPassword)
                        .when()
                        .post(Constants.COURIER_API);
        response
                .then()
                .statusCode(code)
                .body("message", equalTo(resp));
    }

    //Создание курьера
    public void setNewCourier(Courier newCourier, int code, boolean success) {

        Response response =
                given()
                        .header(Constants.CONTENT_TYPE, Constants.APPLICATION)
                        .body(newCourier)
                        .when()
                        .post(Constants.COURIER_API);

        response
                .then()
                .statusCode(code)
                .body("ok", equalTo(success));
    }

    //Нельзя Создать одинаковых курьеров")
    public void youCannotCreateIdenticalCouriers(Courier newCourier, int code, String message) {
        Response response =
                given()
                        .header(Constants.CONTENT_TYPE, Constants.APPLICATION)
                        .body(newCourier)
                        .when()
                        .post(Constants.COURIER_API);
        response
                .then()
                .statusCode(409)
                .body("message", equalTo(message));
    }


    //Авторизоваться от созданного курьера и получить его id
    public String authCourierAndReceiveId(Courier authCourier, int code) {

        Response response =
                given()
                        .header(Constants.CONTENT_TYPE, Constants.APPLICATION)
                        .body(authCourier)
                        .when()
                        .post(Constants.COURIER_LOGIN_API);

        String courierId = response.then().statusCode(code)
                .body("id", notNullValue())
                .extract()
                .path("id").toString();


        System.out.println("Успешная авторизация курьера и получение его ID : " + courierId);
        return courierId;
    }

    //Авторизоваться с несуществующим логином
    public void authNonExistentlogin(Courier nonExistentlogin, int code, String message) {

        Response response =
                given()
                        .header(Constants.CONTENT_TYPE, Constants.APPLICATION)
                        .body(nonExistentlogin)
                        .when()
                        .post(Constants.COURIER_LOGIN_API);

        response
                .then()
                .statusCode(code)
                .body("message", equalTo(message));
    }

    //Авторизоваться без обязательного поля Пароля
    public void authWithoutPassword(Courier withoutPassword, int code, String message) {

        Response response =
                given()
                        .header(Constants.CONTENT_TYPE, Constants.APPLICATION)
                        .body(withoutPassword)
                        .when()
                        .post(Constants.COURIER_LOGIN_API);

        response
                .then()
                .statusCode(code)
                .body("message", equalTo(message));
    }

    //Удалить курьера по его id
    public void removeCourierById(String courierId, int code, boolean success) {
        Response response =
                given()
                        .pathParam("id", courierId)
                        .when()
                        .delete(Constants.REMOVE_COURIER_API);
        response
                .then()
                .statusCode(code)
                .body("ok", equalTo(success));
    }

    //Попробовать удалить курьера по несуществующему id
    public void removeCourierNonExistentId(String courierId, int code, String message) {
        Response response =
                given()
                        .pathParam("id", courierId)
                        .when()
                        .delete(Constants.REMOVE_COURIER_API);
        response
                .then()
                .statusCode(code)
                .body("message", equalTo(message));
    }

}
