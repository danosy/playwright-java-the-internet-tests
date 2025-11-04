package errors.exceptions.handlers;

import errors.ErrorContext;
import errors.exceptions.AppException;
import errors.exceptions.api.APIException;
import errors.exceptions.api.APINetworkException;
import errors.exceptions.api.APIValidationException;
import errors.interfaces.IErrorHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Files;

public class APIErrorHandler implements IErrorHandler {
    private static final Logger log = LogManager.getLogger(APIErrorHandler.class);

    @Override
    public void handle(AppException appException, ErrorContext ctx) {
        if (appException instanceof APINetworkException apiNetworkException) {
            log.error("Network/API client error: {} | hint={} | test={}", apiNetworkException.getMessage(), apiNetworkException.getHint(), ctx.testName, apiNetworkException);
            return;
        }
        if (appException instanceof APIValidationException validationException) {
            log.error("API validation error: {} | hint={} | test={}", validationException.getMessage(), validationException.getHint(), ctx.testName, validationException);
            saveBody(validationException, ctx);
            return;
        }
        if (appException instanceof APIException apiException) {
            log.error("API error (status={}): {} | hint={} | test={}",
                    apiException.getStatusCode(), apiException.getMessage(), apiException.getHint(), ctx.testName, apiException);
            saveBody(apiException, ctx);
            return;
        }
        log.error("API error: {} | hint={} | test={}", appException.getMessage(), appException.getHint(), ctx.testName, appException);
    }

    private void saveBody(APIException api, ErrorContext ctx) {
        try {
            var out = ctx.artifactsDir.resolve(ctx.testName + "_response.json");
            Files.writeString(out, api.getResponseBody() == null ? "" : api.getResponseBody());
            log.info("Saved API response -> {}", out.toAbsolutePath());
        } catch (Exception exception) {
            log.warn("Failed saving API response body", exception);
        }
    }
}
