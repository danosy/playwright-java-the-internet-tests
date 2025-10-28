package tests.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.pages.DownloadPage;
import ui.pages.DropDownPage;

public class DownloadTest extends BaseTest {
    DownloadPage downloadPage;
    Logger log = LogManager.getLogger(DownloadTest.class);

    @BeforeEach
    void setUp() {
        log.info("Init DownloadTest");
        downloadPage = getPageFactory().getDownloadPage();
    }

    @Test
    void downloadFirstFile() {
        downloadPage.navigate("https://the-internet.herokuapp.com/download");
        downloadPage.clickOnSecondElement();
    }
}
