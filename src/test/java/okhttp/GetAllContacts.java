package okhttp;

import helpers.PropertiesReader;
import helpers.TestConfig;
import models.ContactListModel;
import models.ContactModel;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import org.openqa.selenium.json.Json;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetAllContacts {
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoibXltZWdhbWFpbEBtYWlsLmNvbSIsImlzcyI6IlJlZ3VsYWl0IiwiZXhwIjoxNzExMTg4ODIwLCJpYXQiOjE3MTA1ODg4MjB9.gbe2Fgqy433MLtsTQR2WZEK3qquNHasUOJ0KS0eBbOY";
// Bearer Authentication - Autonthicacia Nositelya.
    @Test
    public void getAllContactsPositive() throws IOException {
// we need to create header with authorization.
        Request request = new Request.Builder()
                .url(PropertiesReader.getProperty("getAllContacts"))
                .addHeader("Authorization", PropertiesReader.getProperty("token")).build();

        Response response = TestConfig.client.newCall(request).execute();
        String responseBody = response.body().string();
        Assert.assertTrue(response.isSuccessful());
        ContactListModel contacts = TestConfig
                .gson
                .fromJson(responseBody, ContactListModel.class);

        for (ContactModel contactModel : contacts.getContacts()){
           // System.out.println("Contact's ID : "+contactModel.getId());
            System.out.println(contactModel.getName());
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        }
    }
// dz - Add new contakt metod!

}
