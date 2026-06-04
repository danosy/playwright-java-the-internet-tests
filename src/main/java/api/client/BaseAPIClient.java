package api.client;

import config.TestConfig;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


import static io.restassured.RestAssured.given;

public class BaseAPIClient {
    protected static RequestSpecification spec = null;

    public BaseAPIClient() {
        spec = new RequestSpecBuilder()
                .setBaseUri(TestConfig.getBaseAPIUrl())
                .setContentType(ContentType.JSON)
                .build();
    }

    public static Response get(String endpoint) {
        return given()
                .spec(spec)
                .when()
                .get(endpoint);
    }

    public static Response post(String endpoint, Object body) {
        return given()
                .spec(spec)
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(body)
                .when()
                .post(endpoint);
    }

    public static Response put(String endpoint, Object body) {
        return given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(body)
                .when()
                .put(endpoint);
    }

    public static Response delete(String endpoint) {
        return given()
                .spec(spec)
                .when()
                .delete(endpoint);
    }

//    protected ErrorContext createContext(String testName, Logger log) {
//        try {
//            Path dir = Path.of("build", "artifacts", testName).toAbsolutePath();
//            Files.createDirectories(dir);
//
//            return new ErrorContext.Builder()
//                    .log(log)
//                    .artifacts(dir)
//                    .testName(testName)
//                    .build();
//        } catch (Exception e) {
//            throw new RuntimeException("Cannot create error context", e);
//        }
//    }
}