package ui.factory;

import com.google.inject.Inject;
import com.google.inject.Injector;
import ui.pages.*;

public class PageFactory {
    private final Injector injector;

    @Inject
    public PageFactory(Injector injector) {
        this.injector = injector;
    }

    public LoginPage getLoginPage() {
        return injector.getInstance(LoginPage.class);
    }

    public AddElementsPage getAddElementsPage() {
        return injector.getInstance(AddElementsPage.class);
    }

    public BrokenImagesPage getBrokenImagesPage() {
        return injector.getInstance(BrokenImagesPage.class);
    }

    public CheckboxesPage getCheckboxesPage() {
        return injector.getInstance(CheckboxesPage.class);
    }

    public MainPage getMainPage() {
        return injector.getInstance(MainPage.class);
    }

    public DragAndDropPage getDragAndDropPage() {return injector.getInstance(DragAndDropPage.class);}

    public DropDownPage getDropDownPage() {return injector.getInstance(DropDownPage.class);}

    public DynamicContentPage getDynamicContentPage() { return injector.getInstance(DynamicContentPage.class); }

    public DynamicLoadingPage getDynamicLoadingPage() { return injector.getInstance(DynamicLoadingPage.class); }

    public EntryAdPage getEntryAdPage()  {return injector.getInstance(EntryAdPage.class);}

    public ExitIntentPage getExitIntentPage() {return injector.getInstance(ExitIntentPage.class);}

    public DownloadPage getDownloadPage() {
        return injector.getInstance(DownloadPage.class);
    }

    public IFramePage getIFramePage() {
        return injector.getInstance(IFramePage.class);
    }

    public HorizontalSliderPage getHorizontalSliderPage() {
        return injector.getInstance(HorizontalSliderPage.class);
    }
}