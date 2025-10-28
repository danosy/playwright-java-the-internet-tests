package tests.ui;

import ui.factory.PageFactory;
import tests.setup.TestsSetup;

public class BaseTest {
    private final PageFactory pageFactory;

    public BaseTest() {
        TestsSetup testSetup = new TestsSetup();
        this.pageFactory = testSetup.getPageFactory();
    }

    public PageFactory getPageFactory() {
        return pageFactory;
    }
}
