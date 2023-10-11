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

    private static String token;

    private static Integer addedTagId;
    private static String addedPostId;

    @LocalServerPort
    private int port;

    @Test
    @Order(1)
    public void shouldGiveStatus200AndEmptyContentWhenUserTriesToGetPostsAndDatabaseWillEmpty() {
        RestAssured
                .given()
                    .port(port)
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
        RestAssured
                .given()
                    .port(port)
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
                    .body("status", is(equalTo(HttpStatus.UNAUTHORIZED.name())));
    }

    @Test
    @Order(3)
    public void shouldGiveStatus403WhenUserIsNotLoggedInAndTriesGetPostsWithUnpublished() {
        RestAssured
                .given()
                    .port(port)
                .when()
                    .get("/posts/with-unpublished?page=0&size=2")
                .then()
                    .statusCode(403);
    }

    @Test
    @Order(4)
    public void shouldGivenStatus403WhenUserTriesGetPostWithUnpublishedByAddingRandomToken() {
        RestAssured
                .given()
                    .port(port)
                    .header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYWpvckdyemVnb3J6IiwiaXNzIjoibXktYmFja2VuZCIsImV4cCI6MTY5NTg0Nzc0MSwiaWF0IjoxNjk1NzYxMzQxfQ.D5KXFq52GTOOOaXAT48_iRqj1BILZCP-uBoPe2hxHWk")
                .when()
                    .get("/posts/with-unpublished?page=0&size=2")
                .then()
                    .statusCode(403);
    }

    @Test
    @Order(5)
    public void shouldGiven403WhenUserIsNotLoggedInAndTriesAddNewPost() {
        RestAssured
                .given()
                    .port(port)
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
        RestAssured
                .given()
                    .port(port)
                .when()
                    .get("/paragraphs/1")
                .then()
                    .statusCode(403);
    }

    @Test
    @Order(7)
    public void shouldGiven403WhenUserIsNotLoggedInAndTriesEditParagraph() {
        RestAssured
                .given()
                    .port(port)
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
        RestAssured
                .given()
                    .port(port)
                .when()
                    .get("/paragraphs/1")
                .then()
                    .statusCode(403);
    }

    @Test
    @Order(9)
    public void shouldGiven403WhenUserIsNotLoggedInAndTriesEditTagInDictionary() {
        RestAssured
                .given()
                    .port(port)
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
        RestAssured
                .given()
                    .port(port)
                .when()
                    .delete("/dict/1")
                .then()
                    .statusCode(403);
    }

    @Test
    @Order(11)
    public void shouldGiven200AndBodyWithTokenAndUserNameWhenUserTriesLoginWithCorrectLoginDetails() {
        token = RestAssured //token is needed for next tests
                .given()
                    .port(port)
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
                    .body("name", is(equalTo("user")))
                    //token is needed for next tests
                    .and()
                    .extract()
                    .response()
                    .path("token");
    }

    @Test
    @Order(12)
    public void shouldGiven400AndBodyWithErrorDetailsWhenLoggedInUserTriesAddingThePostButThereIsNoUsedTagInDictionary() {
        RestAssured
                .given()
                    .port(port)
                    .header("Authorization", getTokenHeaderValue())
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
                    .body("status", is(equalTo(HttpStatus.BAD_REQUEST.name())));
    }

    @Test
    @Order(13)
    public void shouldGiven200WhenLoggedInUserTriesAddNewTagToDictionary() {
        addedTagId = RestAssured //tag id is needed for next tests
                .given()
                    .port(port)
                    .header("Authorization", getTokenHeaderValue())
                    .contentType(ContentType.JSON)
                    .body(Examples.TAG)
                .when()
                    .post("/dict")
                .then()
                    .statusCode(200)
                    //tag id is needed for next tests
                    .and()
                    .extract()
                    .response()
                    .path("id");
    }

    @Test
    @Order(14)
    public void shouldGiven409WhenLoggedInUserTriesAddDuplicatedTagToDictionary() {
        RestAssured
                .given()
                    .port(port)
                    .header("Authorization", getTokenHeaderValue())
                    .contentType(ContentType.JSON)
                    .body(Examples.TAG)
                .when()
                    .post("/dict")
                .then()
                    .statusCode(409)
                    .and()
                    .assertThat()
                    .body("message", is(equalTo("Something goes wrong! Tag already exist in dictionary.")))
                    .and()
                    .assertThat()
                    .body("status", is(equalTo(HttpStatus.CONFLICT.name())));
    }

    @Test
    @Order(15)
    public void shouldGiven200WhenLoggedInUserTriesAddingPostWithPublicationDateInTheFuture() {
        RestAssured
                .given()
                    .port(port)
                    .header("Authorization", getTokenHeaderValue())
                    .contentType(ContentType.JSON)
                    .body(Examples.getInTheFuturePost())
                .when()
                    .post("/posts")
                .then()
                    .statusCode(200);
    }

    @Test
    @Order(16)
    public void shouldGiveStatus200AndEmptyContentWhenUserTriesToGetPostsAndDatabaseWillEmptyAgain() {
        shouldGiveStatus200AndEmptyContentWhenUserTriesToGetPostsAndDatabaseWillEmpty();
    }

    @Test
    @Order(17)
    public void shouldGiveStatus200AndNotEmptyListWhenGetPostsFromEndpointWithUnpublished() {
        RestAssured
                .given()
                    .port(port)
                    .header("Authorization", getTokenHeaderValue())
                .when()
                    .get("/posts/with-unpublished?page=0&size=2")
                .then()
                    .statusCode(200)
                    .assertThat()
                    .body("content", is(not(equalTo(emptyList()))));
    }

    @Test
    @Order(18)
    public void shouldGiveStatus409WhenUserTriesDeleteTagUsedInExistingPosts() {
        RestAssured
                .given()
                    .port(port)
                    .header("Authorization", getTokenHeaderValue())
                .when()
                    .delete("/dict/"+addedTagId)
                .then()
                    .statusCode(409);
    }


    private static String getTokenHeaderValue() {
        return "Bearer " + token;
    }

}
