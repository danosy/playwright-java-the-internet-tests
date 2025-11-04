package ui.errors;

import errors.Error;

public final class UIError extends Error {
    private final String reason;

    public UIError(String reason, String hint, Throwable cause) {
        super(hint, cause);
        this.reason = reason;
    }
    public String reason() { return reason; }
}
