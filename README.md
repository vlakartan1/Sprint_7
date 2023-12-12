# Sprint_7
QA Java Project

# Описание проекта
Проект по тестированию API: Создание, Авторизация курьера и получение заказов
Используемые технологии: 
Java 11, junit 4.3.12, maven 3.6.3, rest-assured 5.3.2,
google.code.gson 2.10.1, jackson-databind 2.15.1, allure 2.23.0, aspectjweaver 1.9.7

# Запуск проекта
Команда для запуска - `mvn clean test`
Команда для генерации отчета плагином maven-surefire-plugin - allure serve target/surefire-reports/
После запуска API будут проверены и сформируется Allure отчет по покрытию в \target\allure-results