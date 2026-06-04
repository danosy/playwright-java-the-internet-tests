package tests.ui.base;

import com.microsoft.playwright.Page;
import errors.exceptions.handlers.UIErrorHandler;
import org.junit.jupiter.api.extension.RegisterExtension;
import tests.watcher.FailureWatcher;
import ui.factory.PageFactory;
import tests.setup.TestsSetup;

import java.nio.file.Paths;

public class BaseUITest {
    protected static final ThreadLocal<Page> PAGE = new ThreadLocal<>();
    private final PageFactory pageFactory;

    public BaseUITest() {
        TestsSetup testSetup = new TestsSetup();
        this.pageFactory = testSetup.getPageFactory();
    }

    @RegisterExtension
    static FailureWatcher uiWatcher = new FailureWatcher(
            new UIErrorHandler(),
            Paths.get(System.getProperty("artifactsDir","build/artifacts")),
            PAGE::get
    );

    public PageFactory getPageFactory() {
        return pageFactory;
    }
}
