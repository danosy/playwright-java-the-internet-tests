package ui.constants;

import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static final String usernameIsInvalidMessage = " Your username is invalid!\n" +
            "×";
    public static final String loginSuccessMessage = " You logged into a secure area!\n" +
            "×";


    public static final String secureAreaHeader = " Secure Area";
    public static final String loginPageHeader = "Login Page";

    public static final Map<ArrowKeys, String> arrowKeysStringMap;

    static {
        arrowKeysStringMap = new HashMap<>();
        arrowKeysStringMap.put(ArrowKeys.UP, "ArrowUp");
        arrowKeysStringMap.put(ArrowKeys.DOWN, "ArrowDown");
        arrowKeysStringMap.put(ArrowKeys.LEFT, "ArrowLeft");
        arrowKeysStringMap.put(ArrowKeys.RIGHT, "ArrowRight");
    }
}
