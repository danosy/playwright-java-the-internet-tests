package ui.ioc;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.microsoft.playwright.*;

public class PlaywrightModule extends AbstractModule {

    @Provides
    Playwright providePlaywright() {
        return Playwright.create();
    }

    @Provides
    Browser provideBrowser(Playwright playwright) {
        return playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(System.getenv("GITHUB_ACTIONS") != null)
        );
    }
}