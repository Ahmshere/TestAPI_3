package restassured;

import helpers.IDExtractor;
import helpers.PropertiesReader;
import helpers.TestHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.ContactModel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class UpdateContactTest implements TestHelper {

    ContactModel contactModel;
    String id;
    @BeforeMethod
    public void precondition(){
        RestAssured.baseURI = PropertiesReader.getProperty("getAllContacts");
        contactModel =
                new ContactModel("TestUser3","HisLgastName","jcdkh@megamail.com","44231763567","LA 345 Sunset beach","hough");
        String message = given()
                .header(AuthorizationHeader, PropertiesReader.getProperty("token"))
                .body(contactModel)
                .contentType(ContentType.JSON)
                .when()
                .post()
                .then()
                .assertThat()
                .statusCode(200).extract().path("message");
        id = IDExtractor.extractId(message);
    }

    @Test
    public void updateContactTest(){
            contactModel.setId(id);
            contactModel.setName("UpdatedName");
        given()
                .header(AuthorizationHeader, PropertiesReader.getProperty("token"))
                .body(contactModel)
                .contentType(ContentType.JSON)
                .when()
                .put()
                .then()
                .assertThat()
                .statusCode(200)
                .assertThat().body("message",containsString("updated"));

    }
}
