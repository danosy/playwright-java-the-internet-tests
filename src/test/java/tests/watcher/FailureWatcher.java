package tests.watcher;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;
import errors.ErrorContext;
import errors.exceptions.AppException;
import errors.interfaces.IErrorHandler;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Supplier;

public class FailureWatcher implements TestWatcher, AfterEachCallback {

    // Removed ThreadLocal as it's no longer needed with getExecutionException()
    // private static final ThreadLocal<Boolean> testFailedStatus = ThreadLocal.withInitial(() -> false);

    private final IErrorHandler handler;
    private final Path artifactsDir;
    private final Supplier<Page> pageSupplier;
    private final Supplier<BrowserContext> contextSupplier;
    private final Runnable cleanupCallback;

    public FailureWatcher(IErrorHandler errorHandler, Path artifactsDir, Supplier<Page> pageSupplier, Supplier<BrowserContext> contextSupplier, Runnable cleanupCallback) {
        this.handler = errorHandler;
        this.artifactsDir = artifactsDir;
        this.pageSupplier = pageSupplier;
        this.contextSupplier = contextSupplier;
        // Simplified cleanupCallback as ThreadLocal is removed
        this.cleanupCallback = cleanupCallback;
    }

    @Override
    public void testFailed(ExtensionContext extensionContext, Throwable cause) {
        // This method is still called, but we will rely on getExecutionException() in afterEach
        System.out.println("[FailureWatcher] TestWatcher: testFailed for '" + extensionContext.getDisplayName() + "'. Cause: " + cause.getMessage());

        if (cause instanceof AppException appException) {
            var errorContext = new ErrorContext.Builder()
                    .testName(extensionContext.getDisplayName())
                    .artifacts(artifactsDir)
                    .page(pageSupplier == null ? null : pageSupplier.get())
                    .build();
            handler.handle(appException, errorContext);
        }
    }

    @Override
    public void testSuccessful(ExtensionContext extensionContext) {
        // This method is still called, but we will rely on getExecutionException() in afterEach
        System.out.println("[FailureWatcher] TestWatcher: testSuccessful for '" + extensionContext.getDisplayName() + "'.");
    }

    @Override
    public void testAborted(ExtensionContext extensionContext, Throwable cause) {
        // This method is still called, but we will rely on getExecutionException() in afterEach
        System.out.println("[FailureWatcher] TestWatcher: testAborted for '" + extensionContext.getDisplayName() + "'. Cause: " + cause.getMessage());
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) {
        boolean failed = extensionContext.getExecutionException().isPresent(); // Correctly check for failure
        System.out.println("[FailureWatcher] In afterEach for test '" + extensionContext.getDisplayName() + "'. Test failed: " + failed);

        if (failed) {
            System.out.println("[FailureWatcher] Calling saveScreenshot for failed test: " + extensionContext.getDisplayName());
            saveScreenshot(extensionContext.getDisplayName());
            saveTrace(extensionContext.getDisplayName());
        }

        if (cleanupCallback != null) cleanupCallback.run();
    }

    private void saveScreenshot(String testName) {
        System.out.println("[FailureWatcher] Attempting to save screenshot for test: " + testName);
        if (pageSupplier == null) {
            System.out.println("[FailureWatcher] pageSupplier is null, skipping screenshot");
            return;
        }
        Page page = pageSupplier.get();
        if (page == null) {
            System.out.println("[FailureWatcher] page is null, skipping screenshot");
            return;
        }
        System.out.println("[FailureWatcher] Page object is available for screenshot.");

        try {
            byte[] screenshotBytes = page.screenshot();

            String safeTestName = testName.replaceAll("[^a-zA-Z0-9_\\-]", "_");
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            Path screenshotPath = artifactsDir.resolve("screenshots").resolve(safeTestName + "_" + timestamp + ".png");
            screenshotPath.getParent().toFile().mkdirs();
            Files.write(screenshotPath, screenshotBytes);
            System.out.println("[FailureWatcher] Screenshot saved to: " + screenshotPath.toAbsolutePath());

            Allure.addAttachment("Screenshot on failure", "image/png", new ByteArrayInputStream(screenshotBytes), "png");
            System.out.println("[FailureWatcher] Screenshot attached to Allure successfully");
        } catch (Exception exception) {
            System.out.println("[FailureWatcher] Screenshot failed: " + exception.getMessage());
            exception.printStackTrace();
        }
    }

    private void saveTrace(String testName) {
        if (!Boolean.getBoolean("tracing.enabled")) return;
        if (contextSupplier == null) return;
        BrowserContext browserContext = contextSupplier.get();
        if (browserContext == null) return;

        String safeTestName = testName.replaceAll("[^a-zA-Z0-9_\\-]", "_");
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        Path tracePath = artifactsDir.resolve("traces").resolve(safeTestName + "_" + timestamp + ".zip");
        tracePath.getParent().toFile().mkdirs();

        browserContext.tracing().stop(new Tracing.StopOptions().setPath(tracePath));
    }
}