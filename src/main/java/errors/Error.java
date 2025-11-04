// src/main/java/common/errors/AppError.java
package errors;

public abstract class Error {
    private final String hint;
    private final Throwable cause;

    protected Error(String hint, Throwable cause) {
        this.hint = hint;
        this.cause = cause;
    }
    public String hint()   { return hint; }
    public Throwable cause(){ return cause; }
}
