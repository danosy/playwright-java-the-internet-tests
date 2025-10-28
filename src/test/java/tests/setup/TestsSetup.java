package tests.setup;

import com.google.inject.Guice;
import com.google.inject.Injector;
import ui.factory.PageFactory;
import ui.ioc.PlaywrightModule;

public class TestsSetup {private final Injector injector;

    public TestsSetup() {
        this.injector = Guice.createInjector(new PlaywrightModule());
    }

    public PageFactory getPageFactory() {
        return injector.getInstance(PageFactory.class);    }
}
