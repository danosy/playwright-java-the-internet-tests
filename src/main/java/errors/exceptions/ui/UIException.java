package errors.exceptions.ui;

import errors.exceptions.AppException;

public class UIException extends AppException {
    public UIException(String message, Throwable cause, String hint) {
        super(message, cause, hint);
    }
}
