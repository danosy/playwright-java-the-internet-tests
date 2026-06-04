package tests.api;

import api.client.AuthClient;
import api.errors.APIError;
import api.model.LoginRequest;
import api.model.LoginResponse;
import com.google.inject.Guice;
import com.google.inject.Injector;
import errors.exceptions.api.APIException;
import errors.exceptions.api.APINetworkException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import results.Result;
import tests.api.base.BaseAPITest;

public class AuthTest extends BaseAPITest {
    private AuthClient authClient;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector();
        authClient = injector.getInstance(AuthClient.class);
    }

    @Test
    void testSuccessLoginRequest() {
        LoginRequest loginRequest = new LoginRequest("stuipd", "emilyspass");
        Result<LoginResponse, APIError> loginResult = authClient.login(loginRequest);
        if (loginResult instanceof Result.Ok<LoginResponse, APIError> ok) {
            LoginResponse loginResponse = ok.value;
            System.out.println(loginResponse);
        } else {
            Result.Err<LoginResponse, APIError> err = (Result.Err<LoginResponse, APIError>) loginResult;
            APIError e = err.error;
            throw new APIException(e.hint(), e.cause(), e.hint(), e.status(), e.body());
        }
    }

    @Test
    void testFailedLoginRequest() {
        LoginRequest loginRequest = new LoginRequest("billy", "emilyspass");
        Result<LoginResponse, APIError> loginResult = authClient.login(loginRequest);
        if (loginResult instanceof Result.Err<LoginResponse, APIError> apiError) {
            APIError error = apiError.error;
            System.out.println("Error:");
            System.out.println("======");
            System.out.println("Status: " + error.status());
            System.out.println("Body: " + error.body());
            System.out.println("Hint:" + error.hint());
            if (error.cause() != null) System.out.println("Cause: " + error.cause().getMessage());
        }
    }
}