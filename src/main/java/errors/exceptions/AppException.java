package errors.exceptions;

public class AppException extends RuntimeException {
    private String hint;

    public AppException(String message, Throwable cause, String hint) {
        super(message, cause);
        this.hint = hint;
    }

    public String getHint() {
        return hint;
    }
}
