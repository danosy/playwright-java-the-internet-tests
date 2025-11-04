package api.errors;

import errors.Error;

public final class APIError extends Error {
    private final int status;
    private final String body;

    public APIError(int status, String body, String hint, Throwable cause) {
        super(hint, cause);
        this.status = status;
        this.body = body;
    }
    public int status() { return status; }
    public String body() { return body; }
}
