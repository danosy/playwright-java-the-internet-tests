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
    private static final ThreadLocal<Injector> INJECTOR = ThreadLocal.withInitial(
            () -> Guice.createInjector(new PlaywrightModule())
    );

    private TestsSetup testSetup;

    @RegisterExtension
    static FailureWatcher uiWatcher = new FailureWatcher(
            new UIErrorHandler(),
            Paths.get(System.getProperty("artifactsDir", "build/artifacts")),
            PAGE::get,
            BROWSER_CONTEXT::get,
            () -> {
                PAGE.remove();
                BROWSER_CONTEXT.remove();
            }
    );

    @BeforeEach
    public void setUpBase() {
        testSetup = new TestsSetup(INJECTOR.get());
        PAGE.set(testSetup.getPage());
        BROWSER_CONTEXT.set(testSetup.getBrowserContext());
    }

    @AfterEach
    public void tearDownBase() {
        testSetup.close();
        INJECTOR.remove();
    }

    public PageFactory getPageFactory() {
        return testSetup.getPageFactory();
    }
}