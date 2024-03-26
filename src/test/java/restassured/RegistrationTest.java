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
