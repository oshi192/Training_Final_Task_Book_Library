package util;

import java.util.ResourceBundle;

public class Configuration {
    public static final String BOOK_SEARCH_PAGE_PATH = "BOOK_SEARCH_PAGE_PATH";
    public static final String ADMIN_ALLBOOKS_PATH = "ADMIN_ALLBOOKS_PATH";
    private static ResourceBundle resourceBundle;
    private static final String BUNDLE_NAME = "config";
    public static final String ALLBOOKS_PATH="ALL_BOOKS_PATH";

    public static final String REGISTRATION_PAGE_PATH = "REGISTRATION_PAGE_PATH";
    public static final String ADMIN_TAKENBOOKS_PATH = "ADMIN_TAKENBOOKS_PATH";
    public static final String ERROR_PAGE_PATH = "ERROR_PAGE_PATH";
    public static final String INDEX_PAGE_PATH = "INDEX_PAGE_PATH";
    public static final String LOGIN_PAGE_PATH = "LOGIN_PAGE_PATH";
    public static final String USER_MYBOOK_PATH = "USER_MYBOOK_PATH";

    static {
        resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
    }
    public static String getProperty(String key) {
        return (String) resourceBundle.getObject(key);
    }
private Configuration(){}
}
