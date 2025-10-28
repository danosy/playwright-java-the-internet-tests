package tests.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.pages.DragAndDropPage;

public class DragAndDropTest extends BaseTest{
    DragAndDropPage dragAndDropPage;
    Logger log = LogManager.getLogger(DragAndDropPage.class);

    @BeforeEach
    public void setUp() {
        log.info("Setting up for login test");
        this.dragAndDropPage = getPageFactory().getDragAndDropPage();
    }

    @Test
    public void testDragAndDrop() {
        dragAndDropPage.navigate("https://the-internet.herokuapp.com/drag_and_drop");
        dragAndDropPage.dragAndDropAToB();
        log.info(dragAndDropPage.getBoxBTextContent());
        Assertions.assertTrue(dragAndDropPage.getBoxBTextContent().contains("A"));
    }
}
