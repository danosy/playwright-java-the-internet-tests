package tests.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.ui.base.BaseUITest;
import ui.pages.DropDownPage;

public class DropDownTest extends BaseUITest {
    DropDownPage dropDownPage;
    Logger log = LogManager.getLogger(DropDownTest.class);

    @BeforeEach
    void setUp() {
        log.info("Init DropDownTest");
        dropDownPage = getPageFactory().getDropDownPage();
    }

    @Test
    void selectOption2() {
        dropDownPage.navigate("https://the-internet.herokuapp.com/dropdown");
        dropDownPage.selectOption(2);
        Assertions.assertEquals("2", dropDownPage.getSelectValue());
    }
}
