package controller.command;

import config.ResourceBundleManager;
import controller.util.LogInOutUtils;
import controller.util.Md5;
import model.dao.mysql.MySqlUserDao;
import model.entity.User;
import org.apache.log4j.Logger;
import controller.util.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class LogInCommand implements Command {
    private static Logger logger = Logger.getLogger(LogInCommand.class);
    private static LogInOutUtils utils = new LogInOutUtils();

    private final static String WRONG_EMAIL= "login-wrong-email";
    private final static String WRONG_PASSWORD= "login-wrong-password";
    private final static String ENTER_EMAIL= "login-please-enter-email";
    private final static String ENTER_PASSWORD= "login-please-enter-password";
    private final static String REGEX_EMAIL= "regex-email";
    private final static String HINT_EMAIL= "hint-email";
    private final static String REGEX_PASSWORD= "regex-password";
    private final static String HINT_PASSWORD= "hint-password";

    @Override
    public String executeGet(HttpServletRequest request, HttpServletResponse response) {

        return Configuration.getProperty(Configuration.LOGIN_PAGE_PATH);
    }

    @Override
    public String executePost(HttpServletRequest request, HttpServletResponse response) {
        String page;
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        logger.info(request.getParameterNames().toString());
        logger.info("email = " + email + " pass = " + pass);
        Map<String, String> messages = new HashMap<>();
        if (checkEmailAndPasword(request,messages,email,pass)) {
            User user = new MySqlUserDao().findByEmail(email);
            logger.info("found user:" + user);
            if (user != null) {
                if (Md5.matching(user.getPassword(),pass)) {
                    user.setPassword("");
                    logIn(request, user);
                    page = "redirect:";
                } else {
                    messages.put("password",ResourceBundleManager.getMessage(WRONG_PASSWORD) );
                    page = Configuration.getProperty(Configuration.LOGIN_PAGE_PATH);
                }
            } else {
                messages.put("email", ResourceBundleManager.getMessage(WRONG_EMAIL));
                page = Configuration.getProperty(Configuration.LOGIN_PAGE_PATH);
            }
        }else{
            page = Configuration.getProperty(Configuration.LOGIN_PAGE_PATH);
        }

        request.setAttribute("messages", messages);
        logger.info("errorMessages: " + messages.toString());
        logger.info("toPage: " + page);
        return page;
    }


    public boolean checkEmailAndPasword(HttpServletRequest request,Map<String, String> messages,String email,String pass){
        if (email == null || email.isEmpty()) {
            messages.put("email", ResourceBundleManager.getMessage(ENTER_EMAIL));
        }else{
            if(!email.matches(ResourceBundleManager.getMessage(REGEX_EMAIL))){
                messages.put("email",  ResourceBundleManager.getMessage(HINT_EMAIL));
            }else{
                request.setAttribute("userEmail",email);
            }
        }
        if (pass == null || pass.isEmpty()) {
            messages.put("password", ResourceBundleManager.getMessage(ENTER_PASSWORD));
        }else{
            if(!pass.matches(ResourceBundleManager.getMessage(REGEX_PASSWORD))){
                messages.put("password", ResourceBundleManager.getMessage(HINT_PASSWORD));
            }
        }
        return messages.isEmpty();
    }

    //todo rename or remove
    private void logIn(HttpServletRequest request, User user) {
        Map<Integer, HttpSession> loggedUsers = utils.getLoggedUsers();
        destroyPreviousSession(loggedUsers, user.getId());
        loggedUsers.put(user.getId(), request.getSession());
        utils.setLoggedUsers(loggedUsers);
        sessionSetup(request, user);
    }

    private void destroyPreviousSession(Map<Integer, HttpSession> loggedUsers, int userId) {
        if (loggedUsers.containsKey(userId)) {
            loggedUsers.get(userId).invalidate();//todo read more about
        }
    }

    private void sessionSetup(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        session.setAttribute("userId", user.getId());
        session.setAttribute("role", user.getRole());
        session.setAttribute("firstName", user.getName());
        session.setAttribute("surName", user.getSurname());
        session.setAttribute("user", user);
    }
}

