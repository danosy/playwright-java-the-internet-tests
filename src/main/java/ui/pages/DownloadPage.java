package ui.pages;

import com.google.inject.Inject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.io.File;
import java.nio.file.Path;

public class DownloadPage extends BasePage {
    private final Locator firstElement;
    private final Locator secondElement;

    @Inject
    public DownloadPage(Page page) {
        super(page);
        this.firstElement = page.locator("a[href=\"download/zero_bytes_file.txt\"]");
        this.secondElement = page.locator("//*[@id=\"content\"]/div/a[2]");
        implementDownloadListener(page);
    }

    private static void implementDownloadListener(Page page) {
        page.onDownload(download -> {
            Path downloadPath = download.path();
            System.out.println(downloadPath);

            if (downloadPath != null) {
                File file = new File(downloadPath.toString());

                if (!file.exists()) {
                    throw new RuntimeException("File doesn't exists");
                }
            }
        });
    }

    public void clickOnFirstElement() {
        firstElement.click();
    }

    public void clickOnSecondElement() {
        secondElement.click();
    }
}
