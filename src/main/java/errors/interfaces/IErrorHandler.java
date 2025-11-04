package errors.interfaces;

import errors.ErrorContext;
import errors.exceptions.AppException;

public interface IErrorHandler {
    void handle(AppException exception, ErrorContext errorContext);
}
