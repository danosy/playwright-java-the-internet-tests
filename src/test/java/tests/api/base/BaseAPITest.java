package tests.api.base;

import errors.exceptions.handlers.APIErrorHandler;
import org.junit.jupiter.api.extension.RegisterExtension;
import tests.watcher.FailureWatcher;

import java.nio.file.Paths;

public class BaseAPITest {
    @RegisterExtension
    static FailureWatcher apiWatcher =
            new FailureWatcher(
                    new APIErrorHandler(),
                    Paths.get(System.getProperty("artifactsDir", "build/artifacts")),
                    null,
                    null
            );
}