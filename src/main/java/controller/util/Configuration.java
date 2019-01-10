package controller.util;

import java.util.ResourceBundle;

/**
 * Class for getting pages addresses
 */
public class Configuration {
    //todo thread locale для бандля под нескольких юзеров
    public static final String BOOK_SEARCH_PAGE_PATH = "BOOK_SEARCH_PAGE_PATH";
    public static final String ADMIN_ALLBOOKS_PATH = "ADMIN_ALLBOOKS_PATH";
    public static final String TAKEN_BOOKS_PAGE_PATH = "TAKEN_BOOKS_PAGE_PATH";
    public static final String BOOKS_REQUESTS_PAGE_PATH = "BOOKS_REQUESTS_PAGE_PATH";
    public static final String MY_BOOKS_PAGE_PATH = "MY_BOOKS_PAGE_PATH";
    public static final String MY_REQUESTS_PAGE_PATH = "MY_REQUESTS_PAGE_PATH";
    private static ResourceBundle resourceBundle;
    private static final String BUNDLE_NAME = "config";
    public static final String ALLBOOKS_PATH="ALL_BOOKS_PATH";

    public static final String REGISTRATION_PAGE_PATH = "REGISTRATION_PAGE_PATH";
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
