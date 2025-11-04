package api.config;

import config.TestConfig;
import io.restassured.RestAssured;

public class APIConfig {
    static {
        RestAssured.baseURI = TestConfig.baseAPIUrl();
    }
}
