package ovh.major.mybackendapp;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = MyBackendApplication.class)
public class DBIntegrationTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
    }

}
