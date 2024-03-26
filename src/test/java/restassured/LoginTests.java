package restassured;

import helpers.PropertiesReader;
import helpers.TestHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.AuthenticationRequestModel;
import models.AuthenticationResponseModel;
import models.ErrorModel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LoginTests implements TestHelper {

    @BeforeMethod
    public void precondition(){
        RestAssured.baseURI = PropertiesReader.getProperty("baseUrl");
       // RestAssured.basePath = PropertiesReader.getProperty("v1path");

    }

    @Test
    public void loginTest(){
        AuthenticationRequestModel authenticationRequestModel =
                AuthenticationRequestModel
                        .username(PropertiesReader.getProperty("existingUserEmail"))
                        .password(PropertiesReader.getProperty("existingUserPassword"));

       AuthenticationResponseModel response =  given()
                .body(authenticationRequestModel)
                .contentType(ContentType.JSON)
                .when()
                .post(PropertiesReader.getProperty("login"))
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .as(AuthenticationResponseModel.class);
        System.out.println(response.getToken());

    }
/*
* Создается объект AuthenticationRequestModel, который предположительно содержит информацию,
*  необходимую для аутентификации пользователя. В частности, это может быть имя пользователя
*  (или адрес электронной почты) и пароль.

given(): Начало цепочки спецификации запроса с использованием библиотеки RestAssured.

.body(authenticationRequestModel): Устанавливает тело (body) запроса,
* используя объект authenticationRequestModel, содержащий данные для аутентификации.

.contentType(ContentType.JSON): Устанавливает тип содержимого запроса как JSON.

.when().post(PropertiesReader.getProperty("login")):
* Эта часть отправляет POST-запрос на указанный в настройках ресурс для выполнения входа.
*  Адрес ресурса извлекается из файла настроек.

.then().log().all(): После отправки запроса, метод .then() указывает на то,
* что следует выполнить дополнительные проверки.
* .log().all() используется для записи всех деталей ответа в консоль (для отладки или анализа).

.assertThat().statusCode(200): Утверждает, что ожидается получение ответа с кодом состояния
*  (статус кодом) 200 (ОК).

.extract().as(AuthenticationResponseModel.class):
* После получения ответа, извлекает данные ответа и преобразует их в объект класса
* AuthenticationResponseModel. Это предположительно модель,
*  представляющая ответ на успешную аутентификацию.

System.out.println(response.getToken()): Выводит в консоль токен,
* который был получен в ответе. Это, вероятно, токен,
*  используемый для аутентификации пользователя в дальнейших запросах.*/
    @Test
    public void loginTestNegative(){
        AuthenticationRequestModel authenticationRequestModel =
                AuthenticationRequestModel
                        .username("PropertiesReader")
                        .password(PropertiesReader.getProperty("existingUserPassword"));

        ErrorModel error =  given()
                .body(authenticationRequestModel)
                .contentType(ContentType.JSON)
                .when()
                .post(PropertiesReader.getProperty("login"))
                .then().log().all()
                .assertThat()
                .statusCode(401)
                .extract()
                .as(ErrorModel.class);
        System.out.println(error.getMessage());

    }
}
