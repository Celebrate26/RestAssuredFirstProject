package UserRegistration;

import APIRequest.APIRequest;
import DBConnection.DBConnection;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.sql.SQLException;
import static BaseURLs.BaseURL.baseURL;
import static org.hamcrest.CoreMatchers.equalTo;


public class UserRegistration {

    static String registeredEmail;
    static String userId;
    static String adminToken;

    @BeforeClass
    public void setup() throws SQLException {
        DBConnection.dbConnection();
    }

    @Test(priority = 1)
    public void adminLoginTest() {
        Response response = APIRequest.loginUserResponse(DBConnection.getEmail, DBConnection.getPassword)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true))
                .extract().response();

        adminToken = response.path("data.token");
    }

    @Test(priority = 2, dependsOnMethods = "adminLoginTest")
    public void userRegistration() {
        registeredEmail = Faker.instance().internet().emailAddress();

        Response response = APIRequest.registerUserResponse("Okuhle", "Celebrate", registeredEmail, "Okuhle@543", "1deae17a-c67a-4bb0-bdeb-df0fc9e2e526")
                .then()
                .log().all()
                .assertThat()
                .statusCode(201)
                .body("success", equalTo(true))
                .extract().response();

        userId = response.path("data.id");
    }

    @Test(priority = 3, dependsOnMethods = "userRegistration")
    public void approveUserRegistration() {
        APIRequest.approveUserRegistrationResponse()
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true));
    }

    @Test(priority = 4, dependsOnMethods = "approveUserRegistration")
    public void userLoginTest() {
        APIRequest.loginUserResponse(registeredEmail, "Okuhle@543")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true));
    }

    @Test(priority = 5, dependsOnMethods = "userLoginTest")
    public void deleteUser() {
        Response response = RestAssured.given()
                .baseUri(baseURL)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + adminToken)
                .log().all()
                .when()
                .delete("/APIDEV/admin/users/" + userId)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true))
                .extract().response();
    }
}


