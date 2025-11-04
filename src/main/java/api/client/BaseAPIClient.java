package api.client;

import errors.ErrorContext;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.Logger;


import java.nio.file.Files;
import java.nio.file.Path;

import static io.restassured.RestAssured.given;

public class BaseAPIClient {
    public static Response get(String endpoint) {
        return given()
                .when()
                .get(endpoint);
    }

    public static Response post(String endpoint, Object body) {
        Response response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(body)
                .when()
                .post(endpoint);

        handleResponseErrors(response);
        return response;
    }

    private static void handleResponseErrors(Response response) {

    }

    public static Response put(String endpoint, Object body) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(body)
                .when()
                .put(endpoint);
    }

    public static Response delete(String endpoint) {
        return given()
                .when()
                .delete(endpoint);
    }


    // src/test/java/tests/BaseApiTest.java
    protected ErrorContext createContext(String testName, Logger log) {
        try {
            Path dir = Path.of("build", "artifacts", testName).toAbsolutePath();
            Files.createDirectories(dir);

            return new ErrorContext.Builder()
                    .log(log)
                    .artifacts(dir)
                    .testName(testName)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Cannot create error context", e);
        }
    }
}