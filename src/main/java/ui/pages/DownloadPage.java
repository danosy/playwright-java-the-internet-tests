package ui.pages;

import com.google.inject.Inject;
import com.microsoft.playwright.Page;

import java.io.File;
import java.nio.file.Path;

public class DownloadPage extends BasePage {
    private final String firstElement = "a[href=\"download/zero_bytes_file.txt\"]";
    private final String secondElement = "//*[@id=\"content\"]/div/a[2]";

    @Inject
    public DownloadPage(Page page) {
        super(page);
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
        page.click(firstElement);
    }

    public void clickOnSecondElement() {
        page.click(secondElement);
    }
}
