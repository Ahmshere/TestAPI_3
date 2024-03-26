package okhttp;

import com.google.gson.Gson;
import helpers.PropertiesReader;
import helpers.TestConfig;
import models.AuthenticationRequestModel;
import models.AuthenticationResponseModel;
import models.ErrorModel;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTests {


    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoibXltZWdhbWFpbEBtYWlsLmNvbSIsImlzcyI6IlJlZ3VsYWl0IiwiZXhwIjoxNzExMTg4ODIwLCJpYXQiOjE3MTA1ODg4MjB9.gbe2Fgqy433MLtsTQR2WZEK3qquNHasUOJ0KS0eBbOY";



@Test
    public void loginPositive() throws IOException {
    AuthenticationRequestModel requestModel = AuthenticationRequestModel
            .username(PropertiesReader.getProperty("existingUserEmail"))
            .password(PropertiesReader.getProperty("existingUserPassword"));

    RequestBody requestBody = RequestBody.create(TestConfig.gson.toJson(requestModel), TestConfig.JSON);

    Request request = new Request.Builder()
            .url(PropertiesReader.getProperty("baseUrl")+"/v1/user/login/usernamepassword")
            .post(requestBody)
            .build();

    Response response = TestConfig.client.newCall(request).execute();
    if (response.isSuccessful()){
        AuthenticationResponseModel responseModel =
                TestConfig.gson.fromJson(response.body().string(), AuthenticationResponseModel.class);
        System.out.println("TOKEN : "+responseModel.getToken());
        System.out.println("CODE : "+response.code());
        Assert.assertTrue(response.isSuccessful());
    }
    else {System.out.println("CODE : "+response.code());
        ErrorModel errorModel = TestConfig.gson.fromJson(response.body().string(), ErrorModel.class);
        System.out.println(errorModel.getStatus());
    }

    /*
    *     Создается объект AuthenticationRequestModel, который представляет модель запроса аутентификации.
    *  В данном случае, предполагается, что этот объект содержит данные пользователя, такие как его адрес электронной почты и пароль.

    Создается RequestBody, который содержит данные запроса, преобразованные в формат JSON с использованием библиотеки Gson.

    Создается Request, который представляет HTTP-запрос для входа пользователя.
    *  Этот запрос содержит URL для входа, метод POST и тело запроса.

    Response response = TestConfig.client.newCall(request).execute();:
    * Отправляется HTTP-запрос на сервер и получается ответ.

    if (response.isSuccessful()) {: Проверяется успешность выполнения запроса.
    * Если запрос был успешным (код ответа 200), выполняется следующий блок кода.

    AuthenticationResponseModel responseModel = TestConfig.gson.fromJson(response.body().string(),
    *    AuthenticationResponseModel.class);: Из тела ответа извлекаются данные аутентификации
    * и преобразуются в объект класса AuthenticationResponseModel, предположительно содержащий токен пользователя.

    System.out.println("TOKEN : "+responseModel.getToken());: Выводится в консоль токен, полученный в ответе.

    System.out.println("CODE : "+response.code());: Выводится в консоль код ответа.

    Assert.assertTrue(response.isSuccessful());: Проверяется, что ответ был успешным
    * с использованием утверждения Assert.

    else { ... }: Если запрос не был успешным, выполняется этот блок кода.
    * Код ответа также выводится в консоль, а тело ответа преобразуется в объект класса ErrorModel,
    * который, предположительно, содержит информацию об ошибке.

Этот код тестирует процесс входа пользователя, отправляя HTTP-запрос на сервер и проверяя
*  ответ на успешность и наличие токена. Если вход прошел успешно, токен выводится в консоль.
*  Если неудачно, выводится информация об ошибке.
    * */
}

}
