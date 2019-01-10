package controller.command;

import config.ResourceBundleManager;
import controller.util.Md5;
import model.dao.mysql.MySqlUserDao;
import model.entity.User;
import org.apache.log4j.Logger;
import controller.util.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RegistrationCommand implements Command {
    private final static Logger logger = Logger.getLogger(RegistrationCommand.class);

    private final static String ALREADY_EXIST_EMAIL = "registration-already-exist-email";
    private final static String EMPTY_EMAIL = "registration-empty-email";
    private static final String EMPTY_PHONE_NUMBER = "registration-empty-phone-number";
    private static final String EMPTY_PASSWORD = "registration-empty-password";
    private static final String EMPTY_NAME = "registration-empty-name";
    private static final String EMPTY_SURNAME = "registration-empty-surname";

    private final static String REGEX_EMAIL = "regex-email";
    private static final String REGEX_PHONE_NUMBER = "regex-phone";
    private static final String REGEX_PASSWORD = "regex-password";
    private static final String REGEX_NAME = "regex-name";
    private static final String REGEX_SURNAME = "regex-surname";

    private final static String HINT_EMAIL = "hint-email";
    private static final String HINT_PHONE_NUMBER = "hint-phone";
    private static final String HINT_PASSWORD = "hint-password";
    private static final String NOT_EQUALS_PASSWORDS = "hint-password-confirm";
    private static final String HINT_NAME = "hint-name";
    private static final String HINT_SURNAME = "hint-name";

    @Override
    public String executeGet(HttpServletRequest request, HttpServletResponse response) {
        return Configuration.getProperty(Configuration.REGISTRATION_PAGE_PATH);
    }

    @Override
    public String executePost(HttpServletRequest request, HttpServletResponse response) {
        String page;
        User user = createUserFromRequest(request);
        Map<String, String> messages = new HashMap<>();
        checkFields(request, messages, user);
        if (messages.isEmpty()) {
            new MySqlUserDao().save(user);
            request.setAttribute("message", ResourceBundleManager.getMessage("msg-registration-successful"));
            request.removeAttribute("messages");
            page = Configuration.getProperty(Configuration.LOGIN_PAGE_PATH);
        } else {
            request.setAttribute("messages", messages);
            request.setAttribute("newUser", user);
            page = Configuration.getProperty(Configuration.REGISTRATION_PAGE_PATH);
        }
        logger.info("user from registration page: " + user.toString());
        logger.info("page: " +page);
        return page;
    }

    private User createUserFromRequest(HttpServletRequest request) {
        User user = new User();
        user.setRole(User.Role.USER.name());
        user.setPassword(Md5.convertToMd5(request.getParameter("password")));
        user.setName(request.getParameter("first-name"));
        user.setSurname(request.getParameter("surname"));
        user.setEmail(request.getParameter("email"));
        user.setPhoneNumber(request.getParameter("phone-number"));
        return user;
    }

    void checkFields(HttpServletRequest request, Map<String, String> messages, User user) {
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            messages.put("email", ResourceBundleManager.getMessage(EMPTY_EMAIL));
        } else {
            if (!Objects.isNull(new MySqlUserDao().findByEmail(user.getEmail()))) {
                messages.put("email", ResourceBundleManager.getMessage(ALREADY_EXIST_EMAIL));
            } else {
                if (!user.getEmail().matches(ResourceBundleManager.getMessage(REGEX_EMAIL))) {
                    messages.put("email", ResourceBundleManager.getMessage(HINT_EMAIL));
                }
            }
        }
        if (user.getPhoneNumber() == null || user.getPhoneNumber().isEmpty()) {
            messages.put("phoneNumber", ResourceBundleManager.getMessage(EMPTY_PHONE_NUMBER));
        } else {
            if (!user.getPhoneNumber().matches(ResourceBundleManager.getMessage(REGEX_PHONE_NUMBER))) {
                messages.put("phoneNumber", ResourceBundleManager.getMessage(HINT_PHONE_NUMBER));
            }
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            messages.put("password", ResourceBundleManager.getMessage(EMPTY_PASSWORD));
        } else {
            if (!user.getPassword().matches(ResourceBundleManager.getMessage(REGEX_PASSWORD))) {
                messages.put("password", ResourceBundleManager.getMessage(HINT_PASSWORD));
            } else {
                if (!user.getPassword().equals(request.getParameter("confirm-password"))) {
                    messages.put("password", ResourceBundleManager.getMessage(NOT_EQUALS_PASSWORDS));
                }
            }
        }
        if (user.getName() == null || user.getName().isEmpty()) {
            messages.put("name", ResourceBundleManager.getMessage(EMPTY_NAME));
        } else {
            if (!user.getName().matches(ResourceBundleManager.getMessage(REGEX_NAME))) {
                messages.put("name", ResourceBundleManager.getMessage(HINT_NAME));
            }
        }
        if (user.getSurname() == null || user.getSurname().isEmpty()) {
            messages.put("surname", ResourceBundleManager.getMessage(EMPTY_SURNAME));
        } else {
            if (!user.getSurname().matches(ResourceBundleManager.getMessage(REGEX_SURNAME))) {
                messages.put("surname", ResourceBundleManager.getMessage(HINT_SURNAME));
            }
        }
    }
}
