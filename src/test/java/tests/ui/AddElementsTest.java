package tests.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.ui.base.BaseUITest;
import ui.pages.AddElementsPage;

public class AddElementsTest extends BaseUITest {
    private AddElementsPage addElementsPage;
    Logger log = LogManager.getLogger(AddElementsTest.class);

    @BeforeEach
    public void setUp() {
        log.info("Setting up for login test");
        this.addElementsPage = getPageFactory().getAddElementsPage();
    }

    @Test
    void addElementTest() {
        addElementsPage.navigate("https://the-internet.herokuapp.com/add_remove_elements/");
        addElementsPage.clickOnAddElementButton();
        Assertions.assertTrue(addElementsPage.isDeleteButtonVisible());
    }

    @Test
    void deleteElementTest() {
        addElementTest();
        addElementsPage.clickOnDeleteElementButton();
        Assertions.assertFalse(addElementsPage.isDeleteButtonVisible());
    }
}
