package restassured;

import helpers.PropertiesReader;
import helpers.TestHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.ContactModel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AddNewContactTest implements TestHelper {

    @BeforeMethod
    public void precondition(){
        RestAssured.baseURI = PropertiesReader.getProperty("getAllContacts");
    }
    @Test
    public void addNewContactPositive(){
        ContactModel contactModel =
                new ContactModel("TestUserfb2","HisLgastName","jcdkh@megamail.com","44231763567","LA 345 Sunset beach","hough");
            given()
                    .header(AuthorizationHeader, PropertiesReader.getProperty("token"))
                    .body(contactModel)
                    .contentType(ContentType.JSON)
                    .when()
                    .post()
                    .then()
                    .assertThat()
                    .statusCode(200);
    }
    /*
    * given(): Это часть спецификации тестирования с использованием библиотеки RestAssured.
    * Этот метод начинает цепочку спецификации запроса.

.header(AuthorizationHeader, PropertiesReader.getProperty("token")):
* Эта часть устанавливает заголовок запроса с именем "AuthorizationHeader" и значением,
* полученным из файла свойств (скорее всего, это какой-то токен для аутентификации).

.body(contactModel): Здесь устанавливается тело (body) запроса,
* используя объект contactModel, который мы создали ранее.

.contentType(ContentType.JSON): Устанавливает тип содержимого запроса как JSON.

.when().post(): Это часть, которая отправляет POST-запрос.

.then().assertThat().statusCode(200): После отправки запроса, ожидается,
* что будет получен ответ с кодом состояния (статус кодом) 200 (ОК).*/
}
