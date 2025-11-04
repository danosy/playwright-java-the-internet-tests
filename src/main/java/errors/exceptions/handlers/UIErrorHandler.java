package errors.exceptions.handlers;

import com.microsoft.playwright.Page;
import errors.ErrorContext;
import errors.exceptions.AppException;
import errors.interfaces.IErrorHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UIErrorHandler implements IErrorHandler {
    private static final Logger log = LogManager.getLogger(UIErrorHandler.class);

    @Override
    public void handle(AppException exception, ErrorContext ctx) {
        log.error("UI error: {} | hint={} | test={}", exception.getMessage(), exception.getHint(), ctx.testName);
        try {
            if (ctx.page != null) {
                var shot = ctx.artifactsDir.resolve(ctx.testName + "_fail.png");
                ctx.page.screenshot(new Page.ScreenshotOptions().setPath(shot).setFullPage(true));
                log.info("Saved screenshot -> {}", shot.toAbsolutePath());
            }
        } catch (Exception io) {
            log.warn("Failed to save UI artifacts", io);
        }
    }
}
