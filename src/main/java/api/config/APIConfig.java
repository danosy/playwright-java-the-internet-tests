package api.config;

import io.restassured.RestAssured;

public class APIConfig {
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    static {
        RestAssured.baseURI = BASE_URL;
    }
}
