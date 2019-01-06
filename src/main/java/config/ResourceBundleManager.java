package config;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleManager {
    private static final String BUNDLE_NAME_CONFIG="config";
    private static final String BUNDLE_NAME_MESSAGES="messages";
    private static final String BUNDLE_NAME_SQL="sql";

    public static void setNewLocale(Locale locale) {
        Locale.setDefault(locale);
    }

//    public static ResourceBundle getResourceBundle() {
//        return ResourceBundle.getBundle(BUNDLE_NAME_MESSAGES, new UTF8Control());
//    }

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