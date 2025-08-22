package base;

import config.TestConfig;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseApiTest {
    protected static RequestSpecification spec;

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = TestConfig.BASE_URL;
        spec = TestConfig.requestSpec();
    }
}
