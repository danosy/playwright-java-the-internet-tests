package tests.watcher;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;
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
    private final Supplier<Page> pageSupplier;
    private final Supplier<BrowserContext> contextSupplier;

    public FailureWatcher(IErrorHandler errorHandler, Path dir, Supplier<Page> pageSupplier, Supplier<BrowserContext> contextSupplier) {
        this.handler = errorHandler;
        this.artifactsDir = dir;
        this.pageSupplier = pageSupplier;
        this.contextSupplier = contextSupplier;
    }

    @Override
    public void testFailed(ExtensionContext extensionContext, Throwable cause) {
        saveTrace(extensionContext.getDisplayName());

        if (cause instanceof AppException appException) {
            var errorContext = new ErrorContext.Builder()
                    .testName(extensionContext.getDisplayName())
                    .artifacts(artifactsDir)
                    .page(pageSupplier == null ? null : pageSupplier.get())
                    .build();
            handler.handle(appException, errorContext);
        }
    }

    private void saveTrace(String testName) {
        if (!Boolean.getBoolean("tracing.enabled")) return;
        if (contextSupplier == null) return;
        BrowserContext browserContext = contextSupplier.get();
        if (browserContext == null) return;

        String safeTestName = testName.replaceAll("[^a-zA-Z0-9_\\-]", "_");
        String timestamp = java.time.LocalDateTime.now()
                .format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        Path tracePath = artifactsDir.resolve("traces").resolve(safeTestName + "_" + timestamp + ".zip");
        tracePath.getParent().toFile().mkdirs();

        browserContext.tracing().stop(new Tracing.StopOptions().setPath(tracePath));
    }
}