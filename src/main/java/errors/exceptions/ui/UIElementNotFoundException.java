package errors.exceptions.ui;

import com.microsoft.playwright.Locator;
import errors.exceptions.AppException;

public class UIElementNotFoundException extends AppException {
    public UIElementNotFoundException(Locator locator, Throwable cause) {
        super("Element Not found: " + locator, cause, "Check locators/ poll for element.");
    }
}
