package tests.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import ui.pages.CheckboxesPage;
import ui.pages.MainPage;

public class CheckboxesTest extends BaseTest {
    private MainPage mainPage;
    private CheckboxesPage checkboxesPage;
    Logger log = LogManager.getLogger(CheckboxesTest.class);

    @BeforeEach
    public void setUp() {
        log.info("Setting up for checkboxes test");
        mainPage = getPageFactory().getMainPage();
        checkboxesPage = getPageFactory().getCheckboxesPage();
    }

    @Test
    void testTwoCheckboxesChecked() {
        mainPage.navigate("https://the-internet.herokuapp.com/");
        mainPage.clickOnCheckboxesLink();
        checkboxesPage.clickOnCheckbox(1);
        Assert.assertTrue(checkboxesPage.isChecked(1));
        Assert.assertTrue(checkboxesPage.isChecked(2));
    }
}
