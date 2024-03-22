package okhttp;

import helpers.PropertiesReader;
import helpers.PropertiesWriter;
import helpers.TestConfig;
import models.AuthenticationRequestModel;
import models.AuthenticationResponseModel;
import models.ErrorModel;
import models.NewUserModel;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationTest {

    @Test
    public void registrationPositive() throws IOException {

        NewUserModel newUserModel = new NewUserModel("existing@UserEmail.com","existingUserPassword123!");
        RequestBody requestBody = RequestBody.create(TestConfig.gson.toJson(newUserModel), TestConfig.JSON);

        Request request = new Request.Builder()
                .url(PropertiesReader.getProperty("baseUrl")+"/v1/user/registration/usernamepassword")
                .post(requestBody)
                .build();
        Response response = TestConfig.client.newCall(request).execute();
        String responseBody = response.body().string();

        if (response.isSuccessful()) {
            AuthenticationResponseModel responseModel = TestConfig.gson.fromJson(responseBody, AuthenticationResponseModel.class);
            System.out.println("TOKEN : " + responseModel.getToken());
            System.out.println("CODE : " + response.code());
            PropertiesWriter.writeProperties("existing@UserEmail.com", responseModel.getToken(), false);
            Assert.assertTrue(response.isSuccessful());
        } else {
            System.out.println("CODE : " + response.code());
            ErrorModel errorModel = TestConfig.gson.fromJson(responseBody, ErrorModel.class);
            System.out.println(errorModel.getStatus());
        }
    }
}
