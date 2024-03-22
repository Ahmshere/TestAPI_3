package okhttp;

import helpers.PropertiesReader;
import helpers.TestConfig;
import helpers.TestHelper;
import models.AuthenticationRequestModel;
import models.AuthenticationResponseModel;
import models.ErrorModel;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationUsingINterfaceTest implements TestHelper {

    @Test
    public void registrationUsingInterface() throws IOException {

        AuthenticationRequestModel requestModel = AuthenticationRequestModel
                .username("user_"+i+"u@mail.com")
                .password("asdQWE123n$");
        System.out.println("Model: "+requestModel.toString());
        RequestBody requestBody = RequestBody.create(gson.toJson(requestModel), JSON);
        Request request = new Request.Builder()
                .url(PropertiesReader.getProperty("baseUrl")+"/v1/user/registration/usernamepassword")
                .post(requestBody)
                .build();
        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        Assert.assertTrue(response.isSuccessful());
        AuthenticationResponseModel responseModel =
                gson.fromJson(responseBody, AuthenticationResponseModel.class);
        System.out.println("TOKEN : "+responseModel.getToken());

    }

    @Test
    public void registrationUsingInterfaceNegative() throws IOException {

        AuthenticationRequestModel requestModel = AuthenticationRequestModel
                .username("user_"+i+"mail.com")
                .password("asdQWE123n$");
        System.out.println("Model: "+requestModel.toString());
        RequestBody requestBody = RequestBody.create(gson.toJson(requestModel), JSON);
        Request request = new Request.Builder()
                .url(PropertiesReader.getProperty("baseUrl")+"/v1/user/registration/usernamepassword")
                .post(requestBody)
                .build();
        Response response = client.newCall(request).execute();
        if(response.isSuccessful()) {
            AuthenticationResponseModel responseModel = gson.fromJson(response.body().string(), AuthenticationResponseModel.class);
            // String responseBody = response.body().string();

            System.out.println("TOKEN : "+responseModel.getToken());
        }
        ErrorModel errorModel =
                gson.fromJson(response.body().string(), ErrorModel.class);
        System.out.println("ERROR: "+errorModel.getError()+" | "+errorModel.getMessage());
        Assert.assertTrue(!response.isSuccessful());

    }
}
