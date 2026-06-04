package api.client;

import api.errors.APIError;
import api.model.LoginRequest;
import api.model.LoginResponse;
import api.model.enums.Endpoints;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import results.Result;

public class AuthClient extends BaseAPIClient implements IAuthClient {

    private static final Logger log = LogManager.getLogger(AuthClient.class);

    @Override
    public Result<LoginResponse, APIError> login(LoginRequest request) {
        try {
            log.info("POST {}", Endpoints.AUTH_LOGIN.getFullPath());
            Response result = post(Endpoints.AUTH_LOGIN.getFullPath(), request);
            int statusCode = result.statusCode();
            if (statusCode >= 200 && statusCode < 300)
                return new Result.Ok<>(result.as(LoginResponse.class));
            return new Result.Err<>(new APIError(statusCode, safeBody(result), "Login should succeed", null));
        } catch (Exception exception) {

            return new Result.Err<>(new APIError(-1, null, "Unexpected client error", exception));
        }
    }

    private static String safeBody(Response response) {
        try { return response.getBody() == null ? "" : response.getBody().asPrettyString(); }
        catch (Exception exception) { return "<unreadable>"; }
    }
}