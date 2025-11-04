package errors.exceptions.api;

import errors.exceptions.AppException;

public class APINetworkException extends AppException {
    public APINetworkException(String message, Throwable cause) {
        super(message, cause, "Network issue");
    }
}
