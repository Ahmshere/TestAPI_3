package okhttp;

import helpers.PropertiesReader;
import helpers.TestHelper;
import models.ContactModel;
import models.ContactResponseModel;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static helpers.IDExtractor.extractId;

public class AddNewContactTests implements TestHelper {
    String id;
    @Test
    public void addNewContactPositive() throws IOException {
        ContactModel contactModel =
                new ContactModel("TestUserfb","HisLgastName","jcdkh@megamail.com","44231763567","LA 345 Sunset beach","hough");

        RequestBody requestBody = RequestBody.create(gson.toJson(contactModel), JSON);
        Request request = new Request.Builder()
                .url(PropertiesReader.getProperty("getAllContacts"))
                .addHeader(AuthorizationHeader, PropertiesReader.getProperty("token"))
                .post(requestBody)
                .build();
        Response response = client.newCall(request).execute();
        ContactResponseModel contactResponseModel =
                gson.fromJson(response.body().string(), ContactResponseModel.class);
            String msg = contactResponseModel.getMessage();
        System.out.println("MESSAGE : "+msg);
            // kak dostat ID ?  msg.substring(msg.lastIndexOf(" ")+1); No est' drugoi variant
       id = extractId(msg);
        System.out.println(id);
        Assert.assertTrue(response.isSuccessful());

    }

}
