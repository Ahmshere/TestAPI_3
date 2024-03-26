package restassured;

import helpers.NameAndLastNameGenerator;
import helpers.PasswordStringGenerator;
import helpers.PropertiesReader;
import helpers.TestHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.AuthenticationRequestModel;
import models.NewUserModel;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class RegistrationTest implements TestHelper {

    @Test
    public void registrationPositive(){

        NewUserModel newUserModel = new NewUserModel("mynewqwwresetmail@megamail.com","werASD123$!");
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        String newToken = given()
                .body(newUserModel)
                .contentType(ContentType.JSON)
                .when()
                .post(PropertiesReader.getProperty("registration"))
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("token");
        System.out.println("New token : "+newToken);
    }
/*
*     String newToken: Объявляется переменная newToken типа String, в которую будет сохранено значение токена,
*  извлеченного из ответа сервера.

    given(): Это часть спецификации запроса в RestAssured. Она начинает цепочку спецификации запроса.

    .body(newUserModel): Устанавливает тело (body) запроса, используя объект newUserModel,
    * вероятно, содержащий данные о новом пользователе.

    .contentType(ContentType.JSON): Устанавливает тип содержимого запроса как JSON.

    .when().post(PropertiesReader.getProperty("registration")): Отправляет POST-запрос на URL,
    *  который получается из файла свойств с помощью метода getProperty("registration").
    * Скорее всего, это URL для регистрации нового пользователя.

    .then(): После отправки запроса, начинается цепочка ожиданий.

    .assertThat().statusCode(200): Проверяет, что код состояния (статус код) ответа равен 200 (ОК).
    *  Если это не так, будет сгенерировано исключение.

    .extract(): Этот метод позволяет извлечь значения из ответа.

    .path("token"): Извлекает значение из поля "token" в JSON-ответе и сохраняет его в переменную newToken.

Таким образом, этот код отправляет POST-запрос на URL для регистрации нового пользователя,
* ожидает получения ответа с кодом состояния 200, а затем извлекает значение токена из ответа и сохраняет его в переменной newToken.*/
    @Test
    public void registrationNegative(){

        NewUserModel newUserModel = new NewUserModel("myncom","werASD123$!");
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        given()
                .body(newUserModel)
                .contentType(ContentType.JSON)
                .when()
                .post(PropertiesReader.getProperty("registration"))
                .then()
                .assertThat()
                .statusCode(400)
                .assertThat().body("message.username", containsString("email"));
    }
}
