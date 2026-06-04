package tests.watcher;

import com.microsoft.playwright.Page;
import errors.ErrorContext;
import errors.exceptions.AppException;
import errors.interfaces.IErrorHandler;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.nio.file.Path;
import java.util.function.Supplier;

public class FailureWatcher implements TestWatcher {
    private final IErrorHandler handler;
    private final Path artifactsDir;
    private final Supplier<Page> pageSupplier; // null for API

    public FailureWatcher(IErrorHandler errorHandler, Path dir, Supplier<Page> pageSupplier) {
        this.handler = errorHandler;
        this.artifactsDir = dir;
        this.pageSupplier = pageSupplier;
    }

    @Override public void testFailed(ExtensionContext ctx, Throwable cause) {
        if (cause instanceof AppException app) {
            var errorContext = new ErrorContext.Builder()
                    .testName(ctx.getDisplayName())
                    .artifacts(artifactsDir)
                    .page(pageSupplier == null ? null : pageSupplier.get())
                    .build();
            handler.handle(app, errorContext);
        }
    }
}
