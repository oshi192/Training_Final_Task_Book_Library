package util;

import java.util.ResourceBundle;

public class Configuration {
    public static final String BOOK_SEARCH_PAGE_PATH = "BOOK_SEARCH_PAGE_PATH";
    private static ResourceBundle resourceBundle;
    private static final String BUNDLE_NAME = "config";

    public static final String DATABASE_DRIVER_NAME = "DATABASE_DRIVER_NAME";
    public static final String DATABASE_URL = "DATABASE_URL";
    public static final String ERROR_PAGE_PATH = "ERROR_PAGE_PATH";
    public static final String INDEX_PAGE_PATH = "INDEX_PAGE_PATH";
    public static final String LOGIN_PAGE_PATH = "LOGIN_PAGE_PATH";
    public static final String REGISTRATION_PAGE_PATH = "REGISTRATION_PAGE_PATH";

    static {
        resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
    }
    public static String getProperty(String key) {
        return (String) resourceBundle.getObject(key);
    }
private Configuration(){}
}
