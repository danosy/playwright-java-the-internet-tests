package tests.api;

import api.client.AuthClient;
import api.errors.APIError;
import api.model.LoginRequest;
import api.model.LoginResponse;
import com.google.inject.Guice;
import com.google.inject.Injector;
import config.TestConfig;
import io.restassured.RestAssured;
import org.apiguardian.api.API;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import results.Result;

public class AuthTest {
    private AuthClient authClient;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector();
        authClient = injector.getInstance(AuthClient.class);
        RestAssured.baseURI = TestConfig.baseAPIUrl();
    }

    @Test
    void testLoginRequest() {
        LoginRequest loginRequest = new LoginRequest("emilys", "emilyspass");
        Result<LoginResponse, APIError> loginResult = authClient.login(loginRequest);
        if (loginResult instanceof Result.Ok<LoginResponse, APIError> ok) {
            LoginResponse loginResponse = ok.value;
            System.out.println(loginResponse);
        }
    }
}