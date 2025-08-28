package config;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class TestConfig {
    public static final String BASE_URL1 = "https://petstore.swagger.io/v2";
    public static final String BASE_URL2 = "https://parabank.parasoft.com/parabank/index.htm";
    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL1)
                .setContentType(ContentType.JSON)
                .build();
    }
}
