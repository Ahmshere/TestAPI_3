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
}
