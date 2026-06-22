package tests.ui.base;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import errors.exceptions.handlers.UIErrorHandler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.RegisterExtension;
import tests.setup.TestsSetup;
import tests.watcher.FailureWatcher;
import ui.factory.PageFactory;
import ui.ioc.PlaywrightModule;

import java.nio.file.Paths;

public class BaseUITest {
    protected static final ThreadLocal<Page> PAGE = new ThreadLocal<>();
    protected static final ThreadLocal<BrowserContext> BROWSER_CONTEXT = new ThreadLocal<>();
    protected static final ThreadLocal<TestsSetup> TEST_SETUP = new ThreadLocal<>(); // Made testSetup ThreadLocal
    private static final ThreadLocal<Injector> INJECTOR = ThreadLocal.withInitial(
            () -> Guice.createInjector(new PlaywrightModule())
    );

    // Removed private TestsSetup testSetup;

    @RegisterExtension
    static FailureWatcher uiWatcher = new FailureWatcher(
            new UIErrorHandler(),
            Paths.get(System.getProperty("artifactsDir", "build/artifacts")),
            PAGE::get,
            BROWSER_CONTEXT::get,
            () -> {
                // This cleanupCallback is executed by FailureWatcher after it has processed the test result
                if (TEST_SETUP.get() != null) {
                    TEST_SETUP.get().close(); // Close Playwright resources
                }
                PAGE.remove();
                BROWSER_CONTEXT.remove();
                TEST_SETUP.remove(); // Clean up the ThreadLocal for TestsSetup
            }
    );

    @BeforeEach
    public void setUpBase() {
        TestsSetup currentTestSetup = new TestsSetup(INJECTOR.get());
        TEST_SETUP.set(currentTestSetup); // Set the ThreadLocal TestsSetup
        PAGE.set(currentTestSetup.getPage());
        BROWSER_CONTEXT.set(currentTestSetup.getBrowserContext());
    }

    @AfterEach
    public void tearDownBase() {
        // testSetup.close() is now handled by the cleanupCallback in FailureWatcher
        INJECTOR.remove();
    }

    public PageFactory getPageFactory() {
        return TEST_SETUP.get().getPageFactory(); // Access via ThreadLocal
    }
}