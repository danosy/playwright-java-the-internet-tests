package ui.ioc;

import com.google.inject.AbstractModule;
import com.microsoft.playwright.*;

import java.nio.file.Paths;

public class PlaywrightModule extends AbstractModule {
    @Override
    protected void configure() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setDownloadsPath(Paths.get("C:\\Users\\yonid\\Downloads")));
        bind(Playwright.class).toInstance(playwright);
        BrowserContext context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1920, 1080));
        Page page = context.newPage();
        bind(Page.class).toInstance(page);
    }
}
