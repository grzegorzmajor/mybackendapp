package ovh.major.mybackendapp;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TypicalScenarioUserWantToSeeAndAddPostsIntegrationTest extends DBIntegrationTest {

    @LocalServerPort
    private int port;

    @Test
    @Order(1)
    public void shouldGiveStatus200AndEmptyContentWhenUserTriesToGetPostsAndDatabaseWillEmpty() {
        given().port(port)
                .when()
                    .get("/posts?page=0&size=2")
                .then()
                    .statusCode(200)
                    .assertThat()
                    .body("content", equalTo(emptyList()));
    }

    @Test
    @Order(2)
    public void shouldGiveStatus403WhenUserTriesLoginWithBadCredentials() {
        given().port(port)
                    .body("{\"name\":\"badusername\",\"password\":\"badpass\"")
                .when()
                    .post("/login")
                .then()
                    .statusCode(403);
    }

    @Test
    @Order(3)
    public void shouldGiveStatus403WhenUserTriesGetPostsWithUnpublished() {
        given().port(port)
                .when()
                    .get("/posts/with-unpublished?page=0&size=2")
                .then()
                    .statusCode(403);
    }

    @Test
    @Order(4)
    public void shouldGivenStatus403WhenUserTriesGetPostWithUnpublishedByAddingRandomToken() {
        given().port(port)
                    .header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYWpvckdyemVnb3J6IiwiaXNzIjoibXktYmFja2VuZCIsImV4cCI6MTY5NTg0Nzc0MSwiaWF0IjoxNjk1NzYxMzQxfQ.D5KXFq52GTOOOaXAT48_iRqj1BILZCP-uBoPe2hxHWk")
                .when()
                    .get("/posts/with-unpublished?page=0&size=2")
                .then()
                    .statusCode(403);
    }




}
