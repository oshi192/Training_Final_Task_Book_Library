package config;

import java.util.Locale;
import java.util.ResourceBundle;

//todo thread locale
//todo time utime!! for bd

/**
 *
 */
public class ResourceBundleManager {

    private static final String BUNDLE_NAME_CONFIG="config";
    private static final String BUNDLE_NAME_MESSAGES="messages";
    private static final String BUNDLE_NAME_SQL="sql";

    public static final String BOOK_FIND_BY_ID = "book-find-by-id";
    public static final String BOOK_FIND_ALL_BY_USER_ID = "taken-books-by-user-id";
    public static final String BOOK_REQUEST_BY_USER_ID_COUNT = "user-books-requests-count";
    public static final String BOOK_DELETE = "book-delete-by-id";
    public final static String BOOK_REQUEST_COUNT_BY_USER_ID = "user-books-requests-count";
    public static final String BOOKS_WITH_STATUS_BY_USER_ID = "books-with-status-by-user-id";
    public static final String BOOKS_SECTIONS_ENUM = "books-section-enum";
    public static final String BOOK_CREATE = "book-create" ;
    public static final String FIND_BY_EMAIL = "user-find-by-email";
    public static final String FIND_BY_ID = "user-find-by-id";
    public static final String SAVE = "user-insert";
    public static final String BOOKS_COUNT = "books-count";
    public static final String BOOK_FIND_ALL_CLEAN = "book-find-all-clean";
    public static final String ADMIN_TAKEN_BOOKS_COUNT = "adim-taken-books-count";
    public static final String ADMIN_BOOKS_WITH_STATUS = "adim-books-with-status";
    public static final String ADMIN_BOKS_REQUESTS_COUNT = "admin-books-requests-count";
    public static final String BOOK_UPDATE = "book-update";


    public static void setNewLocale(Locale locale) {
        Locale.setDefault(locale);
    }

    public static String getMessage(String key) {
        return ResourceBundle.getBundle(BUNDLE_NAME_MESSAGES, new UTF8Control()).getString(key);
    }
    public static String getConfig(String key) {
        return ResourceBundle.getBundle(BUNDLE_NAME_CONFIG, new UTF8Control()).getString(key);
    }
    public static String getSqlString(String key) {
        return ResourceBundle.getBundle(BUNDLE_NAME_SQL, new UTF8Control()).getString(key);
    }
}