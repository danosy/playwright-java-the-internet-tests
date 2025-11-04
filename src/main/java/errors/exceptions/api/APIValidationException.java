package errors.exceptions.api;

import errors.exceptions.AppException;

public class APIValidationException extends APIException {
    public APIValidationException(String message, Throwable cause, String hint, int statusCode, String responseBody) {
        super(message, cause, hint, statusCode, responseBody);
    }
}
