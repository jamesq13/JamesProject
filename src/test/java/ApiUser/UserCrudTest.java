package ApiUser;

import ApiBase.BaseApiTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(AllureJunit5.class)
public class UserCrudTest extends BaseApiTest {

    private static Stream<Arguments> userCreate() {
        return Stream.of(
                Arguments.of(100, "Den", "Denis", "Petrov", "den123@mail.ru", "Den123!", "string", 1000),
                Arguments.of(101, "Kirya", "Kiril", "Griwko", "kirya123@mail.ru", "Kirya123!", "string", 1001),
                Arguments.of(102, "Andr", "Andrey", "Miwkov", "andr123@mail.ru", "Miwkov!123", "string", 1002),
                Arguments.of(103, "Vadik", "Vadim", "Obuxov", "vadik123@mail.ru", "Vadik123!", "string", 1003)
        );
    }

    @Order(1)
    @ParameterizedTest
    @DisplayName("Create User")
    @MethodSource("userCreate")
    public void testCreateUser(int id, String username, String firstname, String lastname, String email, String password, String phone, int UserStatus) {
        String requestBody = """
                {
                   "id": %d,
                   "username": "%s",
                   "firstName": "%s",
                   "lastName": "%s",
                   "email": "%s",
                   "password": "%s",
                   "phone": "%s",
                   "userStatus": %d
                 }
                """.formatted(id, username, firstname, lastname, email, password, phone, UserStatus);

        Response response = given()
                .spec(spec)
                .body(requestBody)
                .when()
                .post("/ApiUser")
                .then()
                .statusCode(200)
                .body("type", equalTo("unknown"))
                .body("message", equalTo(String.valueOf(id)))
                .body("code", equalTo(200))
                .extract().response();

        System.out.println("Create Response: " + response.asPrettyString());
    }

    @Order(3)
    @ParameterizedTest
    @DisplayName("Get user by username")
    @MethodSource("userCreate")
    public void testGetUser(int id, String username, String firstname, String lastname, String email, String password, String phone, int userStatus) {
        Response response = given()
                .spec(spec)
                .when()
                .get("/ApiUser/%s".formatted(username))
                .then()
                .statusCode(200)
                .body("id", equalTo(id))
                .body("username", equalTo(username))
                .body("firstName", equalTo(firstname))
                .body("lastName", equalTo(lastname))
                .body("email", equalTo(email))
                .body("password", equalTo(password))
                .body("phone", equalTo(phone))
                .body("userStatus", equalTo(userStatus))
                .extract().response();

        System.out.println("Get Response: " + response.asPrettyString());
    }

    @Order(2)
    @ParameterizedTest
    @DisplayName("Update User firstname")
    @MethodSource("userCreate")
    public void testUpdateUser(int id, String username, String firstname, String lastname, String email, String password, String phone, int UserStatus) {
        String newFirstname = firstname + "_Updated";

        String requestBody = """
                {
                   "id": %d,
                   "username": "%s",
                   "firstName": "%s",
                   "lastName": "%s",
                   "email": "%s",
                   "password": "%s",
                   "phone": "%s",
                   "userStatus": %d
                 }
                """.formatted(id, username, newFirstname, lastname, email, password, phone, UserStatus);

        Response response = given()
                .spec(spec)
                .body(requestBody)
                .when()
                .put("/ApiUser/%s".formatted(username))
                .then()
                .statusCode(200)
                .body("type", equalTo("unknown"))
                .body("message", equalTo(String.valueOf(id)))
                .body("code", equalTo(200))
                .extract().response();

        System.out.println("Updated user ID: " + id + ", new firstname: " + newFirstname);
        System.out.println("Update Response: " + response.asPrettyString());
    }

    @Order(4)
    @ParameterizedTest
    @DisplayName("Delete user by username")
    @MethodSource("userCreate")
    public void testDeleteUser(int id, String username, String firstname, String lastname, String email, String password, String phone, int UserStatus) {
        Response response = given()
                .spec(spec)
                .when()
                .delete("/ApiUser/{username}",username)
                .then()
                .statusCode(200)
                .body("type", equalTo("unknown"))
                .body("message", equalTo(username))
                .extract().response();

        System.out.println("Delete Response: " + response.asPrettyString());
    }
}
