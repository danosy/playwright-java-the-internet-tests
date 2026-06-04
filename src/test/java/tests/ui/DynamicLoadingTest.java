package tests.ui;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import tests.ui.base.BaseUITest;
import ui.pages.DynamicLoadingPage;

public class DynamicLoadingTest extends BaseUITest {
    private DynamicLoadingPage dynamicLoadingPage;
    Logger log = LogManager.getLogger(DynamicLoadingPage.class);

    @BeforeEach
    public void setUp() {
        log.info("Setting up for DynamicLoadingPage");
        this.dynamicLoadingPage = getPageFactory().getDynamicLoadingPage();
    }

    @Test
    void helloWorldVisibleTest1() {
        dynamicLoadingPage.navigate("https://the-internet.herokuapp.com/dynamic_loading/1");
        dynamicLoadingPage.clickOnStart();
        dynamicLoadingPage.getFinishContainer().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        Assert.assertEquals(dynamicLoadingPage.getFinishContainer().textContent().trim(), "Hello World!");
    }

    //Better practice
    //Use functions that give you end result and encapsulate the wait
    @Test
    void helloWorldVisibleTest2() {
        dynamicLoadingPage.navigate("https://the-internet.herokuapp.com/dynamic_loading/2");
        dynamicLoadingPage.clickOnStart();
        String result = dynamicLoadingPage.waitForFinishedText();
        Assert.assertEquals(result, "Hello World!");
    }
}
