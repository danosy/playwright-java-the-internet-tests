package tests.ui;

import com.microsoft.playwright.Locator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.ui.base.BaseUITest;
import ui.pages.DynamicContentPage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DynamicContentTest extends BaseUITest {
    Logger log = LogManager.getLogger(DynamicContentTest.class);
    DynamicContentPage dynamicContentPage;

    @BeforeEach
    void setUp() {
        log.info("Starting DynamicContentPage test");
        this.dynamicContentPage = getPageFactory().getDynamicContentPage();
    }

    @Test
    public void assetImagesCountTest() {
        dynamicContentPage.navigate("https://the-internet.herokuapp.com/dynamic_content");
        Locator allRandomImageSrc = dynamicContentPage.getAllRandomImageSrc();
        Assertions.assertEquals(3, allRandomImageSrc.count());
    }

    @Test
    public void assertRandomness() {
        dynamicContentPage.navigate("https://the-internet.herokuapp.com/dynamic_content");
        List<Locator> allRandomImageSrc = dynamicContentPage.getAllRandomImageSrc().all();
        Set<String> imagesSrcs = allRandomImageSrc.stream()
                .map(img -> img.getAttribute("src"))
                .collect(Collectors.toSet());

        log.info("Different images are loaded : {}", allRandomImageSrc.size());
        Assertions.assertNotEquals(1, imagesSrcs.size());

        //Not best practice. Should separate.
        Set<String> firstImagesSrcs = new HashSet<>();
        for (int i = 0 ; i < 10 ; i++) {
            dynamicContentPage.reload();
            Locator firstImageLocator = dynamicContentPage.getAllRandomImageSrc().first();
            firstImagesSrcs.add(firstImageLocator.getAttribute("src"));
        }

        log.info(firstImagesSrcs);
        Assertions.assertNotEquals(1, imagesSrcs.size());
    }
}
