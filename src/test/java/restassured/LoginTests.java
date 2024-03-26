package restassured;

import helpers.PropertiesReader;
import helpers.TestHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.AuthenticationRequestModel;
import models.AuthenticationResponseModel;
import models.ErrorModel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LoginTests implements TestHelper {

    @BeforeMethod
    public void precondition(){
        RestAssured.baseURI = PropertiesReader.getProperty("baseUrl");
       // RestAssured.basePath = PropertiesReader.getProperty("v1path");

    }

    @Test
    public void loginTest(){
        AuthenticationRequestModel authenticationRequestModel =
                AuthenticationRequestModel
                        .username(PropertiesReader.getProperty("existingUserEmail"))
                        .password(PropertiesReader.getProperty("existingUserPassword"));

       AuthenticationResponseModel response =  given()
                .body(authenticationRequestModel)
                .contentType(ContentType.JSON)
                .when()
                .post(PropertiesReader.getProperty("login"))
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .as(AuthenticationResponseModel.class);
        System.out.println(response.getToken());

    }

    @Test
    public void loginTestNegative(){
        AuthenticationRequestModel authenticationRequestModel =
                AuthenticationRequestModel
                        .username("PropertiesReader")
                        .password(PropertiesReader.getProperty("existingUserPassword"));

        ErrorModel error =  given()
                .body(authenticationRequestModel)
                .contentType(ContentType.JSON)
                .when()
                .post(PropertiesReader.getProperty("login"))
                .then().log().all()
                .assertThat()
                .statusCode(401)
                .extract()
                .as(ErrorModel.class);
        System.out.println(error.getMessage());

    }
}
