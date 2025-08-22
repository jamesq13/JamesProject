package pet;

import base.BaseApiTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PetCrudTest extends BaseApiTest {

    private static Stream<Arguments> petCreate() {
        return Stream.of(
                Arguments.of(10, 10, "Cats", "Mirana", "available"),
                Arguments.of(11, 11, "Chicken", "Garry", "pending"),
                Arguments.of(12, 12, "Dogs", "Charlie", "sold"),
                Arguments.of(13, 13, "Elephant", "Nancy", "available"),
                Arguments.of(14, 14, "Hourse", "Lucy", "available")
        );
    }

    @Order(1)
    @ParameterizedTest
    @DisplayName("Create pet")
    @MethodSource("petCreate")
    public void testValidPets(int id, int categoryId, String categoryName, String name, String status) {
        String requestBody = """
                {
                  "id": %d,
                  "category": {
                    "id": %d,
                    "name": "%s"
                  },
                  "name": "%s",
                  "status": "%s"
                }
                """.formatted(id, categoryId, categoryName, name, status);

        Response response = given()
                .spec(spec)
                .body(requestBody)
                .when()
                .post("/pet")
                .then()
                .statusCode(200)
                .body("id", equalTo(id))
                .body("name", equalTo(name))
                .body("status", equalTo(status))
                .body("category.id", equalTo(categoryId))
                .body("category.name", equalTo(categoryName))
                .body("id", notNullValue())
                .body("name", notNullValue())
                .body("status", notNullValue())
                .body("category.id", notNullValue())
                .body("category.name", notNullValue())
                .extract().response();
        int createdId = response.jsonPath().getInt("id");
        System.out.println("Created pet ID: " + createdId);
        System.out.println("Response: " + response.asPrettyString());
    }
    @Order(2)
    @ParameterizedTest
    @DisplayName("Get pet by ID")
    @MethodSource("petCreate")
    public void testGetPet(int id, int categoryId, String categoryName, String name, String status) {
        Response response = given()
                .spec(spec)
                .when()
                .get("/pet/{petId}", id) // используем ID из аргументов
                .then()
                .statusCode(200)
                .body("id", equalTo(id))
                .body("name", equalTo(name))
                .body("status", equalTo(status))
                .body("category.id", equalTo(categoryId))
                .body("category.name", equalTo(categoryName))
                .extract().response();

        System.out.println("Get Response: " + response.asPrettyString());
    }
    @Order(3)
    @ParameterizedTest
    @DisplayName("Update pet name")
    @MethodSource("petCreate")
    public void testUpdatePetName(int id, int categoryId, String categoryName, String oldName, String status) {
        String newName = oldName + "_Updated";

        String requestBody = """
                {
                  "id": %d,
                  "category": {
                    "id": %d,
                    "name": "%s"
                  },
                  "name": "%s",
                  "status": "%s"
                }
                """.formatted(id, categoryId, categoryName, newName, status);

        Response response = given()
                .spec(spec)
                .body(requestBody)
                .when()
                .put("/pet")
                .then()
                .statusCode(200)
                .body("id", equalTo(id))
                .body("name", equalTo(newName))
                .body("status", equalTo(status))
                .body("category.id", equalTo(categoryId))
                .body("category.name", equalTo(categoryName))
                .extract().response();

        System.out.println("Updated pet ID: " + id + ", new name: " + newName);
        System.out.println("Response: " + response.asPrettyString());
    }
    @Order(4)
    @ParameterizedTest
    @DisplayName("Delete pet by ID")
    @MethodSource("petCreate")
    public void testDeletePet(int id, int categoryId, String categoryName, String name, String status) {
        Response response = given()
                .spec(spec)
                .when()
                .delete("/pet/{petId}", id)
                .then()
                .statusCode(200)
                .body("message", equalTo(String.valueOf(id))) // API обычно возвращает ID удалённого питомца
                .extract().response();

        System.out.println("Delete Response: " + response.asPrettyString());
    }
}