package errors.exceptions.api;

import errors.exceptions.AppException;

public class APIException extends AppException {
    private final int statusCode;
    private final String responseBody;

    public APIException(String message, Throwable cause, String hint, int statusCode, String responseBody) {
        super(message, cause, hint);
        this.statusCode = statusCode;
        this.responseBody = responseBody;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getResponseBody() {
        return responseBody;
    }
}
