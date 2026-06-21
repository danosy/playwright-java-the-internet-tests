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
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Supplier;

public class FailureWatcher implements TestWatcher, AfterEachCallback {

    private static final ExtensionContext.Namespace NAMESPACE =
            ExtensionContext.Namespace.create(FailureWatcher.class);

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
        this.cleanupCallback = cleanupCallback;
    }

    @Override
    public void testFailed(ExtensionContext extensionContext, Throwable cause) {
        extensionContext.getStore(NAMESPACE).put("failed", true);

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
        extensionContext.getStore(NAMESPACE).put("failed", false);
    }

    @Override
    public void testAborted(ExtensionContext extensionContext, Throwable cause) {
        extensionContext.getStore(NAMESPACE).put("failed", false);
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) {
        boolean failed = Boolean.TRUE.equals(
                extensionContext.getStore(NAMESPACE).get("failed", Boolean.class)
        );

        if (failed) {
            saveScreenshot(extensionContext.getDisplayName());
            saveTrace(extensionContext.getDisplayName());
        }

        if (cleanupCallback != null) cleanupCallback.run();
    }

    private void saveScreenshot(String testName) {
        if (pageSupplier == null) return;
        Page page = pageSupplier.get();
        if (page == null) {
            System.out.println("[FailureWatcher] page is null, skipping screenshot");
            return;
        }

        try {
            String safeTestName = testName.replaceAll("[^a-zA-Z0-9_\\-]", "_");
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            Path screenshotPath = artifactsDir.resolve("screenshots").resolve(safeTestName + "_" + timestamp + ".png");
            screenshotPath.getParent().toFile().mkdirs();
            page.screenshot(new Page.ScreenshotOptions().setPath(screenshotPath));

            byte[] screenshotBytes = page.screenshot();
            Allure.addAttachment("Screenshot on failure", "image/png", new ByteArrayInputStream(screenshotBytes), "png");
            System.out.println("[FailureWatcher] Screenshot attached to Allure successfully");
        } catch (Exception exception) {
            System.out.println("[FailureWatcher] Screenshot failed: " + exception.getMessage());
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