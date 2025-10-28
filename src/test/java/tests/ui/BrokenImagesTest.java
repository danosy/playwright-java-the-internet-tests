package tests.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.pages.BrokenImagesPage;

public class BrokenImagesTest extends BaseTest {
    private BrokenImagesPage brokenImagesPage;
    Logger log = LogManager.getLogger(AddElementsTest.class);

    @BeforeEach
    public void setUp() {
        log.info("Setting up for login test");
        brokenImagesPage = getPageFactory().getBrokenImagesPage();
    }

    @Test
    public void checkIfImageBroken() {
        brokenImagesPage.navigate("https://the-internet.herokuapp.com/broken_images");
    }
}
