package restassured;

import helpers.PropertiesReader;
import helpers.TestHelper;
import io.restassured.RestAssured;
import models.ContactListModel;
import models.ContactModel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetAllContactsTest implements TestHelper {

    @BeforeMethod
    public void precondition(){
        RestAssured.baseURI = PropertiesReader.getProperty("getAllContacts");
    }

    @Test
    public void getAllContactsPositive(){
        ContactListModel contactList = given()
                .header(AuthorizationHeader, PropertiesReader.getProperty("token"))
                .when()
                .get(PropertiesReader.getProperty("getAllContacts"))
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .as(ContactListModel.class);

        for (ContactModel contact : contactList.getContacts()){
            System.out.println(contact.getEmail());
            System.out.println(contact.getId());
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
        }

    }
}
