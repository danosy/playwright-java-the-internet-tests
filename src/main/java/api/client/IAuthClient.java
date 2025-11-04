package api.client;

import api.errors.APIError;
import api.model.LoginRequest;
import api.model.LoginResponse;
import results.Result;

public interface IAuthClient {
    Result<LoginResponse, APIError> login(LoginRequest req);
}
