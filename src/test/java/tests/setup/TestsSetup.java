package tests.setup;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;
import ui.factory.PageFactory;

public class TestsSetup {
    private final Playwright playwright;
    private final Browser browser;
    private final BrowserContext browserContext;
    private final Page page;
    private final PageFactory pageFactory;

    public TestsSetup(Injector sharedInjector) {
        this.playwright = sharedInjector.getInstance(Playwright.class);
        this.browser = sharedInjector.getInstance(Browser.class);

        this.browserContext = browser.newContext(
                new Browser.NewContextOptions()
                        .setViewportSize(1920, 1080)
        );

        if (Boolean.getBoolean("tracing.enabled")) {
            browserContext.tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true)
                    .setSources(true));
        }

        this.page = browserContext.newPage();

        Injector testInjector = sharedInjector.createChildInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(Page.class).toInstance(page);
                bind(BrowserContext.class).toInstance(browserContext);
            }
        });

        this.pageFactory = new PageFactory(testInjector);
    }

    public PageFactory getPageFactory() {
        return pageFactory;
    }

    public BrowserContext getBrowserContext() {
        return browserContext;
    }

    public Page getPage() {
        return page;
    }

    public void close() {
        browserContext.close();
        browser.close();
        playwright.close();
    }
}