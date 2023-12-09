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
    public void requestWithoutLoginAndPassword(String content, String application, Courier withoutLoginAndPassword, String api) {
        Response response =
                given()
                        .header(content, application)
                        .body(withoutLoginAndPassword)
                        .when()
                        .post(api);
        response
                .then()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    //Создание курьера
    public void setNewCourier(String content, String application, Courier newCourier, String api) {

        Response response =
                given()
                        .header(content, application)
                        .body(newCourier)
                        .when()
                        .post(api);

        response
                .then()
                .statusCode(201)
                .body("ok", equalTo(true));
    }

    //Нельзя Создать одинаковых курьеров")
    public void youCannotCreateIdenticalCouriers(String content, String application, Courier newCourier, String api) {
        Response response =
                given()
                        .header(content, application)
                        .body(newCourier)
                        .when()
                        .post(api);
        response
                .then()
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }


    //Авторизоваться от созданного курьера и получить его id
    public String authCourierAndReceiveId(String content, String application, Courier authCourier, String api) {

        Response response =
                given()
                        .header(content, application)
                        .body(authCourier)
                        .when()
                        .post(api);

        String courierId = response.then().statusCode(200)
                .body("id", notNullValue())
                .extract()
                .path("id").toString();


        System.out.println("Успешная авторизация и получен ID курьера: " + courierId);
        return courierId;
    }

    //Авторизоваться с несуществующим логином
    public void authNonExistentlogin(String content, String application, Courier nonExistentlogin, String api) {

        Response response =
                given()
                        .header(content, application)
                        .body(nonExistentlogin)
                        .when()
                        .post(api);

        response
                .then()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
    }

    //Авторизоваться без обязательного поля Пароля
    public void authWithoutPassword(String content, String application, Courier withoutPassword, String api) {

        Response response =
                given()
                        .header(content, application)
                        .body(withoutPassword)
                        .when()
                        .post(api);

        response
                .then()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    //Удалить курьера по его id
    public void removeCourierById(String courierId, String api) {
        Response response =
                given()
                        .pathParam("id", courierId)
                        .when()
                        .delete(api);
        response
                .then()
                .statusCode(200)
                .body("ok", equalTo(true));
    }

    //Попробовать удалить курьера по несуществующему id
    public void removeCourierNonExistentId(String courierId, String api) {
        Response response =
                given()
                        .pathParam("id", courierId)
                        .when()
                        .delete(api);
        response
                .then()
                .statusCode(404)
                .body("message", equalTo("Курьера с таким id нет."));
    }

}
