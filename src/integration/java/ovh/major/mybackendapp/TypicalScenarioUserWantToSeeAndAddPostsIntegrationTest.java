package ovh.major.mybackendapp;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;


import static io.restassured.RestAssured.given;
import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Log4j2
public class TypicalScenarioUserWantToSeeAndAddPostsIntegrationTest extends DBIntegrationTest {

    @LocalServerPort
    private int port;

    private static String token;

    @Test
    @Order(1)
    public void shouldGiveStatus200AndEmptyContentWhenUserTriesToGetPostsAndDatabaseWillEmpty() {
        given().port(port)
                .when()
                    .get("/posts?page=0&size=2")
                .then()
                    .statusCode(200)
                    .assertThat()
                    .body("content", is(equalTo(emptyList())));
    }

    @Test
    @Order(2)
    public void shouldGiveStatus403WhenUserTriesLoginWithBadCredentials() {
        given().port(port)
                    .contentType(ContentType.JSON)
                    .body(Examples.BAD_CREDENTIALS)
                .when()
                    .post("/login")
                .then()
                    .statusCode(401)
                    .and()
                    .assertThat()
                    .body("message", is(equalTo("Bad credentials!")))
                    .and()
                    .assertThat()
                    .body("status",is(equalTo(HttpStatus.UNAUTHORIZED.name())));
    }

    @Test
    @Order(3)
    public void shouldGiveStatus403WhenUserIsNotLoggedInAndTriesGetPostsWithUnpublished() {
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

    @Test
    @Order(5)
    public void shouldGiven403WhenUserIsNotLoggedInAndTriesAddNewPost() {
        given().port(port)
                    .contentType(ContentType.JSON)
                    .body(Examples.POST)
                .when()
                    .post("/posts")
                .then()
                    .statusCode(403);
    }

    @Test
    @Order(6)
    public void shouldGiven403WhenUserIsNotLoggedInAndTriesGetParagraphWithSpecifiedId() {
        given().port(port)
                .when()
                    .get("/paragraphs/1")
                .then()
                    .statusCode(403);
    }

    @Test
    @Order(7)
    public void shouldGiven403WhenUserIsNotLoggedInAndTriesEditParagraph() {
        given().port(port)
                    .contentType(ContentType.JSON)
                    .body(Examples.PARAGRAPH)
                .when()
                    .patch("/paragraphs/1")
                .then()
                    .statusCode(403);
    }

    @Test
    @Order(8)
    public void shouldGiven403WhenUserIsNotLoggedInAndTriesGetTagsDictionary() {
        given().port(port)
                .when()
                    .get("/paragraphs/1")
                .then()
                    .statusCode(403);
    }

    @Test
    @Order(9)
    public void shouldGiven403WhenUserIsNotLoggedInAndTriesEditTagInDictionary() {
        given().port(port)
                    .contentType(ContentType.JSON)
                    .body(Examples.TAG)
                .when()
                    .patch("/dict/1")
                .then()
                    .statusCode(403);
    }

    @Test
    @Order(10)
    public void shouldGiven403WhenUserIsNotLoggedInAndTriesDeleteTagInDictionary() {
        given().port(port)
                .when()
                    .delete("/dict/1")
                .then()
                    .statusCode(403);
    }

    @Test
    @Order(11)
    public void shouldGiven200AndBodyWithTokenAndUserNameWhenUserTriesLoginWithCorrectLoginDetails() {
        Response response =
                given().port(port)
                    .contentType(ContentType.JSON)
                    .body(Examples.CORRECT_CREDENTIALS)
                .when()
                    .post("/login")
                .then()
                    .statusCode(200)
                    .and()
                    .assertThat()
                    .body("token", is(not(empty())))
                    .and()
                    .assertThat()
                    .body("name",is(equalTo("user")))
                    .and()
                    .extract()
                    .response();

        //token is needed for next tests
        token = response.path("token");
        log.info(token);
    }

    @Test
    @Order(12)
    public void shouldGiven400AndBodyWithErrorDetailsWhenUserTriesAddingThePostButThereIsNoUsedTagInDictionary() {
        String authorisation = "Bearer " + token;
        RestAssured.given()
                    .port(port)
                    .header("Authorization", authorisation)
                    .contentType(ContentType.JSON)
                    .body(Examples.POST)
                .when()
                    .post("/posts")
                .then()
                    .statusCode(400)
                    .and()
                    .assertThat()
                    .body("message", is(equalTo("Something goes wrong! Maybe there is no used tag in dictionary.")))
                    .and()
                    .assertThat()
                    .body("status",is(equalTo(HttpStatus.BAD_REQUEST.name())));
    }


}
