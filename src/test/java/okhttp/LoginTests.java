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
}

}
