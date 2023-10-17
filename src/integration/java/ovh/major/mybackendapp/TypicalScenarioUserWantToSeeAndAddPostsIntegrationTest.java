package ovh.major.mybackendapp;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Log4j2
public class TypicalScenarioUserWantToSeeAndAddPostsIntegrationTest extends DBIntegrationTest {

    private static String token;
    private static Integer addedPostId;

    private static Integer someParagraphId;
    private static Integer someOtherParagraphId;

    @LocalServerPort
    private int port;

    @Test
    @Order(1)
    public void shouldGivenStatus200AndEmptyContentWhenUserTriesToGetPostsAndDatabaseWillEmpty() {
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
    public void shouldGivenStatus403WhenUserTriesLoginWithBadCredentials() {
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
    public void shouldGivenStatus403WhenUserIsNotLoggedInAndTriesGetPostsWithUnpublished() {
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
                    .header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYWpvckdyemVnb3J6IiwiaXNzIjoibXktYmFja2VuZCIsImV4cCI6MTY5NzE0NTg0MiwiaWF0IjoxNjk3MDU5NDQyfQ.j5dnR07zwM79iOnH3EaOVvRRN39JJHCpuoGfkuNgGzg")
                .when()
                    .get("/posts/with-unpublished?page=0&size=2")
                .then()
                    .statusCode(403);
    }

    @Test
    @Order(5)
    public void shouldGivenStatus403WhenUserIsNotLoggedInAndTriesAddNewPost() {
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
    public void shouldGivenStatus403WhenUserIsNotLoggedInAndTriesGetParagraphWithSpecifiedId() {
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
    public void shouldGivenStatus403WhenUserIsNotLoggedInAndTriesPatchParagraph() {
        RestAssured
                .given()
                    .port(port)
                    .contentType(ContentType.JSON)
                    .body(Examples.PARAGRAPH)
                .when()
                    .patch("/paragraphs")
                .then()
                    .statusCode(403);
    }

    @Test
    @Order(8)
    public void shouldGivenStatus403WhenUserIsNotLoggedInAndTriesGetTagsDictionary() {
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
    public void shouldGivenStatus403WhenUserIsNotLoggedInAndTriesEditTagInDictionary() {
        RestAssured
                .given()
                    .port(port)
                    .contentType(ContentType.JSON)
                    .body(Examples.TAG_H3)
                .when()
                    .patch("/dict/1")
                .then()
                    .statusCode(403);
    }

    @Test
    @Order(10)
    public void shouldGivenStatus403WhenUserIsNotLoggedInAndTriesDeleteTagInDictionary() {
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
    public void shouldGivenStatus200AndBodyWithTokenAndUserNameWhenUserTriesLoginWithCorrectLoginDetails() {
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
    public void shouldGivenStatus400AndBodyWithErrorDetailsWhenLoggedInUserTriesAddingThePostButThereIsNoUsedTagInDictionary() {
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
        RestAssured
                .given()
                    .port(port)
                    .header("Authorization", getTokenHeaderValue())
                    .contentType(ContentType.JSON)
                    .body(Examples.TAG_H3)
                .when()
                    .post("/dict")
                .then()
                    .statusCode(200);
    }

    @Test
    @Order(14)
    public void shouldGiven409WhenLoggedInUserTriesAddDuplicatedTagToDictionary() {
        RestAssured
                .given()
                    .port(port)
                    .header("Authorization", getTokenHeaderValue())
                    .contentType(ContentType.JSON)
                    .body(Examples.TAG_H3)
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
        Response response = RestAssured //post id is needed for next tests
                .given()
                    .port(port)
                    .header("Authorization", getTokenHeaderValue())
                    .contentType(ContentType.JSON)
                    .body(Examples.getPostWithInTheFutureTimestamp())
                .when()
                    .post("/posts")
                .then()
                    .statusCode(200)
                //post id is needed for next tests
                .and()
                .extract()
                .response();
        addedPostId =  response.path("id");
        someParagraphId = response.path("paragraphs[0].id");
        someOtherParagraphId = response.path("paragraphs[1].id");
    }

    @Test
    @Order(16)
    public void shouldGivenStatus200AndEmptyContentWhenLoggedInUserTriesToGetPostsAndDatabaseWillEmptyAgain() {
        shouldGivenStatus200AndEmptyContentWhenUserTriesToGetPostsAndDatabaseWillEmpty();
    }

    @Test
    @Order(17)
    public void shouldGivenStatus200AndNotEmptyListWhenLoggedInUserTriesGetPostsFromEndpointWithUnpublished() {
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
    public void shouldGivenStatus200WhenLoggedInUserTriesPatchPostWitchChangedPublicationDate() {
        RestAssured
                .given()
                    .port(port)
                    .header("Authorization", getTokenHeaderValue())
                    .param("timestamp", Examples.getCurrentTimestamp())
                    .contentType(ContentType.JSON)
                    //.body(Examples.PARAGRAPHS)
                .when()
                    .patch("/posts/" + addedPostId)
                .then()
                    .statusCode(200)
                    .log();
    }

    @Test
    @Order(19)
    public void shouldGivenStatus200AndPostWhenNotLoggedInUserTriesGetPosts() {
        RestAssured
                .given()
                .port(port).port(port)
                .when()
                    .get("/posts?page=0&size=2")
                .then()
                    .statusCode(200)
                    .assertThat()
                    .body("content", is(not(equalTo(emptyList()))));
    }

    @Test
    @Order(20)
    public void shouldGivenStatus200AndPostWhenLoggedInUserTriesGetPostWithSpecifiedId() {
        RestAssured
                .given()
                    .port(port)
                    .header("Authorization", getTokenHeaderValue())
                .when()
                    .get("/posts/" + addedPostId)
                .then()
                    .statusCode(200);
    }

    @Test
    @Order(21)
    public void shouldGivenStatus400WhenLoggedUserTriesPatchParagraphWitchSpecifiedIdAndNewTagIsNotExistInDictionary(){
        RestAssured
                .given()
                    .port(port)
                    .header("Authorization", getTokenHeaderValue())
                    .contentType(ContentType.JSON)
                    .body(Examples
                            .PARAGRAPH_2
                            .toString()
                            .replace("\"id\":\"xx\",", "\"id\":\"" + someParagraphId +  "\","))
                .when()
                    .patch("/paragraphs")
                .then()
                    .statusCode(400)
                    .assertThat()
                    .body("message", is(equalTo("Something goes wrong! Maybe there is no used tag in dictionary.")));
    }

    @Test
    @Order(22)
    public void shouldGivenStatus200WhenLoggedUserTriesAddNewTagToDictionary(){
        RestAssured
                .given()
                    .port(port)
                    .header("Authorization", getTokenHeaderValue())
                    .contentType(ContentType.JSON)
                    .body(Examples.TAG_P)
                .when()
                    .post("/dict")
                .then()
                    .statusCode(200);
    }

    @ParameterizedTest
    @MethodSource("paragraphsExample")
    @Order(23)
    public void shouldGivenStatus200WhenLoggedUserTriesPatchParagraph(String paragraph){
        RestAssured
                .given()
                    .port(port)
                    .header("Authorization", getTokenHeaderValue())
                    .contentType(ContentType.JSON)
                    .body(paragraph)
                .when()
                    .patch("/paragraphs")
                .then()
                    .statusCode(200);
    }


    @Test
    @Order(24)
    public void shouldGivenStatus200WhenLoggedUserTriesDeleteParagraphWitchSpecifiedId(){
        RestAssured
                .given()
                    .port(port)
                    .header("Authorization", getTokenHeaderValue())
                .when()
                    .delete("/paragraphs/" + someOtherParagraphId)
                .then()
                    .statusCode(204);
    }

    @Test
    @Order(25)
    public void shouldGivenStatus200WhenUserTriesDeletePost() {
        RestAssured
                .given()
                    .port(port)
                    .header("Authorization", getTokenHeaderValue())
                .when()
                    .delete("/posts/" + addedPostId)
                .then()
                    .statusCode(204);
    }

    @Test
    @Order(26)
    public void shouldGivenStatus200AndEmptyListWhenLoggedInUserTriesGetTagsFromDictionary() {
        RestAssured
                .given()
                    .port(port)
                    .header("Authorization", getTokenHeaderValue())
                .when()
                    .get("/dict")
                .then()
                    .statusCode(200)
                    .assertThat()
                    .body("content", is(equalTo(emptyList())));
    }

    @Test
    @Order(27)
    public void shouldGivenStatus200AndEmptyContentWhenUserTriesToGetPostsAndEverythingWasDeleted() {
        shouldGivenStatus200AndEmptyContentWhenUserTriesToGetPostsAndDatabaseWillEmpty();
    }

    private static String getTokenHeaderValue() {
        return "Bearer " + token;
    }

    public static Stream<String> paragraphsExample() {
        return Stream.of(
                Examples.PARAGRAPH
                        .toString()
                        .replace("\"id\":\"xx\",", "\"id\":\"" + someParagraphId +  "\","),
                Examples.PARAGRAPH_2
                        .toString()
                        .replace("\"id\":\"xx\",", "\"id\":\"" + someParagraphId +  "\","));
    }

}
